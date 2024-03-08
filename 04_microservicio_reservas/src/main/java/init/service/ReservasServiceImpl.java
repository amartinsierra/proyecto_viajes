package init.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import init.dao.ReservasDao;
import init.dtos.ReservaDto;
import init.model.Hotel;
import init.model.TokenResponse;
import init.model.Vuelo;
import init.utilities.Mapeador;
import jakarta.annotation.PostConstruct;
@Service
public class ReservasServiceImpl implements ReservasService {
	@Value("${app.urlAuth}")
	String urlAuth;
	@Value("${app.username}")
	String username;
	@Value("${app.password}")
	String password;
	@Value("${app.client_id}")
	String clientId;
	@Value("${app.grant_type}")
	String grantType;
	@Autowired
	ReservasDao reservasDao;
	@Autowired
	@Qualifier("ribbonClient")
	RestClient restClientRibbon;
	
	@Autowired
	@Qualifier("noRibbonClient")
	RestClient restClientNoRibbon;
	@Autowired
	Mapeador mapper;
	
	String token;
	
	@PostConstruct //este método será llamado cuando la instancia esté disponible
	public void init() {
		token=getToken();
	}
	
	@Override
	public List<ReservaDto> reservasCliente(String usuario) {
		return reservasDao.findByUsuario(usuario)
				.stream()
				.map(r->mapper.reservaToDto(r))
				.toList();
	}

	@Override
	public void reservar(ReservaDto dto, int plazas) {
		String urlVuelos="http://localhost:9000/";
		String urlHoteles="http://localhost:8000/";
		double total;
		//obtiene hotel y vuelo para calcular precio total
		
		
		Hotel hotel=restClientRibbon.get().uri(urlHoteles+"hotel/"+dto.getHotelDto().getIdHotel()).retrieve().body(Hotel.class);
		Vuelo vuelo=restClientRibbon.get().uri(urlVuelos+"vuelo/"+dto.getVueloDto().getIdVuelo()).retrieve().body(Vuelo.class);
		//calcula precio total
		total=plazas*(hotel.getPrecio()+vuelo.getPrecio());
		dto.setPrecio(total);
		//graba reserva
		reservasDao.save(mapper.dtoToReserva(dto));
		//actualiza microservicio de vuelos
		restClientRibbon.put()
		.uri(urlVuelos+"actualizar/"+dto.getVueloDto().getIdVuelo()+"/"+plazas)
		.header("Authorization", "Bearer "+token)
		.retrieve();
		

	}
	private String getToken() {
		
		MultiValueMap<String,String> params=new LinkedMultiValueMap<>();
		params.add("client_id", clientId);
		params.add("username", username);
		params.add("password", password);
		params.add("grant_type", grantType);
		
		return restClientNoRibbon.post()
		.uri(urlAuth)
		.contentType(MediaType.APPLICATION_FORM_URLENCODED)
		.body(params)
		.retrieve()
		.body(TokenResponse.class) //TokenResponse
		.getAccess_token();
	}

}

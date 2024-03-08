package init.service;

import java.util.List;

import init.model.Hotel;

public interface HotelesService {
	List<Hotel> hotelesDisponiblesDestino(String destino);
	Hotel hotelIdentificador(int idHotel);
	List<String> destinos();
}

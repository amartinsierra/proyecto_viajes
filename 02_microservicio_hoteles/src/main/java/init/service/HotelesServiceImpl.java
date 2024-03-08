package init.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import init.dao.HotelesDao;
import init.model.Hotel;
@Service
public class HotelesServiceImpl implements HotelesService {
	@Autowired
	HotelesDao hotelesDao;
	@Override
	public List<Hotel> hotelesDisponiblesDestino(String destino) {
		return hotelesDao.findByLocalizacionAndDisponible(destino, true);
	}

	@Override
	public Hotel hotelIdentificador(int idHotel) {
		return hotelesDao.findById(idHotel).orElse(null);
	}

	@Override
	public List<String> destinos() {
		return hotelesDao.findAll().stream()
				.map(h->h.getLocalizacion())
				.distinct()
				.toList();
	}

}

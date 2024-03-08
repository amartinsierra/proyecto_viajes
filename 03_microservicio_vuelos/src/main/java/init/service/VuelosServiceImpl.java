package init.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import init.dao.VuelosDao;
import init.model.Vuelo;
@Service
public class VuelosServiceImpl implements VuelosService {
	@Autowired
	VuelosDao vuelosDao;

	@Override
	public List<Vuelo> vuelosPorDestinoConPlazas(String destino, int reservas) {
		return vuelosDao.findDisponibles(destino, reservas);
	}

	@Override
	public void actualizarPlazas(int reservas, int idVuelo) {
		vuelosDao.updatePlazas(reservas, idVuelo);
	}

	@Override
	public Vuelo vueloPorId(int idVuelo) {
		return vuelosDao.findById(idVuelo).orElse(null);
	}

}

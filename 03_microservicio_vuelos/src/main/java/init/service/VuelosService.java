package init.service;

import java.util.List;

import init.model.Vuelo;

public interface VuelosService {
	List<Vuelo> vuelosPorDestinoConPlazas(String destino,int reservas);
	void actualizarPlazas(int reservas, int idVuelo);
	Vuelo vueloPorId(int idVuelo);
}

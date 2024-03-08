package init.service;

import java.util.List;

import init.dtos.ReservaDto;

public interface ReservasService {
	List<ReservaDto> reservasCliente(String usuario);
	void reservar(ReservaDto dto,int plazas);
}

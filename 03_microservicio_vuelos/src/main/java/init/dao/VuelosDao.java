package init.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import init.model.Vuelo;

public interface VuelosDao extends JpaRepository<Vuelo, Integer> {
	@Query("select v from Vuelo v where v.destino=?1 and v.plazas>=?2")
	List<Vuelo> findDisponibles(String destino, int reservas);
	
	@Modifying
	@Transactional
	@Query("update Vuelo v set v.plazas=v.plazas-?1 where v.idVuelo=?2")
	void updatePlazas(int reservas, int idVuelo);
}

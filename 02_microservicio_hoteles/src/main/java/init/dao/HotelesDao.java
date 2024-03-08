package init.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import init.model.Hotel;

public interface HotelesDao extends JpaRepository<Hotel, Integer> {
	List<Hotel> findByLocalizacionAndDisponible(String localizacion,boolean disponible);
}

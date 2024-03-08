package init.dtos;

import init.model.Hotel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class HotelDto {
	private int idHotel;
	private String nombre;
	private int categoria;
	private double precio;
	private boolean disponible;
	private String localizacion;
}

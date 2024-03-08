package init.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import init.model.Hotel;
import init.service.HotelesService;
@CrossOrigin("*")
@RestController
public class HotelesController {
	@Autowired
	HotelesService hotelesService;
	@GetMapping(value="destinos",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> destinos(){
		return new ResponseEntity<>(hotelesService.destinos(),HttpStatus.OK);
	}
	@GetMapping(value="hoteles",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Hotel>> hotelesDisponibles(@RequestParam("destino") String destino){
		return new ResponseEntity<>(hotelesService.hotelesDisponiblesDestino(destino), HttpStatus.OK);
	}
	@GetMapping(value="hotel/{idHotel}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Hotel> hotel(@PathVariable("idHotel") int idHotel){
		return new ResponseEntity<>(hotelesService.hotelIdentificador(idHotel), HttpStatus.OK);
	}
}

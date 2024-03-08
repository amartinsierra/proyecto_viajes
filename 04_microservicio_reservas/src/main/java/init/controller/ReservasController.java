package init.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import init.dtos.ReservaDto;
import init.service.ReservasService;
@CrossOrigin("*")
@RestController
public class ReservasController {
	@Autowired
	ReservasService service;
	@GetMapping(value="reservas/{usuario}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ReservaDto>> reservasUsuario(@PathVariable("usuario") String usuario){
		return new ResponseEntity<>(service.reservasCliente(usuario),HttpStatus.OK);
	}
	@PostMapping(value="alta/{plazas}",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> guardarReserva(@RequestBody ReservaDto dto, @PathVariable("plazas")int plazas){
		service.reservar(dto, plazas);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}

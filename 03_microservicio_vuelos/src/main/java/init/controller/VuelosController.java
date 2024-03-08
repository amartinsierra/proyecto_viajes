package init.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import init.model.Vuelo;
import init.service.VuelosService;
@CrossOrigin("*")
@RestController
public class VuelosController {
	@Autowired
	VuelosService vuelosService;
	@GetMapping(value="vuelos", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Vuelo>> vuelosDisponibles(@RequestParam("destino") String destino, @RequestParam("reservas") int reservas){
		return new ResponseEntity<List<Vuelo>>(vuelosService.vuelosPorDestinoConPlazas(destino, reservas), HttpStatus.OK);
	}
	@GetMapping(value="vuelo/{idVuelo}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Vuelo> vueloPorId(@PathVariable("idVuelo") int idVuelo){
		return new ResponseEntity<Vuelo>(vuelosService.vueloPorId(idVuelo), HttpStatus.OK);
	}
	@PutMapping(value="actualizar/{idVuelo}/{reservas}")
	public ResponseEntity<Void> actualizarPlazas(@PathVariable("idVuelo") int idVuelo,@PathVariable("reservas") int reservas){
		vuelosService.actualizarPlazas(reservas, idVuelo);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}

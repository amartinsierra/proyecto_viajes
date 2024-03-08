package init.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import init.model.Cliente;
import init.service.ClientesService;
@CrossOrigin("*")
@Controller
public class ClientesController {
	@Autowired
	ClientesService clientesService;
	@GetMapping(value="autenticar",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> autenticar(@RequestParam("usuario") String usuario, @RequestParam("password") String password) {
		return new ResponseEntity<Cliente>(clientesService.autenticar(usuario, password),HttpStatus.OK);
	}
	@PostMapping(value="registrar",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> registrar(@RequestBody Cliente cliente){
		if(clientesService.registrar(cliente)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
}

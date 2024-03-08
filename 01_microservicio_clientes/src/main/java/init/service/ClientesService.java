package init.service;

import init.model.Cliente;

public interface ClientesService {
	Cliente autenticar(String usuario, String password);
	boolean registrar(Cliente cliente);
}

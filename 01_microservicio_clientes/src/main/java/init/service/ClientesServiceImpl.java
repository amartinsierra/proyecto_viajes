package init.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import init.dao.ClientesDao;
import init.model.Cliente;
@Service
public class ClientesServiceImpl implements ClientesService {
	@Autowired
	ClientesDao clientesDao;
	@Override
	public Cliente autenticar(String usuario, String password) {
		return clientesDao.findByUsuarioAndPassword(usuario, password);
	}

	@Override
	public boolean registrar(Cliente cliente) {
		if(clientesDao.findById(cliente.getUsuario()).isPresent()) {
			return false;
		}
		clientesDao.save(cliente);
		return true;
	}

}

package siglo21.springboot.backend.apirest.models.services;

import java.util.List;

import siglo21.springboot.backend.apirest.models.entity.Cliente;

public interface IClienteService {

	public List<Cliente> findAll();

	public Cliente findById(int id);
	
	public boolean existsById(int id);

	public Cliente save(Cliente cliente);

	public void delete(int id);
}

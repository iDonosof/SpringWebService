package siglo21.springboot.backend.apirest.models.services;

import java.util.List;

import siglo21.springboot.backend.apirest.models.entity.Mesa;

public interface IMesaService {

	public List<Mesa> findAll();

	public Mesa findById(int id);

	public Mesa save(Mesa mesa);
	
	public Mesa changeStatusAvailable(int id);
	
	public Mesa changeStatusNotAvailable(int id);

	public void delete(int id);
}

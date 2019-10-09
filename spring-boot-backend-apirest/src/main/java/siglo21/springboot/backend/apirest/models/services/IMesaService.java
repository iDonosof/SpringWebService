package siglo21.springboot.backend.apirest.models.services;

import java.util.List;

import siglo21.springboot.backend.apirest.models.entity.Mesa;

public interface IMesaService {

	public List<Mesa> findAll();

	public Mesa findById(int id);

	public Mesa save(Mesa mesa);

	public void delete(int id);
}

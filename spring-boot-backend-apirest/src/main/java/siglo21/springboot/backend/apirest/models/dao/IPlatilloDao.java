package siglo21.springboot.backend.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import siglo21.springboot.backend.apirest.models.entity.Platillo;

public interface IPlatilloDao extends CrudRepository<Platillo, Integer> {

	public boolean existsByNombre(String nombre);
	
	public Platillo findByNombre(String nombre);
}

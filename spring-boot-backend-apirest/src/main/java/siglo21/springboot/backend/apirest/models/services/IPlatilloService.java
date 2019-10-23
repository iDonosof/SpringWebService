package siglo21.springboot.backend.apirest.models.services;

import java.util.List;

import siglo21.springboot.backend.apirest.models.entity.Platillo;

public interface IPlatilloService {

	public List<Platillo> findAll();
	
	public Platillo findById(int id);
	
	public Platillo save(Platillo platillo, boolean saveOption);
	
	public void delete(int id);
}

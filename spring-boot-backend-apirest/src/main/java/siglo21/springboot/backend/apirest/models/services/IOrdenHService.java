package siglo21.springboot.backend.apirest.models.services;

import java.util.List;

import siglo21.springboot.backend.apirest.models.entity.OrdenH;

public interface IOrdenHService {

	public List<OrdenH> findAll();
	
	public OrdenH findById(int id);
	
	public OrdenH save(OrdenH ordenH);
	
	public void delete(int id);
	
	public OrdenH changeStatusPaid(int id);
}

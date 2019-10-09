package siglo21.springboot.backend.apirest.models.services;

import java.util.List;

import siglo21.springboot.backend.apirest.models.entity.OrdenB;

public interface IOrdenBService {

	public List<OrdenB> findAll();

	public OrdenB findById(int id);

	public OrdenB save(OrdenB ordenB);

	public void delete(int id);
}

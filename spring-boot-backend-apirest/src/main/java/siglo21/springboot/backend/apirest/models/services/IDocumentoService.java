package siglo21.springboot.backend.apirest.models.services;

import java.util.List;

import siglo21.springboot.backend.apirest.models.entity.Documento;

public interface IDocumentoService {

	public List<Documento> findAll();
	
	public Documento findById(int id);
	
	public Documento save(Documento documento);
	
	public void delete(int id);
}

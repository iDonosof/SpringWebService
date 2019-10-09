package siglo21.springboot.backend.apirest.models.services;

import java.util.List;

import siglo21.springboot.backend.apirest.models.entity.Proveedor;

public interface IProveedorService {
	public List<Proveedor> findAll();

	public Proveedor findById(String id);

	public Proveedor save(Proveedor proveedor);

	public void delete(String id);
}

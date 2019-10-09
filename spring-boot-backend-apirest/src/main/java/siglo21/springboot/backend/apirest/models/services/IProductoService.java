package siglo21.springboot.backend.apirest.models.services;

import java.util.List;

import siglo21.springboot.backend.apirest.models.entity.Producto;

public interface IProductoService {

	public List<Producto> findAll();

	public Producto findById(int id);

	public Producto save(Producto producto);

	public void delete(int id);
}

package siglo21.springboot.backend.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import siglo21.springboot.backend.apirest.models.entity.Producto;

public interface IProductoDao extends CrudRepository<Producto, Integer>{

}

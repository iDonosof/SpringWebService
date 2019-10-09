package siglo21.springboot.backend.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import siglo21.springboot.backend.apirest.models.entity.Proveedor;

public interface IProveedorDao extends CrudRepository<Proveedor, String>{

}

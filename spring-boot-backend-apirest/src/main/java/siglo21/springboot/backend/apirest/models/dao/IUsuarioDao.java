package siglo21.springboot.backend.apirest.models.dao;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import siglo21.springboot.backend.apirest.models.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Integer>{

	public Usuario findByUsername(String username);
	
	/*@Query("select u from usuario as u where u.usuario=?1")
	public Usuario findByQuery(String username);*/
}

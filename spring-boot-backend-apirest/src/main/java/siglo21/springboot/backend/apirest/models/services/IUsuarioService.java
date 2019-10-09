package siglo21.springboot.backend.apirest.models.services;

import java.util.List;

import siglo21.springboot.backend.apirest.models.entity.Usuario;

public interface IUsuarioService {

	public List<Usuario> findAll();
	
	public Usuario findById(int id);
	
	public Usuario save(Usuario usuario);
	
	public void delete(int id);
}

package com.siglo21.springboot.backend.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.siglo21.springboot.backend.apirest.models.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Integer>{
	
	public Usuario findByUsername(String username);
	
	
	/* Otra opcion 
	@Query("select u from usuario as u where u.username = ?1")
	public Usuario findByUsername(String username);
	*/
}

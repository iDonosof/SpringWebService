package com.siglo21.springboot.backend.apirest.models.services;

import java.util.List;

import com.siglo21.springboot.backend.apirest.models.entity.Cliente;

public interface IClienteService {

	public List<Cliente> findAll();
	
	public Cliente save(Cliente cliente);
	
	public Cliente findById(int id);
	
	public void delete(int id);
}

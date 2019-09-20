package com.siglo21.springboot.backend.apirest.models.services;

import java.util.List;

import com.siglo21.springboot.backend.apirest.models.entity.Producto;

public interface IProductoService {
	
	public List<Producto> findAll();

	public Producto save(Producto producto);

	public Producto findById(int id);

	public void delete(int id);
}

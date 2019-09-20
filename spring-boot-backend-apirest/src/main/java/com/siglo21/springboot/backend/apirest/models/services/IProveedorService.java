package com.siglo21.springboot.backend.apirest.models.services;

import java.util.List;

import com.siglo21.springboot.backend.apirest.models.entity.Proveedor;

public interface IProveedorService {

	public List<Proveedor> findAll();

	public Proveedor save(Proveedor proveedor);

	public Proveedor findById(int id);

	public void delete(int id);
}

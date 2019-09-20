package com.siglo21.springboot.backend.apirest.models.services;

import java.util.List;

import com.siglo21.springboot.backend.apirest.models.entity.Platillo;

public interface IPlatilloService {
	
	public List<Platillo> findAll();

	public Platillo save(Platillo platillo);

	public Platillo findById(int id);

	public void delete(int id);
}

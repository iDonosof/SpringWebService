package com.siglo21.springboot.backend.apirest.models.services;

import java.util.List;

import com.siglo21.springboot.backend.apirest.models.entity.Ingrediente;

public interface IIngredienteService {
	
	public List<Ingrediente> findAll();
	
	public Ingrediente save(Ingrediente ingrediente);
	
	public Ingrediente findById(int id);
	
	public void delete(int id);
}

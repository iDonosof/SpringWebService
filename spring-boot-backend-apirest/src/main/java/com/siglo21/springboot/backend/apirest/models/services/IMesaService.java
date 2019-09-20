package com.siglo21.springboot.backend.apirest.models.services;

import java.util.List;

import com.siglo21.springboot.backend.apirest.models.entity.Mesa;

public interface IMesaService {

	public List<Mesa> findAll();
	
	public Mesa save(Mesa mesa);
	
	public Mesa findById(int id);
	
	public void delete(int id);
}

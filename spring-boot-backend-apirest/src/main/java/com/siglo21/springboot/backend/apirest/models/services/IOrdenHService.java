package com.siglo21.springboot.backend.apirest.models.services;

import java.util.List;

import com.siglo21.springboot.backend.apirest.models.entity.OrdenH;

public interface IOrdenHService {

	public List<OrdenH> findAll();
	
	public OrdenH save(OrdenH ordenH);
	
	public OrdenH findById(int id);
	
	public void delete(int id);
}

package com.siglo21.springboot.backend.apirest.models.services;

import java.util.List;

import com.siglo21.springboot.backend.apirest.models.entity.OrdenB;

public interface IOrdenBService {

	public List<OrdenB> findAll();
	
	public OrdenB save(OrdenB ordenB);
	
	public OrdenB findById(int id);
	
	public void delete(int id);
}

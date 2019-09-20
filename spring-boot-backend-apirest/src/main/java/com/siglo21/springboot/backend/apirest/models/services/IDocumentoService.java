package com.siglo21.springboot.backend.apirest.models.services;

import java.util.List;

import com.siglo21.springboot.backend.apirest.models.entity.Documento;

public interface IDocumentoService {

	public List<Documento> findAll();
	
	public Documento save(Documento documento);
	
	public Documento findById(int id);
	
	public void delete(int id);
}

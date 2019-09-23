package com.siglo21.springboot.backend.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.siglo21.springboot.backend.apirest.models.entity.Documento;
import com.siglo21.springboot.backend.apirest.models.services.IDocumentoService;

@CrossOrigin(origins = {"http://localhost"})
@RestController
@RequestMapping("/documentos")
public class DocumentoController {
/*
	@Autowired
	private IDocumentoService documentoService;
	
	@GetMapping("/obtener-documentos")
	public List<Documento> Documentos() {
		return documentoService.findAll();
	}
	
	@GetMapping("/buscar-documento/{id}")
	public Documento BuscarDocumento(@PathVariable int id) {
		return documentoService.findById(id);
	}
	
	@PostMapping("/crear-documento")
	@ResponseStatus(HttpStatus.CREATED)
	public Documento CrearDocumento(@RequestBody Documento documento) {
		return documentoService.save(documento);
	}
	
	@DeleteMapping("/eliminar-documento/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void EliminarDocumento(@PathVariable int id) {
		documentoService.delete(id);
	}*/
	
 }

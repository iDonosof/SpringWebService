package com.siglo21.springboot.backend.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.siglo21.springboot.backend.apirest.models.entity.Ingrediente;
import com.siglo21.springboot.backend.apirest.models.services.IIngredienteService;

@CrossOrigin(origins = {"http://localhost"})
@RestController
@RequestMapping("/ingredientes")
public class IngredienteController {

	@Autowired
	private IIngredienteService ingredienteService;
	
	@GetMapping("/obtener-ingredientes")
	public List<Ingrediente> Ingredientes() {
		return ingredienteService.findAll();
	}
	
	@GetMapping("/buscar-ingrediente/{id}")
	public Ingrediente BuscarIngrediente(@PathVariable int id) {
		return ingredienteService.findById(id);
	}
	
	@PostMapping("/crear-ingrediente")
	@ResponseStatus(HttpStatus.CREATED)
	public Ingrediente CrearIngrediente(@RequestBody Ingrediente ingrediente) {
		return ingredienteService.save(ingrediente);
	}
	
	@PutMapping("/actualizar-ingrediente/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Ingrediente ActualizarIngrediente(@RequestBody Ingrediente ingrediente, @PathVariable int id) {
		Ingrediente ingredienteActual = ingredienteService.findById(id);
		ingredienteActual.setCantidad(ingrediente.getCantidad());
		return ingredienteService.save(ingredienteActual);
	}
	
	@DeleteMapping("/eliminar-ingrediente/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void EliminarIngrediente(@PathVariable int id) {
		ingredienteService.delete(id);
	}
}
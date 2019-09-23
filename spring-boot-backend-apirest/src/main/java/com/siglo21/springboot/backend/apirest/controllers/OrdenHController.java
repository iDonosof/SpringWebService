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

import com.siglo21.springboot.backend.apirest.models.services.IOrdenHService;
import com.siglo21.springboot.backend.apirest.models.entity.OrdenH;

@CrossOrigin(origins = {"http://localhost"})
@RestController
@RequestMapping("/ordenh")
public class OrdenHController {
	
	@Autowired
	private IOrdenHService ordenHService;
	
	@GetMapping("/obtener-ordenh")
	public List<OrdenH> OrdenH() {
		return ordenHService.findAll();
	}
	
	@GetMapping("/buscar-ordenh/{id}")
	public OrdenH BuscarOrdenH(@PathVariable int id) {
		return ordenHService.findById(id);
	}
	
	@PostMapping("/crear-ordenh")
	@ResponseStatus(HttpStatus.CREATED)
	public OrdenH CrearOrdenH(@RequestBody OrdenH ordenH) {
		return ordenHService.save(ordenH);
	}
	
	@PutMapping("/actualizar-ordenh/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public OrdenH ActualizarOrdenH(@RequestBody OrdenH ordenH, @PathVariable int id) {
		OrdenH ordenHActual = ordenHService.findById(id);
		ordenHActual.setEstado(ordenH.getEstado());
		ordenHActual.setTotal(ordenH.getTotal());
		return ordenHService.save(ordenHActual);
	}
	
	@DeleteMapping("/eliminar-ordenh/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void EliminarOrdenH(@PathVariable int id) {
		ordenHService.delete(id);
	}
}

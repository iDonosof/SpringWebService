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

import com.siglo21.springboot.backend.apirest.models.entity.OrdenB;
import com.siglo21.springboot.backend.apirest.models.services.IOrdenBService;

@CrossOrigin(origins = {"http://localhost"})
@RestController
@RequestMapping("/ordenb")
public class OrdenBController {

	@Autowired
	private IOrdenBService ordenBService;
	
	@GetMapping("/obtener-ordenb")
	public List<OrdenB> OrdenB() {
		return ordenBService.findAll();
	}
	
	@GetMapping("/buscar-ordenb/{id}")
	public OrdenB BuscarOrdenB(@PathVariable int id) {
		return ordenBService.findById(id);
	}
	
	@PostMapping("/crear-ordenb")
	@ResponseStatus(HttpStatus.CREATED)
	public OrdenB CrearOrdenB(@RequestBody OrdenB ordenB) {
		return ordenBService.save(ordenB);
	}
	
	@PutMapping("/actualizar-ordenb/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public OrdenB ActualizarOrdenB(@RequestBody OrdenB ordenB, @PathVariable int id) {
		OrdenB ordenBActual = ordenBService.findById(id);
		ordenBActual.setCantidad(ordenB.getCantidad());
		ordenBActual.setSubtotal(ordenB.getSubtotal());
		return ordenBService.save(ordenBActual);
	}
	
	@DeleteMapping("/eliminar-ordenb/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void EliminarOrdenB(@PathVariable int id) {
		ordenBService.delete(id);
	}
}

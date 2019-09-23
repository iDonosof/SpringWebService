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

import com.siglo21.springboot.backend.apirest.models.services.IPedidoBService;
import com.siglo21.springboot.backend.apirest.models.entity.PedidoB;

@CrossOrigin(origins = {"http://localhost"})
@RestController
@RequestMapping("/pedidob")
public class PedidoBController {

	@Autowired
	private IPedidoBService pedidoBService;
	
	@GetMapping("/obtener-pedidob")
	public List<PedidoB> PedidoB() {
		return pedidoBService.findAll();
	}
	
	@GetMapping("/buscar-pedidob/{id}")
	public PedidoB BuscarPedidoB(@PathVariable int id) {
		return pedidoBService.findById(id);
	}
	
	@PostMapping("/crear-pedidob")
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoB CrearPedidoB(@RequestBody PedidoB pedidoB) {
		return pedidoBService.save(pedidoB);
	}
	
	@PutMapping("/actualizar-pedidob/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoB ActualizarPedidoB(@RequestBody PedidoB pedidoB, @PathVariable int id) {
		PedidoB pedidoBActual = pedidoBService.findById(id);
		pedidoBActual.setCantidad(pedidoB.getCantidad());
		pedidoBActual.setSubtotal(pedidoB.getSubtotal());
		return pedidoBService.save(pedidoBActual);
	}
	
	@DeleteMapping("/eliminar-pedidob/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void EliminarPedidoB(@PathVariable int id) {
		pedidoBService.delete(id);
	}
}

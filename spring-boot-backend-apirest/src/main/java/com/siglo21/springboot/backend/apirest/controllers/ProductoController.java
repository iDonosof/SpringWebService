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

import com.siglo21.springboot.backend.apirest.models.entity.Producto;
import com.siglo21.springboot.backend.apirest.models.services.IProductoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/productos")
public class ProductoController {

	@Autowired
	private IProductoService productoService;
	
	@GetMapping("/obtener-productos")
	public List<Producto> Productos() {
		return productoService.findAll();
	}
	
	@GetMapping("/buscar-producto/{id}")
	public Producto BuscarProducto(@PathVariable int id) {
		return productoService.findById(id);
	}
	
	@PostMapping("/crear-producto")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto CrearProducto(Producto producto) {
		return productoService.save(producto);
	}
	
	@PutMapping("/actualizar-producto/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto ActualizarProducto(Producto producto, @PathVariable int id) {
		Producto productoActual = productoService.findById(id);
		productoActual.setNombre(producto.getNombre());
		productoActual.setDescripcion(producto.getDescripcion());
		productoActual.setCantidad(producto.getCantidad());
		productoActual.setCategoria(producto.getCategoria());
		productoActual.setPrecio(producto.getPrecio());
		return productoService.save(productoActual);
	}
	
	@DeleteMapping("/eliminar-producto/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void EliminarProducto(@PathVariable int id) {
		productoService.delete(id);
	}
}

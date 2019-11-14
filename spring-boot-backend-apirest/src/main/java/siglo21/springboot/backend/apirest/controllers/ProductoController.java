package siglo21.springboot.backend.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import siglo21.springboot.backend.apirest.models.entity.Producto;
import siglo21.springboot.backend.apirest.models.services.IProductoService;

@CrossOrigin(origins = { "http://localhost", "*" })
@RestController
@RequestMapping("/productos")
public class ProductoController {
	@Autowired
	private IProductoService productoService;

	@GetMapping("/obtener-productos")
	public ResponseEntity<?> ObtenerProductos() {
		Map<String, Object> response = new HashMap<>();
		try {
			List<Producto> productos = productoService.findAll();
			if(productos == null || productos.size() == 0) {
				response.put("message", "No se encontró productos en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<List<Producto>>(productos, HttpStatus.OK);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/buscar-producto/{id}")
	public ResponseEntity<?> BuscarProducto(@PathVariable int id) {
		Map<String, Object> response = new HashMap<>();
		try {
			Producto producto = productoService.findById(id);
			if(producto == null) {
				response.put("message", "Porducto no se encontró en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Producto>(producto, HttpStatus.OK);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/crear-producto")
	public ResponseEntity<?> CrearProducto(@RequestBody Producto producto) {
		Map<String, Object> response = new HashMap<>();
		try {
			Producto productoOut = productoService.save(producto);
			if(productoOut == null) {
				response.put("message", "Ocurrio un error al ingresar el producto a la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<Producto>(productoOut, HttpStatus.CREATED);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/actualizar-producto/{id}")
	public ResponseEntity<?> ActualizarProducto(@RequestBody Producto producto, @PathVariable int id) {
		Map<String, Object> response = new HashMap<>();
		try {
			Producto productoActual = productoService.findById(id);
			if(productoActual == null) {
				response.put("message", "Producto no se encontró en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			productoActual.setNombre(producto.getNombre());
			productoActual.setDescripcion(producto.getDescripcion());
			productoActual.setCantidad(producto.getCantidad());
			productoActual.setPrecio(producto.getPrecio());
			productoActual.setCategoria(producto.getCategoria());
			productoActual = productoService.save(productoActual);
			if(productoActual == null) {
				response.put("message", "Ocurrio un error al actualizar el producto en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<Producto>(productoActual, HttpStatus.CREATED);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/eliminar-producto/{id}")
	public ResponseEntity<?> EliminarProducto(@PathVariable int id) {
		Map<String, Object> response = new HashMap<>();
		try {
			productoService.delete(id);
			response.put("message", "Producto eliminado con exito");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

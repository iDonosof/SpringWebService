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

import siglo21.springboot.backend.apirest.models.entity.Proveedor;
import siglo21.springboot.backend.apirest.models.services.IProveedorService;

@CrossOrigin(origins = { "http://localhost", "*" })
@RestController
@RequestMapping("/proveedores")
public class ProveedorController {
	@Autowired
	private IProveedorService proveedorService;

	@GetMapping("/obtener-proveedores")
	public ResponseEntity<?> ObtenerProveedors() {
		Map<String, Object> response = new HashMap<>();
		try {
			List<Proveedor> proveedores = proveedorService.findAll();
			if(proveedores == null || proveedores.size() == 0) {
				response.put("message", "No se encontró ningun proveedor en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<List<Proveedor>>(proveedores, HttpStatus.OK);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/buscar-proveedor/{id}")
	public ResponseEntity<?> BuscarProveedor(@PathVariable String id) {
		Map<String, Object> response = new HashMap<>();
		try {
			Proveedor proveedor = proveedorService.findById(id);
			if(proveedor == null) {
				response.put("message", "El proveedor no se encontró en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Proveedor>(proveedor, HttpStatus.OK);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/crear-proveedor")
	public ResponseEntity<?> CrearProveedor(@RequestBody Proveedor proveedor) {
		Map<String, Object> response = new HashMap<>();
		try {
			Proveedor proveedorOut = proveedorService.save(proveedor);
			if(proveedorOut == null) {
				response.put("message", "Ocurrio un error al ingresar el proveedor en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<Proveedor>(proveedorOut, HttpStatus.CREATED);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/actualizar-proveedor/{id}")
	public ResponseEntity<?> ActualizarProveedor(@RequestBody Proveedor proveedor, @PathVariable String id) {
		Map<String, Object> response = new HashMap<>();
		try {
			Proveedor proveedorActual = proveedorService.findById(id);
			if(proveedorActual == null) {
				response.put("message", "No se encontró al proveedor en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			proveedorActual.setNombre(proveedor.getNombre());
			proveedorActual.setTelefono(proveedor.getTelefono());
			proveedorActual.setDireccion(proveedor.getDireccion());
			proveedorActual.setCorreo(proveedor.getCorreo());
			proveedorActual = proveedorService.save(proveedorActual);
			if(proveedorActual == null) {
				response.put("message", "Ocurrio un error al actualizar el proveedor en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<Proveedor>(proveedorActual, HttpStatus.CREATED);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/eliminar-proveedor/{id}")
	public ResponseEntity<?> EliminarProveedor(@PathVariable String id) {
		Map<String, Object> response = new HashMap<>();
		try {
			proveedorService.delete(id);
			response.put("message", "Proveedor eliminado con exito");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

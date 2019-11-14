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

import siglo21.springboot.backend.apirest.models.entity.Ingrediente;
import siglo21.springboot.backend.apirest.models.services.IIngredienteService;

@CrossOrigin(origins = { "http://localhost", "*" })
@RestController
@RequestMapping("/ingredientes")
public class IngredienteController {

	@Autowired
	private IIngredienteService ingredienteService;

	@GetMapping("/obtener-ingredientes")
	public ResponseEntity<?> ObtenerIngredientes() {
		Map<String, Object> response = new HashMap<>();
		try {
			List<Ingrediente> ingredientes = ingredienteService.findAll();
			if(ingredientes == null || ingredientes.size() == 0) {
				response.put("message", "No se encontraron ingredientes en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<List<Ingrediente>>(ingredientes, HttpStatus.OK);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/buscar-ingrediente/{id}")
	public ResponseEntity<?> BuscarIngrediente(@PathVariable int id) {
		Map<String, Object> response = new HashMap<>();
		try {
			Ingrediente ingrediente = ingredienteService.findById(id);
			if(ingrediente == null) {
				response.put("message", "No se encontro el ingrediente en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Ingrediente>(ingrediente, HttpStatus.OK);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/crear-ingrediente")
	public ResponseEntity<?> CrearIngrediente(@RequestBody Ingrediente ingrediente) {
		Map<String, Object> response = new HashMap<>();
		try {
			Ingrediente ingredienteOut = ingredienteService.save(ingrediente);
			if (ingredienteOut == null) {
				response.put("message", "Ocurrio un error al ingresar el ingrediente en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<Ingrediente>(ingrediente, HttpStatus.CREATED);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/actualizar-ingrediente/{id}")
	public ResponseEntity<?> ActualizarIngrediente(@RequestBody Ingrediente ingrediente, @PathVariable int id) {
		Map<String, Object> response = new HashMap<>();
		try {
			Ingrediente ingredienteActual = ingredienteService.findById(id);
			if(ingredienteActual == null) {
				response.put("message", "No se encontró el ingrediente en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			ingredienteActual.setCantidad(ingrediente.getCantidad());
			ingredienteActual = ingredienteService.save(ingredienteActual);
			if(ingredienteActual == null) {
				response.put("message", "Ocurrio un error al actualizar el ingrediente en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<Ingrediente>(ingredienteActual, HttpStatus.CREATED);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/eliminar-ingrediente/{id}")
	public ResponseEntity<?> EliminarIngrediente(@PathVariable int id) {
		Map<String, Object> response = new HashMap<>();
		try {
			ingredienteService.delete(id);
			response.put("message", "Ingrediente eliminado con exito");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

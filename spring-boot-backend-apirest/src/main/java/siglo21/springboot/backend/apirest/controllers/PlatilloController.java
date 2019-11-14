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

import siglo21.springboot.backend.apirest.models.entity.Platillo;
import siglo21.springboot.backend.apirest.models.services.IPlatilloService;

@CrossOrigin(origins = { "http://localhost", "*" })
@RestController
@RequestMapping("/platillos")
public class PlatilloController {
	
	@Autowired
	private IPlatilloService platilloService;

	@GetMapping("/obtener-platillos")
	public ResponseEntity<?> ObtenerPlatillos() {
		Map<String, Object> response = new HashMap<>();
		try {
			List<Platillo> platillos = platilloService.findAll(); 
			if(platillos == null || platillos.size() == 0) {
				response.put("message", "No se encontró platillos en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<List<Platillo>>(platillos, HttpStatus.OK);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/buscar-platillo/{id}")
	public ResponseEntity<?> BuscarPlatillo(@PathVariable int id) {
		Map<String, Object> response = new HashMap<>();
		try {
			Platillo platillo = platilloService.findById(id);
			if(platillo == null) {
				response.put("message", "Platillo no se encontró en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Platillo>(platillo, HttpStatus.OK);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/crear-platillo")
	public ResponseEntity<?> CrearPlatillo(@RequestBody Platillo platillo) {
		Map<String, Object> response = new HashMap<>();
		try {
			Platillo platilloOut = platilloService.save(platillo, false);
			if(platillo == null) {
				response.put("message", "Ocurrio un error al ingresar el platillo a la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<Platillo>(platilloOut, HttpStatus.CREATED);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/actualizar-platillo/{id}")
	public ResponseEntity<?> ActualizarPlatillo(@RequestBody Platillo platillo, @PathVariable int id) {
		Map<String, Object> response = new HashMap<>();
		try {
			Platillo platilloActual = platilloService.findById(id);
			if(platilloActual == null) {
				response.put("message", "El platillo no se encontró en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			platilloActual.setNombre(platillo.getNombre());
			platilloActual.setTiempo(platillo.getTiempo());
			platilloActual.setPrecio(platillo.getPrecio());
			platilloActual = platilloService.save(platilloActual, true);
			if(platilloActual == null) {
				response.put("message", "El platillo no se puedo actualizar en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<Platillo>(platillo, HttpStatus.CREATED);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/eliminar-platillo/{id}")
	public ResponseEntity<?> EliminarPlatillo(@PathVariable int id) {
		Map<String, Object> response = new HashMap<>();
		try {
			platilloService.delete(id);
			response.put("message", "El platillo fue eliminado con exito");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

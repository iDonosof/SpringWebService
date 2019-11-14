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

import siglo21.springboot.backend.apirest.models.entity.Mesa;
import siglo21.springboot.backend.apirest.models.services.IMesaService;

@CrossOrigin(origins = { "http://localhost", "*" })
@RestController
@RequestMapping("/mesas")
public class MesaController {
	
	@Autowired
	private IMesaService mesaService;
	
	@GetMapping("/obtener-mesas")
	public ResponseEntity<?> ObtenerMesas() {
		Map<String, Object> response = new HashMap<>();
		try {
			List<Mesa> mesas = mesaService.findAll();
			if(mesas == null || mesas.size() == 0) {
				response.put("message", "No se encontro mesas en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<List<Mesa>>(mesas, HttpStatus.OK);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);		
		}
	}
	
	@GetMapping("/buscar-mesa/{id}")
	public ResponseEntity<?> BuscarMesa(@PathVariable int id) {
		Map<String, Object> response = new HashMap<>();
		try {
			Mesa mesa = mesaService.findById(id);
			if(mesa == null) {
				response.put("message", "La mesa no se encontró en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Mesa>(mesa, HttpStatus.OK);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);		
		}
	}
	
	@GetMapping("/cambiar-estado-disponible/{id}")
	public ResponseEntity<?> CambiarEstadoDisponible(@PathVariable int id) {
		Map<String, Object> response = new HashMap<>();
		try {
			Mesa mesa = mesaService.changeStatusAvailable(id);
			if(mesa == null) {
				response.put("message", "No se encontro la mesa en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Mesa>(mesa, HttpStatus.OK);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);		
		}
	}
	
	@GetMapping("/cambiar-estado-no-disponible/{id}") 
	public ResponseEntity<?> CambiarEstadoNoDisponible(@PathVariable int id) {
		Map<String, Object> response = new HashMap<>();
		try {
			Mesa mesa = mesaService.changeStatusNotAvailable(id);
			if(mesa == null) {
				response.put("message", "No se encontro la mesa en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Mesa>(mesa, HttpStatus.OK);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);		
		}
	}
	
	@PostMapping("/crear-mesa")
	public ResponseEntity<?> CrearMesa(@RequestBody Mesa mesa) {
		Map<String, Object> response = new HashMap<>();
		try {
			Mesa mesaOut = mesaService.save(mesa);
			if(mesaOut == null) {
				response.put("message", "Ocurrio un error al ingresar la mesa a la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<Mesa>(mesaOut, HttpStatus.CREATED);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
	
	@PutMapping("/actualizar-mesa/{id}")
	public ResponseEntity<?> ActualizarMesa(@RequestBody Mesa mesa, @PathVariable int id) {
		Map<String, Object> response = new HashMap<>();
		try {
			Mesa mesaActual = mesaService.findById(id);
			if(mesaActual == null) {
				response.put("message", "No se encontró la mesa en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			mesaActual.setNumero(mesa.getNumero());
			mesaActual.setCapacidad(mesa.getCapacidad());
			mesaActual = mesaService.save(mesaActual);
			if(mesaActual == null) {
				response.put("message", "Ocurrio un error al actualizar la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<Mesa>(mesa, HttpStatus.CREATED);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
	
	@DeleteMapping("/eliminar-mesa/{id}")
	public ResponseEntity<?> EliminarMesa(@PathVariable int id) {
		Map<String, Object> response = new HashMap<>();
		try {
			mesaService.delete(id);
			response.put("message", "Mesa eliminada con exito");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
}

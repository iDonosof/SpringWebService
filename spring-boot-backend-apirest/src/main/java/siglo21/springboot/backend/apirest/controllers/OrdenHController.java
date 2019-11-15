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

import siglo21.springboot.backend.apirest.models.entity.OrdenH;
import siglo21.springboot.backend.apirest.models.services.IOrdenHService;

@CrossOrigin(origins = { "http://localhost", "*" })
@RestController
@RequestMapping("/ordenh")
public class OrdenHController {
	@Autowired
	private IOrdenHService ordenHService;

	@GetMapping("/obtener-ordenh")
	public ResponseEntity<?> ObtenerOrdenHs() {
		Map<String, Object> response = new HashMap<>();
		try {
			List<OrdenH> ordenHs = ordenHService.findAll();
			if(ordenHs == null || ordenHs.size() == 0) {
				response.put("message", "No se encontró ninguna orden en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<List<OrdenH>>(ordenHs, HttpStatus.OK);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/buscar-ordenh/{id}")
	public ResponseEntity<?> BuscarOrdenH(@PathVariable int id) {
		Map<String, Object> response = new HashMap<>();
		try {
			OrdenH ordenH = ordenHService.findById(id);
			if(ordenH == null) {
				response.put("message", "No se encontro la orden en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);	
			}
			return new ResponseEntity<OrdenH>(ordenH, HttpStatus.OK);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/cambiar-estado-ordenh/{id}")
	public ResponseEntity<?> CambiarEstadoOrdenH(@PathVariable int id) {
		Map<String, Object> response = new HashMap<>();
		try {
			OrdenH ordenH = ordenHService.changeStatusPaid(id);
			if(ordenH == null) {
				response.put("message", "No se encontro la orden en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<OrdenH>(ordenH, HttpStatus.OK);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/crear-ordenh")
	public ResponseEntity<?> CrearOrdenH(@RequestBody OrdenH ordenH) {
		Map<String, Object> response = new HashMap<>();
		try {
			OrdenH ordenHOut = ordenHService.save(ordenH);
			if(ordenHOut == null) {
				response.put("message", "Ocurrio un error al ingresar los datos a la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<OrdenH>(ordenHOut, HttpStatus.CREATED);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/actualizar-ordenh/{id}")
	public ResponseEntity<?> ActualizarOrdenH(@RequestBody OrdenH ordenH, @PathVariable int id) {
		Map<String, Object> response = new HashMap<>();
		try {
			OrdenH ordenHActual = ordenHService.findById(id);
			if(ordenHActual == null) {
				response.put("message", "No se encontró la orden en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			ordenHActual.setEstado(ordenH.getEstado());
			ordenHActual.setTotal(ordenH.getTotal());
			ordenHActual = ordenHService.save(ordenHActual);
			if(ordenHActual == null) {
				response.put("message", "Ocurrio un error al actualizar la orden en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<OrdenH>(ordenHActual, HttpStatus.CREATED);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/eliminar-ordenh/{id}")
	public ResponseEntity<?> EliminarOrdenH(@PathVariable int id) {
		Map<String, Object> response = new HashMap<>();
		try {
			ordenHService.delete(id);
			response.put("message", "Orden eliminado con exito de la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

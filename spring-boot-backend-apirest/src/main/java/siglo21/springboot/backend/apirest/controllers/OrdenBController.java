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

import siglo21.springboot.backend.apirest.models.entity.OrdenB;
import siglo21.springboot.backend.apirest.models.services.IOrdenBService;

@CrossOrigin(origins = { "http://localhost", "*" })
@RestController
@RequestMapping("/ordenb")
public class OrdenBController {
	@Autowired
	private IOrdenBService ordenBService;

	@GetMapping("/obtener-ordenb")
	public ResponseEntity<?> ObtenerOrdenBs() {
		Map<String, Object> response = new HashMap<>();
		try {
			List<OrdenB> ordenBs = ordenBService.findAll();
			if(ordenBs == null || ordenBs.size() == 0) {
				response.put("message", "No se encontró ninguna orden en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<List<OrdenB>>(ordenBs, HttpStatus.OK);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/buscar-ordenb/{id}")
	public ResponseEntity<?> BuscarOrdenB(@PathVariable int id) {
		Map<String, Object> response = new HashMap<>();
		try {
			OrdenB ordenB = ordenBService.findById(id);
			if(ordenB == null) {
				response.put("message", "No se encontró la orden en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<OrdenB>(ordenB, HttpStatus.OK);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/crear-ordenb")
	public ResponseEntity<?> CrearOrdenB(@RequestBody OrdenB ordenB) {
		Map<String, Object> response = new HashMap<>();
		try {
			OrdenB ordenBOut = ordenBService.save(ordenB);
			if(ordenBOut == null) {
				response.put("message", "Ocurrio un error al ingresar la orden en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<OrdenB>(ordenBOut, HttpStatus.CREATED);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/actualizar-ordenb/{id}")
	public ResponseEntity<?> ActualizarOrdenB(@RequestBody OrdenB ordenB, @PathVariable int id) {
		Map<String, Object> response = new HashMap<>();
		try {
			OrdenB ordenBActual = ordenBService.findById(id);
			if(ordenBActual == null) {
				response.put("message", "No se encontro la orden en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			ordenBActual.setCantidad(ordenB.getCantidad());
			ordenBActual.setSubtotal(ordenB.getSubtotal());
			ordenBActual = ordenBService.save(ordenBActual); 
			if(ordenBActual == null) {
				response.put("message", "Ocurrio un error al ingresar actualizar la orden en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<OrdenB>(ordenBActual, HttpStatus.CREATED);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/eliminar-ordenb/{id}")
	public ResponseEntity<?> EliminarOrdenB(@PathVariable int id) {
		Map<String, Object> response = new HashMap<>();
		try {
			ordenBService.delete(id);
			response.put("message", "Se elimino con exito la orden en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

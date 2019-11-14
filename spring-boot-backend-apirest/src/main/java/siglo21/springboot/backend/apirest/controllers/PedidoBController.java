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

import siglo21.springboot.backend.apirest.models.entity.PedidoB;
import siglo21.springboot.backend.apirest.models.services.IPedidoBService;

@CrossOrigin(origins = { "http://localhost", "*" })
@RestController
@RequestMapping("/pedidob")
public class PedidoBController {
	
	@Autowired
	private IPedidoBService pedidoBService;

	@GetMapping("/obtener-pedidob")
	public ResponseEntity<?> ObtenerPedidoBs() {
		Map<String, Object> response = new HashMap<>();
		try {
			List<PedidoB> pedidoBs = pedidoBService.findAll();
			if(pedidoBs == null || pedidoBs.size() == 0) {
				response.put("message", "No se encontró ningun pedido en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<List<PedidoB>>(pedidoBs, HttpStatus.OK);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/buscar-pedidob/{id}")
	public ResponseEntity<?> BuscarPedidoB(@PathVariable int id) {
		Map<String, Object> response = new HashMap<>();
		try {
			PedidoB pedidoB = pedidoBService.findById(id);
			if(pedidoB == null) {
				response.put("message", "No se encontro el pedido en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<PedidoB>(pedidoB, HttpStatus.OK);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/crear-pedidob")
	public ResponseEntity<?> CrearPedidoB(@RequestBody PedidoB pedidoB) {
		Map<String, Object> response = new HashMap<>();
		try {
			PedidoB pedidoBOut = pedidoBService.save(pedidoB);
			if(pedidoBOut == null) {
				response.put("message", "Ocurrio un error al ingresar el pedido a la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<PedidoB>(pedidoBOut, HttpStatus.CREATED);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/actualizar-pedidob/{id}")
	public ResponseEntity<?> ActualizarPedidoB(@RequestBody PedidoB pedidoB, @PathVariable int id) {
		Map<String, Object> response = new HashMap<>();
		try {
			PedidoB pedidoBActual = pedidoBService.findById(id);
			if(pedidoBActual == null) {
				response.put("message", "No se encontró el pedido en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			pedidoBActual.setCantidad(pedidoB.getCantidad());
			pedidoBActual.setSubtotal(pedidoB.getSubtotal());
			pedidoBActual = pedidoBService.save(pedidoBActual);
			if(pedidoBActual == null) {
				response.put("message", "Ocurrio un error al actualizar el pedido en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<PedidoB>(pedidoBActual, HttpStatus.CREATED);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/eliminar-pedidob/{id}")
	public ResponseEntity<?> EliminarPedidoB(@PathVariable int id) {
		Map<String, Object> response = new HashMap<>();
		try {
			pedidoBService.delete(id);
			response.put("message", "Se eliminó el pedido con exito de la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

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

import siglo21.springboot.backend.apirest.models.entity.PedidoH;
import siglo21.springboot.backend.apirest.models.services.IPedidoHService;

@CrossOrigin(origins = { "http://localhost", "*" })
@RestController
@RequestMapping("/pedidoh")
public class PedidoHController {

	@Autowired
	private IPedidoHService pedidoHService;

	@GetMapping("/obtener-pedidoh")
	public ResponseEntity<?> ObtenerPedidoHs() {
		Map<String, Object> response = new HashMap<>();
		try {
			List<PedidoH> pedidoHs = pedidoHService.findAll();
			if (pedidoHs == null || pedidoHs.size() == 0) {
				response.put("message", "No se encontró ningun pedido en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<List<PedidoH>>(pedidoHs, HttpStatus.OK);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/buscar-pedidoh/{id}")
	public ResponseEntity<?> BuscarPedidoH(@PathVariable int id) {
		Map<String, Object> response = new HashMap<>();
		try {
			PedidoH pedidoH = pedidoHService.findById(id);
			if (pedidoH == null) {
				response.put("message", "No se encontró el pedido en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<PedidoH>(pedidoH, HttpStatus.OK);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/cambiar-estado-pedidoh/{id}")
	public ResponseEntity<?> CambiarEstadoPedidoH(@PathVariable int id) {
		Map<String, Object> response = new HashMap<>();
		try {
			PedidoH pedidoH = pedidoHService.changeStatus(id);
			if (pedidoH == null) {
				response.put("message", "No se encontro el pedido en la base de datps");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<PedidoH>(pedidoH, HttpStatus.OK);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/crear-pedidoh")
	public ResponseEntity<?> CrearPedidoH(@RequestBody PedidoH pedidoH) {
		Map<String, Object> response = new HashMap<>();
		try {
			PedidoH pedidoHOut = pedidoHService.save(pedidoH);
			if (pedidoHOut == null) {
				response.put("message", "Ocurrio un error al ingresar el pedido");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<PedidoH>(pedidoHOut, HttpStatus.CREATED);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/actualizar-pedidoh/{id}")
	public ResponseEntity<?> ActualizarPedidoH(@RequestBody PedidoH pedidoH, @PathVariable int id) {
		Map<String, Object> response = new HashMap<>();
		try {
			PedidoH PedidoHActual = pedidoHService.findById(id);
			if (PedidoHActual == null) {
				response.put("message", "No se encontró el pedido en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			PedidoHActual.setTotal(pedidoH.getTotal());
			PedidoHActual = pedidoHService.save(PedidoHActual);
			if (PedidoHActual == null) {
				response.put("message", "Ocurrio un error al actualizar el pedido en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<PedidoH>(PedidoHActual, HttpStatus.CREATED);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/eliminar-pedidoh/{id}")
	public ResponseEntity<?> EliminarPedidoH(@PathVariable int id) {
		Map<String, Object> response = new HashMap<>();
		try {
			pedidoHService.delete(id);
			response.put("message", "Pedido eliminado con exito de la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

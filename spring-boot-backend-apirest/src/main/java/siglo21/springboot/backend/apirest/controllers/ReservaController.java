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

import siglo21.springboot.backend.apirest.models.entity.Reserva;
import siglo21.springboot.backend.apirest.models.services.IReservaService;

@CrossOrigin(origins = { "http://localhost", "*" })
@RestController
@RequestMapping("/reservas")
public class ReservaController {

	@Autowired
	private IReservaService reservaService;

	@GetMapping("/obtener-reservas")
	public ResponseEntity<?> ObtenerReservas() {
		Map<String, Object> response = new HashMap<>();
		try {
			List<Reserva> reservas = reservaService.findAll();
			if(reservas == null || reservas.size() == 0) {
				response.put("message", "No se encontró reservas en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<List<Reserva>>(reservas, HttpStatus.OK);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/buscar-reserva/{id}")
	public ResponseEntity<?> BuscarReserva(@PathVariable int id) {
		Map<String, Object> response = new HashMap<>();
		try {
			Reserva reserva = reservaService.findById(id);
			if(reserva == null) {
				response.put("message", "No se encontró la reserva en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Reserva>(reserva, HttpStatus.OK);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/cambiar-estado-reserva/{id}")
	public ResponseEntity<?> CambiarEstadoReserva(@PathVariable int id) {
		Map<String, Object> response = new HashMap<>();
		try {
			Reserva reserva = reservaService.changeStatusOcuppied(id);
			if(reserva == null) {
				response.put("message", "No se encontro la reserva en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Reserva>(reserva, HttpStatus.OK);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/crear-reserva")
	public ResponseEntity<?> CrearReserva(@RequestBody Reserva reserva) {
		Map<String, Object> response = new HashMap<>();
		try {
			Reserva reservaOut = reservaService.save(reserva);
			if(reservaOut == null) {
				response.put("message", "Ocurrio un error ingresando la reserva en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<Reserva>(reservaOut, HttpStatus.CREATED);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/actualizar-reserva/{id}")
	public ResponseEntity<?> ActualizarReserva(@RequestBody Reserva reserva, @PathVariable int id) {
		Map<String, Object> response = new HashMap<>();
		try {
			Reserva reservaActual = reservaService.findById(id);
			if(reservaActual == null) {
				response.put("message", "No se encontró la reserva en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			reservaActual.setFecha(reserva.getFecha());
			reservaActual.setHora(reserva.getHora());
			reservaActual.setEstado(reserva.getEstado());
			reservaActual.setCantidadPersonas(reserva.getCantidadPersonas());
			reservaActual.setMesaId(reserva.getMesaId());
			reservaActual = reservaService.save(reservaActual);
			if(reservaActual == null) {
				response.put("message", "Ocurrio un error al ingresar reserva en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<Reserva>(reservaActual, HttpStatus.CREATED);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/eliminar-reserva/{id}")
	public ResponseEntity<?> EliminarReserva(@PathVariable int id) {
		Map<String, Object> response = new HashMap<>();
		try {
			reservaService.delete(id);
			response.put("message", "La reserva se elimino con exito");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
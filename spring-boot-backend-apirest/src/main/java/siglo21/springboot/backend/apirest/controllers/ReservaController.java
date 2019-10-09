package siglo21.springboot.backend.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	public List<Reserva> ObtenerReservas() {
		return reservaService.findAll();
	}

	@GetMapping("/buscar-reserva/{id}")
	public Reserva BuscarReserva(@PathVariable int id) {
		return reservaService.findById(id);
	}

	@PostMapping("/crear-reserva")
	@ResponseStatus(HttpStatus.CREATED)
	public Reserva CrearReserva(@RequestBody Reserva reserva) {
		return reservaService.save(reserva);
	}

	@PutMapping("/actualizar-reserva/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Reserva ActualizarReserva(@RequestBody Reserva reserva, @PathVariable int id) {
		Reserva reservaActual = reservaService.findById(id);
		reservaActual.setFecha(reserva.getFecha());
		reservaActual.setHora(reserva.getHora());
		reservaActual.setCantidadPersonas(reserva.getCantidadPersonas());
		reservaActual.setMesaId(reserva.getMesaId());
		return reservaService.save(reservaActual);
	}

	@DeleteMapping("/eliminar-reserva/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void EliminarReserva(@PathVariable int id) {
		reservaService.delete(id);
	}
}

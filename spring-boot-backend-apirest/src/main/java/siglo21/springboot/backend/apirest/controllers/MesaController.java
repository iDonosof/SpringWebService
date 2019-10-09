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

import siglo21.springboot.backend.apirest.models.entity.Mesa;
import siglo21.springboot.backend.apirest.models.services.IMesaService;

@CrossOrigin(origins = { "http://localhost", "*" })
@RestController
@RequestMapping("/mesas")
public class MesaController {
	
	@Autowired
	private IMesaService mesaService;
	
	@GetMapping("/obtener-mesas")
	public List<Mesa> ObtenerMesas() {
		return mesaService.findAll();
	}
	
	@GetMapping("/buscar-mesa/{id}")
	public Mesa BuscarMesa(@PathVariable int id) {
		return mesaService.findById(id);
	}
	
	@PostMapping("/crear-mesa")
	@ResponseStatus(HttpStatus.CREATED)
	public Mesa CrearMesa(@RequestBody Mesa mesa) {
		return mesaService.save(mesa);
	}
	
	@PutMapping("/actualizar-mesa/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Mesa ActualizarMesa(@RequestBody Mesa mesa, @PathVariable int id) {
		Mesa mesaActual = mesaService.findById(id);
		mesaActual.setNumero(mesa.getNumero());
		mesaActual.setCapacidad(mesa.getCapacidad());
		return mesaService.save(mesaActual);
	}
	
	@DeleteMapping("/eliminar-mesa/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void EliminarMesa(@PathVariable int id) {
		mesaService.delete(id);
	}
}

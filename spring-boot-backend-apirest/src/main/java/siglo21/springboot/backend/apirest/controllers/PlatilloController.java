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

import siglo21.springboot.backend.apirest.models.entity.Platillo;
import siglo21.springboot.backend.apirest.models.services.IPlatilloService;

@CrossOrigin(origins = { "http://localhost", "*" })
@RestController
@RequestMapping("/platillos")
public class PlatilloController {
	
	@Autowired
	private IPlatilloService platilloService;

	@GetMapping("/obtener-platillos")
	public List<Platillo> ObtenerPlatillos() {
		return platilloService.findAll();
	}

	@GetMapping("/buscar-platillo/{id}")
	public Platillo BuscarPlatillo(@PathVariable int id) {
		return platilloService.findById(id);
	}

	@PostMapping("/crear-platillo")
	@ResponseStatus(HttpStatus.CREATED)
	public Platillo CrearPlatillo(@RequestBody Platillo platillo) {
		return platilloService.save(platillo, false);
	}

	@PutMapping("/actualizar-platillo/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Platillo ActualizarPlatillo(@RequestBody Platillo platillo, @PathVariable int id) {
		Platillo platilloActual = platilloService.findById(id);
		platilloActual.setNombre(platillo.getNombre());
		platilloActual.setTiempo(platillo.getTiempo());
		return platilloService.save(platilloActual, true);
	}

	@DeleteMapping("/eliminar-platillo/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void EliminarPlatillo(@PathVariable int id) {
		platilloService.delete(id);
	}
}

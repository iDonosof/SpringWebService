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

import siglo21.springboot.backend.apirest.models.entity.PedidoH;
import siglo21.springboot.backend.apirest.models.services.IPedidoHService;

@CrossOrigin(origins = { "http://localhost", "*" })
@RestController
@RequestMapping("/pedidoh")
public class PedidoHController {

	@Autowired
	private IPedidoHService PedidoHService;

	@GetMapping("/obtener-pedidoh")
	public List<PedidoH> ObtenerPedidoHs() {
		return PedidoHService.findAll();
	}

	@GetMapping("/buscar-pedidoh/{id}")
	public PedidoH BuscarPedidoH(@PathVariable int id) {
		return PedidoHService.findById(id);
	}

	@PostMapping("/crear-pedidoh")
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoH CrearPedidoH(@RequestBody PedidoH pedidoH) {
		return PedidoHService.save(pedidoH);
	}

	@PutMapping("/actualizar-pedidoh/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoH ActualizarPedidoH(@RequestBody PedidoH pedidoH, @PathVariable int id) {
		PedidoH PedidoHActual = PedidoHService.findById(id);
		PedidoHActual.setTotal(pedidoH.getTotal());
		return PedidoHService.save(PedidoHActual);
	}

	@DeleteMapping("/eliminar-pedidoh/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void EliminarPedidoH(@PathVariable int id) {
		PedidoHService.delete(id);
	}
}

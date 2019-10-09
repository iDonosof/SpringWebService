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

import siglo21.springboot.backend.apirest.models.entity.Cliente;
import siglo21.springboot.backend.apirest.models.services.IClienteService;

@CrossOrigin(origins = { "http://localhost", "*" })
@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private IClienteService clienteService;

	@GetMapping("/obtener-clientes")
	public List<Cliente> ObtenerClientes() {
		return clienteService.findAll();
	}

	@GetMapping("/buscar-cliente/{id}")
	public Cliente BuscarCliente(@PathVariable int id) {
		return clienteService.findById(id);
	}

	@PostMapping("/crear-cliente")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente CrearCliente(@RequestBody Cliente cliente) {
		return clienteService.save(cliente);
	}

	@PutMapping("/actualizar-cliente/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente ActualizarCliente(@RequestBody Cliente cliente, @PathVariable int id) {
		Cliente clienteActual = clienteService.findById(id);
		clienteActual.setNombre(cliente.getNombre());
		clienteActual.setApellido(cliente.getApellido());
		clienteActual.setCorreo(cliente.getCorreo());
		clienteActual.setTelefono(cliente.getTelefono());
		return clienteService.save(clienteActual);
	}

	@DeleteMapping("/eliminar-usuario/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void EliminarCliente(@PathVariable int id) {
		clienteService.delete(id);
	}
}

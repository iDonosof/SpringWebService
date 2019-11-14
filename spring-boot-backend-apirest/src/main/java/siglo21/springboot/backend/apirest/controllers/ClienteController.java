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

import siglo21.springboot.backend.apirest.models.entity.Cliente;
import siglo21.springboot.backend.apirest.models.services.IClienteService;

@CrossOrigin(origins = { "http://localhost", "*" })
@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private IClienteService clienteService;

	@GetMapping("/obtener-clientes")
	public ResponseEntity<?> ObtenerClientes() {
		Map<String, Object> response = new HashMap<>();
		try {
			List<Cliente> clientes = clienteService.findAll(); 
			if(clientes == null || clientes.size() == 0) {
				response.put("message", "No existen clientes en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<List<Cliente>>(clientes, HttpStatus.OK);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/buscar-cliente/{id}")
	public ResponseEntity<?> BuscarCliente(@PathVariable int id) {
		Map<String, Object> response = new HashMap<>();
		try {
			Cliente cliente = clienteService.findById(id);
			if(cliente == null) {
				response.put("error", "El cliente no encontrado");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/crear-cliente")
	public ResponseEntity<?> CrearCliente(@RequestBody Cliente cliente) {
		Map<String, Object> response = new HashMap<>();
		try {
			Cliente clienteOut = clienteService.save(cliente);
			if(clienteOut == null) {
				response.put("message", "Ocurrio un error en la insersión del cliente, compruebe los datos ingresados");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<Cliente>(clienteOut, HttpStatus.CREATED);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/actualizar-cliente/{id}")
	public ResponseEntity<?> ActualizarCliente(@RequestBody Cliente cliente, @PathVariable int id) {
		Map<String, Object> response = new HashMap<>();
		try {
			Cliente clienteActual = clienteService.findById(id);
			if(clienteActual == null) {
				response.put("message", "Cliente no encontrado en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			clienteActual.setNombre(cliente.getNombre());
			clienteActual.setApellido(cliente.getApellido());
			clienteActual.setCorreo(cliente.getCorreo());
			clienteActual.setTelefono(cliente.getTelefono());
			clienteActual = clienteService.save(clienteActual);
			if(clienteActual == null) {
				response.put("message", "Error al actualizar el usuario en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<Cliente>(clienteActual, HttpStatus.CREATED);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/eliminar-cliente/{id}")
	public ResponseEntity<?> EliminarCliente(@PathVariable int id) {
		Map<String, Object> response = new HashMap<>();
		try {
			clienteService.delete(id);
			response.put("message", "Cliente eliminado con exito");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}

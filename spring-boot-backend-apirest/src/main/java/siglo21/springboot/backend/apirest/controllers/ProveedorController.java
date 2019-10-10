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

import siglo21.springboot.backend.apirest.models.entity.Proveedor;
import siglo21.springboot.backend.apirest.models.services.IProveedorService;

@CrossOrigin(origins = { "http://localhost", "*" })
@RestController
@RequestMapping("/proveedores")
public class ProveedorController {
	@Autowired
	private IProveedorService proveedorService;

	@GetMapping("/obtener-proveedores")
	public List<Proveedor> ObtenerProveedors() {
		return proveedorService.findAll();
	}

	@GetMapping("/buscar-proveedor/{id}")
	public Proveedor BuscarProveedor(@PathVariable String id) {
		return proveedorService.findById(id);
	}

	@PostMapping("/crear-proveedor")
	@ResponseStatus(HttpStatus.CREATED)
	public Proveedor CrearProveedor(@RequestBody Proveedor proveedor) {
		return proveedorService.save(proveedor);
	}

	@PutMapping("/actualizar-proveedor/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Proveedor ActualizarProveedor(@RequestBody Proveedor proveedor, @PathVariable String id) {
		Proveedor proveedorActual = proveedorService.findById(id);
		proveedorActual.setNombre(proveedor.getNombre());
		proveedorActual.setTelefono(proveedor.getTelefono());
		proveedorActual.setDireccion(proveedor.getDireccion());
		proveedorActual.setCorreo(proveedor.getCorreo());
		return proveedorService.save(proveedorActual);
	}

	@DeleteMapping("/eliminar-proveedor/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void EliminarProveedor(@PathVariable String id) {
		proveedorService.delete(id);
	}
}

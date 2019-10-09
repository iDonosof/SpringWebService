package siglo21.springboot.backend.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

import siglo21.springboot.backend.apirest.models.entity.Usuario;
import siglo21.springboot.backend.apirest.models.services.IUsuarioService;

@CrossOrigin(origins = { "http://localhost", "*" })
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@GetMapping("/obtener-usuarios")
	public List<Usuario> ObtenerUsuarios() {
		return usuarioService.findAll();
	}

	@GetMapping("/buscar-usuario/{id}")
	public Usuario BuscarUsuario(@PathVariable int id) {
		return usuarioService.findById(id);
	}

	@PostMapping("/crear-usuario")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario CrearUsuario(@RequestBody Usuario usuario) {
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		return usuarioService.save(usuario);
	}

	@PutMapping("/actualizar-usuario/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario ActualizarUsuario(@RequestBody Usuario usuario, @PathVariable int id) {
		Usuario usuarioActual = usuarioService.findById(id);
		usuarioActual.setNombre(usuario.getNombre());
		usuarioActual.setApellido(usuario.getApellido());
		usuarioActual.setCorreo(usuario.getCorreo());
		usuarioActual.setFechaNacimiento(usuario.getFechaNacimiento());
		usuarioActual.setUsername(usuario.getUsername());
		usuarioActual.setPassword(usuario.getPassword().length() < 25 ? passwordEncoder.encode(usuario.getPassword())
				: usuario.getPassword());
		usuarioActual.setRol(usuario.getRol());
		return usuarioService.save(usuarioActual);
	}

	@DeleteMapping("/eliminar-usuario/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void EliminarUsuario(@PathVariable int id) {
		usuarioService.delete(id);
	}
}

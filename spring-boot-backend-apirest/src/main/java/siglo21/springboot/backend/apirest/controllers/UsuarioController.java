package siglo21.springboot.backend.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public ResponseEntity<?> ObtenerUsuarios() {
		Map<String, Object> response = new HashMap<>();
		try {
			List<Usuario> usuarios = usuarioService.findAll();
			if(usuarios == null || usuarios.size() == 0) {
				response.put("message", "No se encontró ningun usuario en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/buscar-usuario/{id}")
	public ResponseEntity<?> BuscarUsuario(@PathVariable int id) {
		Map<String, Object> response = new HashMap<>();
		try {
			Usuario usuario = usuarioService.findById(id);
			if(usuario == null) {
				response.put("message", "No se encontró al usuario en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/crear-usuario")
	public ResponseEntity<?> CrearUsuario(@RequestBody Usuario usuario) {
		Map<String, Object> response = new HashMap<>();
		try {
			usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
			Usuario usuarioOut = usuarioService.save(usuario); 
			if(usuarioOut == null) {
				response.put("message", "Ocurrio un error al ingresar el usuario a la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<Usuario>(usuarioOut, HttpStatus.CREATED);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/actualizar-usuario/{id}")
	public ResponseEntity<?> ActualizarUsuario(@RequestBody Usuario usuario, @PathVariable int id) {
		Map<String, Object> response = new HashMap<>();
		try {
			Usuario usuarioActual = usuarioService.findById(id);
			if(usuarioActual == null) {
				response.put("message", "No se encontró el usuario en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			usuarioActual.setNombre(usuario.getNombre());
			usuarioActual.setApellido(usuario.getApellido());
			usuarioActual.setCorreo(usuario.getCorreo());
			usuarioActual.setFechaNacimiento(usuario.getFechaNacimiento());
			usuarioActual.setUsername(usuario.getUsername());
			usuarioActual.setPassword(usuario.getPassword().length() < 25 ? passwordEncoder.encode(usuario.getPassword())
					: usuario.getPassword());
			usuarioActual.setRol(usuario.getRol());
			usuarioActual = usuarioService.save(usuarioActual);
			if(usuarioActual == null) {
				response.put("message", "Ocurrio un error al actualizar el usuario en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<Usuario>(usuarioActual, HttpStatus.CREATED);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/eliminar-usuario/{id}")
	public ResponseEntity<?> EliminarUsuario(@PathVariable int id) {
		Map<String, Object> response = new HashMap<>();
		try {
			usuarioService.delete(id);
			response.put("message", "Se elimino el usuario con exito");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

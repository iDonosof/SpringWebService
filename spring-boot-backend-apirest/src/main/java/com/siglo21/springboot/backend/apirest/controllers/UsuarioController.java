package com.siglo21.springboot.backend.apirest.controllers;

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

import com.siglo21.springboot.backend.apirest.models.entity.Usuario;
import com.siglo21.springboot.backend.apirest.models.services.IUsuarioService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired 
	private BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping("/obtener-usuarios")
	public List<Usuario> Usuarios() {
		return usuarioService.findAll();
	}
	
	@GetMapping("/buscar-usuario/{id}")
	public Usuario BuscarUsuario(@PathVariable int id) {
		return usuarioService.findById(id);
	}
	
	@PostMapping("/crear-usuario")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario CrearUsuario(@RequestBody Usuario usuario) {
		if(usuario.getPassword().length() < 20) {
			usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));			
		}
		return usuarioService.save(usuario);
	}
	
	@PutMapping("/actualizar-usuario/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario ActualizarUsuario(@RequestBody Usuario usuario, @PathVariable int id) {
		Usuario usuarioActual = usuarioService.findById(id);
		usuarioActual.setPassword(passwordEncoder.encode(usuario.getPassword()));
		usuarioActual.setNombre(usuario.getNombre());
		usuarioActual.setApellido(usuario.getApellido());
		usuarioActual.setRol(usuario.getRol());
		usuarioActual.setCorreo(usuario.getCorreo());
		usuarioActual.setFechaNacimiento(usuario.getFechaNacimiento());
		return usuarioService.save(usuarioActual);
	}
	
	@DeleteMapping("/eliminar-usuario/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void EliminarUsuario(@PathVariable int id) {
		usuarioService.delete(id);
	}
}

package com.siglo21.springboot.backend.apirest.controllers;

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

import com.siglo21.springboot.backend.apirest.models.entity.Usuario;
import com.siglo21.springboot.backend.apirest.models.services.IUsuarioService;

@CrossOrigin(origins = {"http://localhost"})
@RestController@RequestMapping("/api")
public class UsuarioController {
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@GetMapping("/usuarios")
	public List<Usuario> Usuarios() {
		return usuarioService.findAll();
	}
	
	@GetMapping("/usuarios/{id}")
	public Usuario BuscarUsuario(@PathVariable int id) {
		return usuarioService.findById(id);
	}
	
	@PostMapping("/usuarios")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario CrearUsuario(@RequestBody Usuario usuario) {
		return usuarioService.save(usuario);
	}
	
	@PutMapping("/usuarios/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario ActualizarUsuario(@RequestBody Usuario usuario, @PathVariable int id) {
		Usuario usuarioActual = usuarioService.findById(id);
		usuarioActual.setPassword(usuario.getPassword());
		usuarioActual.setNombre(usuario.getNombre());
		usuarioActual.setApellido(usuario.getApellido());
		usuarioActual.setRol(usuario.getRol());
		usuarioActual.setCorreo(usuario.getCorreo());
		usuarioActual.setFechaNacimiento(usuario.getFechaNacimiento());
		return usuarioService.save(usuarioActual);
	}
	
	
	@DeleteMapping("/usuarios/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void EliminarUsuario(@PathVariable int id) {
		usuarioService.delete(id);
	}
	/*@RequestMapping("/Greeting")
	public String index() {
		return "Hola este es el web service del restaurante siglo XXI";
	}*/
}

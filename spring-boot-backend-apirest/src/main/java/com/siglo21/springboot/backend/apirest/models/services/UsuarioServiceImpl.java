package com.siglo21.springboot.backend.apirest.models.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.siglo21.springboot.backend.apirest.models.dao.IUsuarioDao;
import com.siglo21.springboot.backend.apirest.models.entity.Usuario;

@Service
public class UsuarioServiceImpl implements IUsuarioService, UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		return (List<Usuario>) usuarioDao.findAll();
	}

	@Override
	@Transactional
	public Usuario save(Usuario usuario) {
		return usuarioDao.save(usuario);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findById(int id) {
		return usuarioDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(int id) {
		usuarioDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioDao.findByUsername(username);
		if(usuario == null) {
			logger.error("Error en el login: El usuario " + username + " no existe");
			throw new UsernameNotFoundException("Error en el login: El usuario " + username + " no existe");
		}
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		authorities.add(new SimpleGrantedAuthority("ROLE_FINANZAS"));
		authorities.add(new SimpleGrantedAuthority("ROLE_COCINA"));
		authorities.add(new SimpleGrantedAuthority("ROLE_BODEGA"));
		return new User(usuario.getUsername(), usuario.getPassword(), true, true, true, true, authorities);
	}
}
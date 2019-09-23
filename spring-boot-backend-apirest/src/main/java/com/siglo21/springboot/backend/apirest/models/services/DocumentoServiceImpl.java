package com.siglo21.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.siglo21.springboot.backend.apirest.models.dao.IDocumentoDao;
import com.siglo21.springboot.backend.apirest.models.entity.Documento;

@Service
public class DocumentoServiceImpl implements IDocumentoService{
	
	@Autowired
	private IDocumentoDao documentoDao;

	@Override
	@Transactional(readOnly = true)
	public List<Documento> findAll() {
		return (List<Documento>) documentoDao.findAll();
	}

	@Override
	@Transactional
	public Documento save(Documento documento) {
		return documentoDao.save(documento);
	}

	@Override
	@Transactional(readOnly = true)
	public Documento findById(int id) {
		return documentoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(int id) {
		documentoDao.deleteById(id);
	}
}

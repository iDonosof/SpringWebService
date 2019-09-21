package com.siglo21.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.siglo21.springboot.backend.apirest.models.dao.IMesaDao;
import com.siglo21.springboot.backend.apirest.models.entity.Mesa;

@Service
public class MesaServiceImpl implements IMesaService {

	@Autowired
	private IMesaDao mesaDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Mesa> findAll() {
		return (List<Mesa>) mesaDao.findAll();
	}

	@Override
	@Transactional
	public Mesa save(Mesa mesa) {
		return mesaDao.save(mesa);
	}

	@Override
	@Transactional(readOnly = true)
	public Mesa findById(int id) {
		return mesaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(int id) {
		mesaDao.deleteById(id);
	}

}

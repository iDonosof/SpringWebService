package com.siglo21.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.siglo21.springboot.backend.apirest.models.dao.IPlatilloDao;
import com.siglo21.springboot.backend.apirest.models.entity.Platillo;

@Service
public class PlatilloServiceImpl implements IPlatilloService{

	@Autowired
	private IPlatilloDao platilloDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Platillo> findAll() {
		return (List<Platillo>) platilloDao.findAll();
	}

	@Override
	@Transactional
	public Platillo save(Platillo platillo) {
		return platilloDao.save(platillo);
	}

	@Override
	@Transactional(readOnly = true)
	public Platillo findById(int id) {
		return platilloDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(int id) {
		platilloDao.deleteById(id);
	}
}
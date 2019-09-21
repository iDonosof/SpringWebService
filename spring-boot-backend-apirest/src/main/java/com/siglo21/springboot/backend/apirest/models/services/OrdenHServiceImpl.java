package com.siglo21.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.siglo21.springboot.backend.apirest.models.dao.IOrdenHDao;
import com.siglo21.springboot.backend.apirest.models.entity.OrdenH;

@Service
public class OrdenHServiceImpl implements IOrdenHService{

	@Autowired
	private IOrdenHDao ordenHDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<OrdenH> findAll() {
		return (List<OrdenH>) ordenHDao.findAll();
	}

	@Override
	@Transactional
	public OrdenH save(OrdenH ordenH) {
		return ordenHDao.save(ordenH);
	}

	@Override
	@Transactional(readOnly = true)
	public OrdenH findById(int id) {
		return ordenHDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(int id) {
		ordenHDao.deleteById(id);
	}
}
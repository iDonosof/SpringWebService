package com.siglo21.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.siglo21.springboot.backend.apirest.models.dao.IOrdenBDao;
import com.siglo21.springboot.backend.apirest.models.entity.OrdenB;

@Service
public class OrdenBServiceImpl implements IOrdenBService{

	@Autowired
	private IOrdenBDao ordenBDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<OrdenB> findAll() {
		return (List<OrdenB>) ordenBDao.findAll();
	}

	@Override
	@Transactional
	public OrdenB save(OrdenB ordenB) {
		return ordenBDao.save(ordenB);
	}

	@Override
	@Transactional(readOnly = true)
	public OrdenB findById(int id) {
		return ordenBDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(int id) {
		ordenBDao.deleteById(id);
	}
}
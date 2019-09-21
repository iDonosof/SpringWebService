package com.siglo21.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.siglo21.springboot.backend.apirest.models.dao.IReservaDao;
import com.siglo21.springboot.backend.apirest.models.entity.Reserva;

@Service
public class ReservaServiceImpl implements IReservaService{

	@Autowired
	private IReservaDao reservaDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Reserva> findAll() {
		return (List<Reserva>) reservaDao.findAll();
	}

	@Override
	@Transactional
	public Reserva save(Reserva reserva) {
		return reservaDao.save(reserva);
	}

	@Override
	@Transactional(readOnly = true)
	public Reserva findById(int id) {
		return reservaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(int id) {
		reservaDao.deleteById(id);
	}
}
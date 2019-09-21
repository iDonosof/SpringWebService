package com.siglo21.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.siglo21.springboot.backend.apirest.models.dao.IPedidoHDao;
import com.siglo21.springboot.backend.apirest.models.entity.PedidoH;

@Service
public class PedidoHServiceImpl implements IPedidoHService{

	@Autowired
	private IPedidoHDao pedidoHDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<PedidoH> findAll() {
		return (List<PedidoH>) pedidoHDao.findAll();
	}

	@Override
	@Transactional
	public PedidoH save(PedidoH pedidoH) {
		return pedidoHDao.save(pedidoH);
	}

	@Override
	@Transactional(readOnly = true)
	public PedidoH findById(int id) {
		return pedidoHDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(int id) {
		pedidoHDao.deleteById(id);
	}
}
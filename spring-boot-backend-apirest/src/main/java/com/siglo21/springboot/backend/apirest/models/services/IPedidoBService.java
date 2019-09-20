package com.siglo21.springboot.backend.apirest.models.services;

import java.util.List;

import com.siglo21.springboot.backend.apirest.models.entity.PedidoB;

public interface IPedidoBService {
	
	public List<PedidoB> findAll();

	public PedidoB save(PedidoB pedidoB);

	public PedidoB findById(int id);

	public void delete(int id);
}

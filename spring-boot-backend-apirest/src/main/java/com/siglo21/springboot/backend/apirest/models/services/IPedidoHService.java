package com.siglo21.springboot.backend.apirest.models.services;

import java.util.List;

import com.siglo21.springboot.backend.apirest.models.entity.PedidoH;

public interface IPedidoHService {

	public List<PedidoH> findAll();

	public PedidoH save(PedidoH pedidoH);

	public PedidoH findById(int id);

	public void delete(int id);
}

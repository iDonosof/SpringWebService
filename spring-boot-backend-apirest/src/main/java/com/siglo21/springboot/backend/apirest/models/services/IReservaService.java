package com.siglo21.springboot.backend.apirest.models.services;

import java.util.List;

import com.siglo21.springboot.backend.apirest.models.entity.Reserva;

public interface IReservaService {

	public List<Reserva> findAll();
	
	public Reserva save(Reserva reserva);
	
	public Reserva findById(int id);
	
	public void delete(int id);
}

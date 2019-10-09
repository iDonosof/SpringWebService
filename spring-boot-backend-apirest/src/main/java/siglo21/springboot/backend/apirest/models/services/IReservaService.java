package siglo21.springboot.backend.apirest.models.services;

import java.util.List;

import siglo21.springboot.backend.apirest.models.entity.Reserva;

public interface IReservaService {

	public List<Reserva> findAll();
	
	public Reserva findById(int id);
	
	public Reserva save(Reserva reserva);
	
	public void delete(int id);
}

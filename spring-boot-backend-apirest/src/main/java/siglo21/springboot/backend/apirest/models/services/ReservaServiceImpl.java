package siglo21.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import siglo21.springboot.backend.apirest.models.dao.IReservaDao;
import siglo21.springboot.backend.apirest.models.entity.Reserva;

@Service
public class ReservaServiceImpl implements IReservaService {

	@Autowired
	private IReservaDao reservaDao;
	
	@Override
	public List<Reserva> findAll() {
		return (List<Reserva>) reservaDao.findAll();
	}

	@Override
	public Reserva findById(int id) {
		return reservaDao.findById(id).orElse(null);
	}

	@Override
	public Reserva save(Reserva reserva) {
		return reservaDao.save(reserva);
	}

	@Override
	public void delete(int id) {
		reservaDao.deleteById(id);
	}

}

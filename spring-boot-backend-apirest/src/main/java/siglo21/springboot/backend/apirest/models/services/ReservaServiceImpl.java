package siglo21.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import siglo21.springboot.backend.apirest.models.dao.IClienteDao;
import siglo21.springboot.backend.apirest.models.dao.IMesaDao;
import siglo21.springboot.backend.apirest.models.dao.IReservaDao;
import siglo21.springboot.backend.apirest.models.entity.Reserva;

@Service
public class ReservaServiceImpl implements IReservaService {

	@Autowired
	private IReservaDao reservaDao;
	
	@Autowired
	private IClienteDao clienteDao;
	
	@Autowired
	private IMesaDao mesaDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Reserva> findAll() {
		return (List<Reserva>) reservaDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Reserva findById(int id) {
		return reservaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Reserva save(Reserva reserva) {
		if(!clienteDao.existsById(reserva.getClienteId().getRut())) {
			reserva.setClienteId(clienteDao.save(reserva.getClienteId()));			
		}
		else {
			reserva.setClienteId(clienteDao.findById(reserva.getClienteId().getRut()).orElse(null));
		}
		reserva.setMesaId(mesaDao.findById(reserva.getMesaId().getId()).orElse(null));
		return reservaDao.save(reserva);
	}

	@Override
	@Transactional
	public void delete(int id) {
		reservaDao.deleteById(id);
	}

	@Override
	@Transactional
	public Reserva changeStatusOcuppied(int id) {
		Reserva reservaTemp = reservaDao.findById(id).orElse(null);
		if(reservaTemp != null) {
			reservaTemp.setEstado(2);
			return reservaDao.save(reservaTemp);
		}
		return reservaTemp;
	}

}

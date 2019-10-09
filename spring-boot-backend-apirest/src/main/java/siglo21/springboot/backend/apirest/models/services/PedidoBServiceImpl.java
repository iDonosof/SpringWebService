package siglo21.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import siglo21.springboot.backend.apirest.models.dao.IPedidoBDao;
import siglo21.springboot.backend.apirest.models.entity.PedidoB;

@Service
public class PedidoBServiceImpl implements IPedidoBService {

	@Autowired
	private IPedidoBDao pedidoBDao;

	@Override
	@Transactional(readOnly = true)
	public List<PedidoB> findAll() {
		return (List<PedidoB>) pedidoBDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public PedidoB findById(int id) {
		return pedidoBDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public PedidoB save(PedidoB pedidoB) {
		return pedidoBDao.save(pedidoB);
	}

	@Override
	@Transactional
	public void delete(int id) {
		pedidoBDao.deleteById(id);
	}
}

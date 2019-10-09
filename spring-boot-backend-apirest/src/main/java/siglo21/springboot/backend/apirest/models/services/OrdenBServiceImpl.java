package siglo21.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import siglo21.springboot.backend.apirest.models.dao.IOrdenBDao;
import siglo21.springboot.backend.apirest.models.entity.OrdenB;

@Service
public class OrdenBServiceImpl implements IOrdenBService {

	
	@Autowired
	private IOrdenBDao ordenBDao;
	
	@Override
	public List<OrdenB> findAll() {
		return (List<OrdenB>) ordenBDao.findAll();
	}

	@Override
	public OrdenB findById(int id) {
		return ordenBDao.findById(id).orElse(null);
	}

	@Override
	public OrdenB save(OrdenB ordenB) {
		return ordenBDao.save(ordenB);
	}

	@Override
	public void delete(int id) {
		ordenBDao.deleteById(id);
	}

	
}

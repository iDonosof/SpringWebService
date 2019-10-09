package siglo21.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import siglo21.springboot.backend.apirest.models.dao.IOrdenHDao;
import siglo21.springboot.backend.apirest.models.entity.OrdenH;

@Service
public class OrdenHServiceImpl implements IOrdenHService {

	@Autowired
	private IOrdenHDao ordenHDao;
	
	@Override
	public List<OrdenH> findAll() {
		return (List<OrdenH>) ordenHDao.findAll();
	}

	@Override
	public OrdenH findById(int id) {
		return ordenHDao.findById(id).orElse(null);
	}

	@Override
	public OrdenH save(OrdenH ordenH) {
		return ordenHDao.save(ordenH);
	}

	@Override
	public void delete(int id) {
		ordenHDao.deleteById(id);
	}

}

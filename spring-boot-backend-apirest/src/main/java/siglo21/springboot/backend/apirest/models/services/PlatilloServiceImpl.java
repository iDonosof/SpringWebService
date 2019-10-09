package siglo21.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import siglo21.springboot.backend.apirest.models.dao.IPlatilloDao;
import siglo21.springboot.backend.apirest.models.entity.Platillo;

@Service
public class PlatilloServiceImpl implements IPlatilloService {

	@Autowired
	private IPlatilloDao platilloDao;
	
	@Override
	public List<Platillo> findAll() {
		return (List<Platillo>) platilloDao.findAll();
	}

	@Override
	public Platillo findById(int id) {
		return platilloDao.findById(id).orElse(null);
	}

	@Override
	public Platillo save(Platillo platillo) {
		return platilloDao.save(platillo);
	}

	@Override
	public void delete(int id) {
		platilloDao.deleteById(id);
	}

	
}

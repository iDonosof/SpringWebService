package siglo21.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import siglo21.springboot.backend.apirest.models.dao.IOrdenHDao;
import siglo21.springboot.backend.apirest.models.entity.OrdenB;
import siglo21.springboot.backend.apirest.models.entity.OrdenH;

@Service
public class OrdenHServiceImpl implements IOrdenHService {

	@Autowired
	private IOrdenHDao ordenHDao;

	@Override
	public List<OrdenH> findAll() {
		return RemoverIngredientes((List<OrdenH>) ordenHDao.findAll());
	}

	@Override
	public OrdenH findById(int id) {
		return RemoverIngredientes(ordenHDao.findById(id).orElse(null));
	}

	@Override
	public OrdenH save(OrdenH ordenH) {
		return RemoverIngredientes(ordenHDao.save(ordenH));
	}

	@Override
	public void delete(int id) {
		ordenHDao.deleteById(id);
	}

	private List<OrdenH> RemoverIngredientes(List<OrdenH> param) {
		for (OrdenH ordenh : param) {
			for (OrdenB ordenb : ordenh.getOrdenBId()) {
				ordenb.getPlatilloId().setIngredienteId(null);
			}
		}
		return param;
	}

	private OrdenH RemoverIngredientes(OrdenH param) {
		if (param != null) {
			for (OrdenB ordenb : param.getOrdenBId()) {
				ordenb.getPlatilloId().setIngredienteId(null);
			}
		}
		return param;
	}

}

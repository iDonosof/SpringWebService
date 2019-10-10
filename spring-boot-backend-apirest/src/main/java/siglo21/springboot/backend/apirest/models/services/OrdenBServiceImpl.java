package siglo21.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import siglo21.springboot.backend.apirest.models.dao.IOrdenBDao;
import siglo21.springboot.backend.apirest.models.entity.OrdenB;
import siglo21.springboot.backend.apirest.models.entity.OrdenH;

@Service
public class OrdenBServiceImpl implements IOrdenBService {

	@Autowired
	private IOrdenBDao ordenBDao;

	@Override
	public List<OrdenB> findAll() {
		return RemoverIngredientes((List<OrdenB>) ordenBDao.findAll());
	}

	@Override
	public OrdenB findById(int id) {
		return RemoverIngredientes(ordenBDao.findById(id).orElse(null));
	}

	@Override
	public OrdenB save(OrdenB ordenB) {
		return RemoverIngredientes(ordenBDao.save(ordenB));
	}

	@Override
	public void delete(int id) {
		ordenBDao.deleteById(id);
	}

	private List<OrdenB> RemoverIngredientes(List<OrdenB> param) {
		for (OrdenB ordenb : param) {
			ordenb.getPlatilloId().setIngredienteId(null);
		}
		return param;
	}

	private OrdenB RemoverIngredientes(OrdenB param) {
		if (param != null) {
			param.getPlatilloId().setIngredienteId(null);
		}
		return param;
	}
}

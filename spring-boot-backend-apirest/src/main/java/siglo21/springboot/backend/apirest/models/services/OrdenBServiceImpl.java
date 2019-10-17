package siglo21.springboot.backend.apirest.models.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import siglo21.springboot.backend.apirest.models.dao.IOrdenBDao;
import siglo21.springboot.backend.apirest.models.dao.IPlatilloDao;
import siglo21.springboot.backend.apirest.models.entity.Ingrediente;
import siglo21.springboot.backend.apirest.models.entity.OrdenB;

@Service
public class OrdenBServiceImpl implements IOrdenBService {

	@Autowired
	private IOrdenBDao ordenBDao;
	
	@Autowired
	private IPlatilloDao platilloDao;
	

	@Override
	@Transactional(readOnly = true)
	public List<OrdenB> findAll() {
		return RemoverIngredientes((List<OrdenB>) ordenBDao.findAll());
	}

	@Override
	@Transactional(readOnly = true)
	public OrdenB findById(int id) {
		return RemoverIngredientes(ordenBDao.findById(id).orElse(null));
	}

	@Override
	@Transactional
	public OrdenB save(OrdenB ordenB) {
		ordenB.setPlatilloId(platilloDao.findById(ordenB.getPlatilloId().getId()).orElse(null));
		return RemoverIngredientes(ordenBDao.save(ordenB));
	}

	@Override
	@Transactional
	public void delete(int id) {
		ordenBDao.deleteById(id);
	}

	private List<OrdenB> RemoverIngredientes(List<OrdenB> param) {
		for (OrdenB ordenb : param) {
			ordenb.getPlatilloId().setIngredienteId(new ArrayList<Ingrediente>());
		}
		return param;
	}

	private OrdenB RemoverIngredientes(OrdenB param) {
		if (param != null) {
			param.getPlatilloId().setIngredienteId(new ArrayList<Ingrediente>());
		}
		return param;
	}
}

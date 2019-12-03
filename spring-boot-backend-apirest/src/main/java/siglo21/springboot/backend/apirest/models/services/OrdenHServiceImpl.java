package siglo21.springboot.backend.apirest.models.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import siglo21.springboot.backend.apirest.models.dao.IMesaDao;
import siglo21.springboot.backend.apirest.models.dao.IOrdenBDao;
import siglo21.springboot.backend.apirest.models.dao.IOrdenHDao;
import siglo21.springboot.backend.apirest.models.dao.IPlatilloDao;
import siglo21.springboot.backend.apirest.models.entity.Ingrediente;
import siglo21.springboot.backend.apirest.models.entity.OrdenB;
import siglo21.springboot.backend.apirest.models.entity.OrdenH;

@Service
public class OrdenHServiceImpl implements IOrdenHService {

	@Autowired
	private IOrdenHDao ordenHDao;

	@Autowired
	private IOrdenBDao ordenBDao;
	
	@Autowired
	private IPlatilloDao platilloDao;
	
	@Autowired 
	private IMesaDao mesaDao;

	@Override
	@Transactional(readOnly = true)
	public List<OrdenH> findAll() {
		return RemoverIngredientes((List<OrdenH>) ordenHDao.findAll());
	}

	@Override
	@Transactional(readOnly = true)
	public OrdenH findById(int id) {
		return RemoverIngredientes(ordenHDao.findById(id).orElse(null));
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public OrdenH save(OrdenH ordenH) {
		return ordenH.getId() != 0 ? ordenHDao.save(ordenH) : AgregarOrden(ordenH);
	}

	@Override
	@Transactional
	public void delete(int id) {
		ordenHDao.deleteById(id);
	}
	
	@Override
	@Transactional
	public OrdenH changeStatusPaid(int id) {
		OrdenH ordenHTemp = ordenHDao.findById(id).orElse(null);
		if(ordenHTemp != null) {
			ordenHTemp.setEstado(2);
			return ordenHDao.save(ordenHTemp);
		}
		return ordenHTemp;
	}

	private List<OrdenH> RemoverIngredientes(List<OrdenH> param) {
		for (OrdenH ordenh : param) {
			for (OrdenB ordenb : ordenh.getOrdenBId()) {
				ordenb.getPlatilloId().setIngredienteId(new ArrayList<Ingrediente>());
			}
		}
		return param;
	}

	private OrdenH RemoverIngredientes(OrdenH param) {
		if (param != null) {
			for (OrdenB ordenb : param.getOrdenBId()) {
				ordenb.getPlatilloId().setIngredienteId(new ArrayList<Ingrediente>());
			}
		}
		return param;
	}

	private OrdenH AgregarOrden(OrdenH ordenH) {
		OrdenH ordenHTemp = new OrdenH();
		ordenHTemp.setDocumentoId(ordenH.getDocumentoId());
		ordenHTemp.setEstado(ordenH.getEstado());
		ordenHTemp.setMesaId(mesaDao.findById(ordenH.getMesaId().getId()).orElse(null));
		ordenHTemp.setOrdenBId(new ArrayList<OrdenB>());
		ordenHTemp.setTotal(ordenH.getTotal());
		ordenHTemp = ordenHDao.save(ordenHTemp);
		for (OrdenB ordenB : ordenH.getOrdenBId()) {
			OrdenB ob = new OrdenB();
			ob.setCantidad(ordenB.getCantidad());
			ob.setSubtotal(ordenB.getSubtotal());
			ob.setPlatilloId(platilloDao.findById(ordenB.getPlatilloId().getId()).orElse(null));
			ob.getPlatilloId().setIngredienteId(new ArrayList<Ingrediente>());
			ob.setOrdenHId(ordenHTemp.getId());
			ordenHTemp.getOrdenBId().add(ordenBDao.save(ob));
		}
		return ordenHTemp;
	}
}

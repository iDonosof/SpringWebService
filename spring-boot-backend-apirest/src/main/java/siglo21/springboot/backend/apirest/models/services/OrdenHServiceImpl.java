package siglo21.springboot.backend.apirest.models.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import siglo21.springboot.backend.apirest.models.dao.IOrdenBDao;
import siglo21.springboot.backend.apirest.models.dao.IOrdenHDao;
import siglo21.springboot.backend.apirest.models.entity.OrdenB;
import siglo21.springboot.backend.apirest.models.entity.OrdenH;

@Service
public class OrdenHServiceImpl implements IOrdenHService {

	@Autowired
	private IOrdenHDao ordenHDao;

	@Autowired
	private IOrdenBDao ordenBDao;

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
		OrdenH orden = new OrdenH();
		orden.setTotal(ordenH.getTotal());
		orden.setEstado(ordenH.getEstado());
		orden.setDocumentoId(ordenH.getDocumentoId());
		orden.setOrdenBId(new ArrayList<OrdenB>());
		orden.setMesaId(ordenH.getMesaId());
		OrdenH ordenHTemp = ordenHDao.save(orden);
		if (ordenH.getOrdenBId().size() != 0)
			AgregarOrden(ordenH, ordenHTemp.getId());
		return RemoverIngredientes(ordenHDao.save(ordenH));
	}

	@Override
	public void delete(int id) {
		ordenHDao.deleteById(id);
	}
	
	@Override
	public OrdenH changeStatus(int id) {
		try {
			OrdenH ordenHTemp = ordenHDao.findById(id).orElse(null);
			if(ordenHTemp != null) {
				ordenHTemp.setEstado(2);
				return ordenHDao.save(ordenHTemp);
			}
		} catch (Exception e) {
			
		}
		return null;
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

	private boolean AgregarOrden(OrdenH orden, int idOrdenH) {
		try {
			for (OrdenB ordenB : orden.getOrdenBId()) {
				OrdenB ob = new OrdenB();
				ob.setCantidad(ordenB.getCantidad());
				ob.setSubtotal(ordenB.getSubtotal());
				ob.setPlatilloId(ordenB.getPlatilloId());
				ob.setOrdenHId(idOrdenH);
				ordenBDao.save(ob);
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	

}

package siglo21.springboot.backend.apirest.models.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import siglo21.springboot.backend.apirest.models.dao.IIngredienteDao;
import siglo21.springboot.backend.apirest.models.dao.IPlatilloDao;
import siglo21.springboot.backend.apirest.models.entity.Ingrediente;
import siglo21.springboot.backend.apirest.models.entity.Platillo;

@Service
public class PlatilloServiceImpl implements IPlatilloService {

	@Autowired
	private IPlatilloDao platilloDao;
	
	@Autowired
	private IIngredienteDao ingredienteDao;
	
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
		try {
			AgregarIngredientes(platillo);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public void delete(int id) {
		platilloDao.deleteById(id);
	}

	private Platillo AgregarIngredientes(Platillo platillo) {
		try {
			Platillo platilloTemp = new Platillo();
			platilloTemp.setIngredienteId(new ArrayList<Ingrediente>());
			platilloTemp.setNombre(platillo.getNombre());
			platilloTemp.setTiempo(platillo.getTiempo());
			platilloTemp = platilloDao.save(platilloTemp);
			if(platilloTemp.getId() != 0) {
				for(Ingrediente i : platillo.getIngredienteId()) {
					Ingrediente ingrediente = new Ingrediente();
					ingrediente.setCantidad(i.getCantidad());
					ingrediente.setPlatilloId(platilloTemp.getId());
					ingrediente.setProductoId(i.getProductoId());
					platilloTemp.getIngredienteId().add(ingredienteDao.save(ingrediente));
				}
			}
			return platilloTemp;
		} catch (Exception e) {
		}
		return null;
	}
}

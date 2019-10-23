package siglo21.springboot.backend.apirest.models.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import siglo21.springboot.backend.apirest.models.dao.IIngredienteDao;
import siglo21.springboot.backend.apirest.models.dao.IPlatilloDao;
import siglo21.springboot.backend.apirest.models.dao.IProductoDao;
import siglo21.springboot.backend.apirest.models.entity.Ingrediente;
import siglo21.springboot.backend.apirest.models.entity.Platillo;

@Service
public class PlatilloServiceImpl implements IPlatilloService {

	@Autowired
	private IPlatilloDao platilloDao;
	
	@Autowired
	private IIngredienteDao ingredienteDao;
	
	@Autowired
	private IProductoDao productoDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Platillo> findAll() {
		return (List<Platillo>) platilloDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Platillo findById(int id) {
		return platilloDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Platillo save(Platillo platillo, boolean saveOption) {
		//saveOption equivale a la opcion si quiere actualizar o ingresar un nuevo producto
		//La opcion "true" equivale a que el platillo ya existe y se dea actualizar
		//Si la opcion es "false" es porque se desea ingresar un nuevo producto
		try {
			if(saveOption) {
				return platilloDao.save(platillo);
			} 
			else {
				return AgregarIngredientes(platillo);				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	@Transactional
	public void delete(int id) {
		platilloDao.deleteById(id);
	}

	private Platillo AgregarIngredientes(Platillo platillo) {
		try {
			Platillo platilloTemp = new Platillo();
			if(!platilloDao.existsByNombre(platillo.getNombre()))
			{
				platilloTemp.setIngredienteId(new ArrayList<Ingrediente>());
				platilloTemp.setNombre(platillo.getNombre());
				platilloTemp.setTiempo(platillo.getTiempo());
				platilloTemp.setPrecio(platillo.getPrecio());
				platilloTemp = platilloDao.save(platilloTemp);				
			}
			else {
				platilloTemp = platilloDao.findByNombre(platillo.getNombre());
			}
			if(platilloTemp.getId() != 0 && platilloTemp != null) {
				for(Ingrediente i : platillo.getIngredienteId()) {
					Ingrediente ingrediente = new Ingrediente();
					ingrediente.setCantidad(i.getCantidad());
					ingrediente.setPlatilloId(platilloTemp.getId());
					ingrediente.setProductoId(productoDao.findById(i.getProductoId().getId()).orElse(null));
					ingrediente = ingredienteDao.save(ingrediente);
					platilloTemp.getIngredienteId().add(ingrediente);
				}
			}
			return platilloTemp;
		} catch (Exception e) {
			System.out.println("Esta wea se cae por: " + e.getMessage());
		}
		return null;
	}
}

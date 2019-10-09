package siglo21.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import siglo21.springboot.backend.apirest.models.dao.IIngredienteDao;
import siglo21.springboot.backend.apirest.models.entity.Ingrediente;

@Service
public class IngredienteServiceImpl implements IIngredienteService{
	@Autowired
	private IIngredienteDao ingredienteDao;

	@Override
	@Transactional(readOnly = true)
	public List<Ingrediente> findAll() {
		return (List<Ingrediente>) ingredienteDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Ingrediente findById(int id) {
		return ingredienteDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Ingrediente save(Ingrediente ingrediente) {
		return ingredienteDao.save(ingrediente);
	}

	@Override
	@Transactional
	public void delete(int id) {
		ingredienteDao.deleteById(id);
	}
}

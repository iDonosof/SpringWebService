package siglo21.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import siglo21.springboot.backend.apirest.models.dao.IProveedorDao;
import siglo21.springboot.backend.apirest.models.entity.Proveedor;

@Service
public class ProveedorServiceImpl implements IProveedorService {
	
	@Autowired
	private IProveedorDao proveedorDao;

	@Override
	@Transactional(readOnly = true)
	public List<Proveedor> findAll() {
		return (List<Proveedor>) proveedorDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Proveedor findById(String id) {
		return proveedorDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Proveedor save(Proveedor proveedor) {
		return proveedorDao.save(proveedor);
	}

	@Override
	@Transactional
	public void delete(String id) {
		proveedorDao.deleteById(id);
	}
}

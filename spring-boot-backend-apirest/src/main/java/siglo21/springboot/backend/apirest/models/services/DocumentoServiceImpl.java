package siglo21.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import siglo21.springboot.backend.apirest.models.dao.IDocumentoDao;
import siglo21.springboot.backend.apirest.models.entity.Documento;

@Service
public class DocumentoServiceImpl implements IDocumentoService {

	@Autowired
	private IDocumentoDao documentoDao;
	
	@Override
	public List<Documento> findAll() {
		return (List<Documento>) documentoDao.findAll();
	}

	@Override
	public Documento findById(int id) {
		return documentoDao.findById(id).orElse(null);
	}

	@Override
	public Documento save(Documento documento) {
		return documentoDao.save(documento);
	}

	@Override
	public void delete(int id) {
		documentoDao.deleteById(id);
	}
	
}

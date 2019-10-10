package siglo21.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import siglo21.springboot.backend.apirest.models.dao.IDocumentoDao;
import siglo21.springboot.backend.apirest.models.entity.Documento;
import siglo21.springboot.backend.apirest.models.entity.OrdenB;
import siglo21.springboot.backend.apirest.models.entity.OrdenH;

@Service
public class DocumentoServiceImpl implements IDocumentoService {

	@Autowired
	private IDocumentoDao documentoDao;
	
	@Override
	public List<Documento> findAll() {
		return RemoverIngredientes((List<Documento>) documentoDao.findAll());
	}

	@Override
	public Documento findById(int id) {
		return RemoverIngredientes(documentoDao.findById(id).orElse(null));
	}

	@Override
	public Documento save(Documento documento) {
		return RemoverIngredientes(documentoDao.save(documento));
	}

	@Override
	public void delete(int id) {
		documentoDao.deleteById(id);
	}
	
	private List<Documento> RemoverIngredientes(List<Documento> param) {
		for(Documento documento : param) {
			for(OrdenH ordenh : documento.getOrdenHId()) {
				for(OrdenB ordenb : ordenh.getOrdenBId()) {
					ordenb.getPlatilloId().setIngredienteId(null);;
				}
			}
		}
		return param;
	}
	
	private Documento RemoverIngredientes(Documento param) {
		if(param != null)
		{
			for(OrdenH ordenh : param.getOrdenHId()) {
				for(OrdenB ordenb : ordenh.getOrdenBId()) {
					ordenb.setPlatilloId(null);
				}
			}
		}
		return param;
	}
	
}

package siglo21.springboot.backend.apirest.models.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import siglo21.springboot.backend.apirest.models.dao.IDocumentoDao;
import siglo21.springboot.backend.apirest.models.dao.IOrdenBDao;
import siglo21.springboot.backend.apirest.models.dao.IOrdenHDao;
import siglo21.springboot.backend.apirest.models.dao.IPedidoBDao;
import siglo21.springboot.backend.apirest.models.dao.IPedidoHDao;
import siglo21.springboot.backend.apirest.models.entity.Documento;
import siglo21.springboot.backend.apirest.models.entity.OrdenB;
import siglo21.springboot.backend.apirest.models.entity.OrdenH;
import siglo21.springboot.backend.apirest.models.entity.PedidoB;
import siglo21.springboot.backend.apirest.models.entity.PedidoH;

@Service
public class DocumentoServiceImpl implements IDocumentoService {

	@Autowired
	private IDocumentoDao documentoDao;

	@Autowired
	private IOrdenHDao ordenHDao;

	@Autowired
	private IOrdenBDao ordenBDao;
	
	@Autowired
	private IPedidoHDao pedidoHDao;
	
	@Autowired
	private IPedidoBDao pedidoBDao;

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
		return AgregarDocumento(documento) ?  documento : null;
	}

	@Override
	public void delete(int id) {
		documentoDao.deleteById(id);
	}

	private List<Documento> RemoverIngredientes(List<Documento> param) {
		for (Documento documento : param) {
			for (OrdenH ordenh : documento.getOrdenHId()) {
				for (OrdenB ordenb : ordenh.getOrdenBId()) {
					ordenb.getPlatilloId().setIngredienteId(null);
					;
				}
			}
		}
		return param;
	}

	private Documento RemoverIngredientes(Documento param) {
		if (param != null) {
			for (OrdenH ordenh : param.getOrdenHId()) {
				for (OrdenB ordenb : ordenh.getOrdenBId()) {
					ordenb.setPlatilloId(null);
				}
			}
		}
		return param;
	}

	private boolean AgregarDocumento(Documento documento) {
		try {
			Documento documentoTemp = new Documento();
			documentoTemp.setId(documento.getId());
			documentoTemp.setFecha(documento.getFecha());
			documentoTemp.setHora(documento.getHora());
			documentoTemp.setTipo(documento.getTipo());
			documentoTemp.setOrdenHId(new ArrayList<OrdenH>());
			documentoTemp.setPedidoH(new ArrayList<PedidoH>());
			documentoDao.save(documentoTemp);
			if(documento.getOrdenHId().size() != 0 ? AgregarOrden(documento.getOrdenHId(), documento.getId()) : 
				documento.getPedidoH().size() != 0 ? AgregarPedido(documento.getPedidoH(), documento.getId()) : 
				false) {
				return true;				
			}
		} catch (Exception e) {
		}
		return false;
	}

	private boolean AgregarOrden(List<OrdenH> orden, int idDocumento) {
		try {
			for(OrdenH ordenH : orden) {
				OrdenH oh = new OrdenH();
				oh.setTotal(ordenH.getTotal());
				oh.setEstado(ordenH.getEstado());
				oh.setDocumentoId(idDocumento);
				oh.setMesaId(ordenH.getMesaId());
				OrdenH ordenHTemp = ordenHDao.save(oh);
				for(OrdenB ordenB : ordenH.getOrdenBId()) {
					OrdenB ob = new OrdenB();
					ob.setCantidad(ordenB.getCantidad());
					ob.setSubtotal(ordenB.getSubtotal());
					ob.setPlatilloId(ordenB.getPlatilloId());
					ob.setOrdenHId(ordenHTemp.getId());
					ordenBDao.save(ob);
				}
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	private boolean AgregarPedido(List<PedidoH> pedido, int idDocumento) {
		try {
			for(PedidoH pedidoH : pedido) {
				PedidoH ph  =new PedidoH();
				ph.setTotal(pedidoH.getTotal());
				ph.setEstado(pedidoH.getEstado());
				ph.setDocumentoId(idDocumento);
				ph.setProveedor(pedidoH.getProveedor());
				PedidoH pedidoHTemp = pedidoHDao.save(ph);
				for(PedidoB pedidoB : pedidoH.getPedidoBId()) {
					PedidoB pb = new PedidoB();
					pb.setCantidad(pedidoB.getCantidad());
					pb.setSubtotal(pedidoB.getSubtotal());
					pb.setPedidoHId(pedidoHTemp.getId());
					pb.setProductoId(pedidoB.getProductoId());
					pedidoBDao.save(pb);
				}
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
}

package siglo21.springboot.backend.apirest.models.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import siglo21.springboot.backend.apirest.models.dao.IDocumentoDao;
import siglo21.springboot.backend.apirest.models.dao.IMesaDao;
import siglo21.springboot.backend.apirest.models.dao.IOrdenBDao;
import siglo21.springboot.backend.apirest.models.dao.IOrdenHDao;
import siglo21.springboot.backend.apirest.models.dao.IPedidoBDao;
import siglo21.springboot.backend.apirest.models.dao.IPedidoHDao;
import siglo21.springboot.backend.apirest.models.dao.IPlatilloDao;
import siglo21.springboot.backend.apirest.models.dao.IProductoDao;
import siglo21.springboot.backend.apirest.models.dao.IProveedorDao;
import siglo21.springboot.backend.apirest.models.entity.Documento;
import siglo21.springboot.backend.apirest.models.entity.Ingrediente;
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
	
	@Autowired
	private IMesaDao mesaDao;
	
	@Autowired
	private IPlatilloDao platilloDao;
	
	@Autowired
	private IProveedorDao proveedorDao;
	
	@Autowired
	private IProductoDao productoDao;

	@Override
	@Transactional(readOnly = true)
	public List<Documento> findAll() {
		return RemoverIngredientes((List<Documento>) documentoDao.findAll());
	}

	@Override
	@Transactional(readOnly = true)
	public Documento findById(int id) {
		return RemoverIngredientes(documentoDao.findById(id).orElse(null));
	}

	@Override
	@Transactional
	public Documento save(Documento documento) {
		return AgregarDocumento(documento) ?  documento : null;
	}

	@Override
	@Transactional
	public void delete(int id) {
		documentoDao.deleteById(id);
	}

	private List<Documento> RemoverIngredientes(List<Documento> param) {
		//Realizo el for para eliminar la lista de ingredientes de los platillos para 
		//Evitar mostrar mas datos de los que se requieren
		//Esta lista de ingredientes es reemplazada por una lista en blanco
		for (Documento documento : param) {
			for (OrdenH ordenh : documento.getOrdenHId()) {
				for (OrdenB ordenb : ordenh.getOrdenBId()) {
					ordenb.getPlatilloId().setIngredienteId(new ArrayList<Ingrediente>());
					;
				}
			}
		}
		return param;
	}

	private Documento RemoverIngredientes(Documento param) {
		//Realizo el for para eliminar la lista de ingredientes de los platillos para 
		//Evitar mostrar mas datos de los que se requieren
		//Esta lista de ingredientes es reemplazada por una lista en blanco
		if (param != null) {
			for (OrdenH ordenh : param.getOrdenHId()) {
				for (OrdenB ordenb : ordenh.getOrdenBId()) {
					ordenb.getPlatilloId().setIngredienteId(new ArrayList<Ingrediente>());
				}
			}
		}
		return param;
	}

	private boolean AgregarDocumento(Documento documento) {
		try {
			/*
			 * Se crea un documento con solo los datos del documento para poder realizar un for que agregue 
			 * las ordenes o los pedidos header en caso de que tenga
			 */
			Documento documentoTemp = new Documento();
			documentoTemp.setFecha(documento.getFecha());
			documentoTemp.setHora(documento.getHora());
			documentoTemp.setTipo(documento.getTipo());
			documentoTemp.setOrdenHId(new ArrayList<OrdenH>());
			documentoTemp.setPedidoH(new ArrayList<PedidoH>());
			documentoTemp = documentoDao.save(documentoTemp);
			//Pregunto si el documento fue ingresado correctamente
			if(documentoTemp != null) {
				//Pregunto si la lista de ordenes tiene un largo distinto a 0 es decir que no este vacia y que no sea nula, si se cumplen ambos casos realiza el metodo de insercion de las ordenes 
				//En el caso de pedido pregunto si tiene un largo distinto a 0 es decir que no este vacia y que no sea nula, si se cumplen ambos casos realiza el metodo de insercion de los pedidos
				if(documento.getOrdenHId().size() != 0 && documento.getOrdenHId() != null && documentoTemp != null? AgregarOrden(documento.getOrdenHId(), documentoTemp.getId()) : 
					documento.getPedidoH().size() != 0 && documento.getPedidoH() != null && documentoTemp != null? AgregarPedido(documento.getPedidoH(), documentoTemp.getId()) : 
					false) {
					return true;				
				}
				else {
					return true;					
				}
			}
		} catch (Exception e) {
			System.out.println("Se esta callendo la wea xd");
		}
		return false;
	}

	private boolean AgregarOrden(List<OrdenH> orden, int idDocumento) {
		try {
			//Realizo un for para insertar el orden header y generar su id
			for(OrdenH ordenH : orden) {
				OrdenH oh = new OrdenH();
				oh.setTotal(ordenH.getTotal());
				oh.setEstado(ordenH.getEstado());
				oh.setDocumentoId(idDocumento);
				oh.setMesaId(mesaDao.findById(ordenH.getMesaId().getId()).orElse(null));
				OrdenH ordenHTemp = ordenHDao.save(oh);
				//Realizo un for para insertar el orden body utilizando la id generada por el orden header
				for(OrdenB ordenB : ordenH.getOrdenBId()) {
					OrdenB ob = new OrdenB();
					ob.setCantidad(ordenB.getCantidad());
					ob.setSubtotal(ordenB.getSubtotal());
					ob.setPlatilloId(platilloDao.findById(ordenB.getPlatilloId().getId()).orElse(null));
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
			//Realizo un for para insertar los pedidos header y generar la id
			for(PedidoH pedidoH : pedido) {
				PedidoH ph = new PedidoH();
				ph.setTotal(pedidoH.getTotal());
				ph.setEstado(pedidoH.getEstado());
				ph.setDocumentoId(idDocumento);
				ph.setProveedor(proveedorDao.findById(pedidoH.getProveedor().getRut()).orElse(null));
				PedidoH pedidoHTemp = pedidoHDao.save(ph);
				//Realizo un for para insertar los pedidos bodies utilizando la id generada del pedido header
				for(PedidoB pedidoB : pedidoH.getPedidoBId()) {
					PedidoB pb = new PedidoB();
					pb.setCantidad(pedidoB.getCantidad());
					pb.setSubtotal(pedidoB.getSubtotal());
					pb.setPedidoHId(pedidoHTemp.getId());
					pb.setProductoId(productoDao.findById(pedidoB.getProductoId().getId()).orElse(null));
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

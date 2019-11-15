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
	@Transactional(rollbackFor = Exception.class)
	public Documento save(Documento documento) {
		return AgregarDocumento(documento);
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

	private Documento AgregarDocumento(Documento documento) {
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
			if(documento.getOrdenHId().size() != 0 && documento.getOrdenHId() != null) {
				documentoTemp.setPedidoH(new ArrayList<PedidoH>());
				documentoTemp = AgregarOrden(documento.getOrdenHId(), documentoTemp);
			}
			else if(documento.getPedidoH().size() != 0 && documento.getPedidoH() != null && documentoTemp != null) {
				documentoTemp.setOrdenHId(new ArrayList<OrdenH>());
				documentoTemp = AgregarPedido(documento.getPedidoH(), documentoTemp);
			}
		}
		return documentoTemp;
	}

	private Documento AgregarOrden(List<OrdenH> ordenes, Documento documento) {
		for(OrdenH orden : ordenes) {
			
			OrdenH oh = new OrdenH();
			//Genero el orden H por separado para poder generar la id necesaria para la insercion de los bodies
			oh.setTotal(orden.getTotal());
			oh.setEstado(orden.getEstado());
			oh.setDocumentoId(documento.getId());
			oh.setMesaId(mesaDao.findById(orden.getMesaId().getId()).orElse(null));
			oh.setOrdenBId(new ArrayList<OrdenB>());
			OrdenH ordenHTemp = ordenHDao.save(oh);
			
			//Realizo un for para insertar el orden body utilizando la id generada por el orden header
			for(OrdenB ordenB : orden.getOrdenBId()) {
				OrdenB ob = new OrdenB();
				ob.setCantidad(ordenB.getCantidad());
				ob.setSubtotal(ordenB.getSubtotal());
				ob.setPlatilloId(platilloDao.findById(ordenB.getPlatilloId().getId()).orElse(null));
				ob.setOrdenHId(ordenHTemp.getId());
			 	ordenHTemp.getOrdenBId().add(ordenBDao.save(ob));
			}
			
			//Agrego el orden H con todos orden B agregados al documento para mostrarlo correctamente
			documento.getOrdenHId().add(ordenHTemp);
		}
		return documento;
	}

	private Documento AgregarPedido(List<PedidoH> pedidos, Documento documento) {
		for(PedidoH pedido : pedidos) {
			
			//Realizo un for para insertar los pedidos header y generar la id
			PedidoH ph = new PedidoH();
			ph.setTotal(pedido.getTotal());
			ph.setEstado(pedido.getEstado());
			ph.setDocumentoId(documento.getId());
			ph.setProveedor(proveedorDao.findById(pedido.getProveedor().getRut()).orElse(null));
			ph.setPedidoBId(new ArrayList<PedidoB>());
			PedidoH pedidoHTemp = pedidoHDao.save(ph);
			
			//Realizo un for para insertar los pedidos bodies utilizando la id generada del pedido header
			for(PedidoB pedidoB : pedido.getPedidoBId()) {
				PedidoB pb = new PedidoB();
				pb.setCantidad(pedidoB.getCantidad());
				pb.setSubtotal(pedidoB.getSubtotal());
				pb.setPedidoHId(pedidoHTemp.getId());
				pb.setProductoId(productoDao.findById(pedidoB.getProductoId().getId()).orElse(null));
				pedidoHTemp.getPedidoBId().add(pedidoBDao.save(pb));
			}
			
			//Agrego al pedido H con todos sus pedidos B agregados al documento para mostrarlo correctamente
			documento.getPedidoH().add(pedidoHTemp);
		}
		return documento;
	}
}

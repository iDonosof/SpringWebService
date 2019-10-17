package siglo21.springboot.backend.apirest.models.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import siglo21.springboot.backend.apirest.models.dao.IPedidoBDao;
import siglo21.springboot.backend.apirest.models.dao.IPedidoHDao;
import siglo21.springboot.backend.apirest.models.dao.IProductoDao;
import siglo21.springboot.backend.apirest.models.dao.IProveedorDao;
import siglo21.springboot.backend.apirest.models.entity.PedidoB;
import siglo21.springboot.backend.apirest.models.entity.PedidoH;
import siglo21.springboot.backend.apirest.models.entity.Producto;

@Service
public class PedidoHServiceImpl implements IPedidoHService {
	
	@Autowired
	private IPedidoHDao pedidoHDao;
	
	@Autowired
	private IPedidoBDao pedidoBDao;
	
	@Autowired
	private IProductoDao productoDao;
	
	@Autowired
	private IProveedorDao proveedorDao;

	@Override
	@Transactional(readOnly = true)
	public List<PedidoH> findAll() {
		return (List<PedidoH>) pedidoHDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public PedidoH findById(int id) {
		return pedidoHDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public PedidoH save(PedidoH pedidoH) {
		PedidoH pedido = new PedidoH();
		pedido.setTotal(pedidoH.getTotal());
		pedido.setEstado(pedidoH.getEstado());
		pedido.setDocumentoId(pedidoH.getDocumentoId());
		pedido.setProveedor(proveedorDao.findById(pedido.getProveedor().getRut()).orElse(null));
		pedido.setPedidoBId(new ArrayList<PedidoB>());
		PedidoH pedidoHTemp = pedidoHDao.save(pedido);
		if(pedidoH.getPedidoBId().size()!= 0 && pedidoHTemp != null)
			AgregarPedido(pedidoH, pedidoHTemp.getId());
		return pedidoHDao.save(pedidoH);
	}

	@Override
	@Transactional
	public void delete(int id) {
		pedidoHDao.deleteById(id);
	}
	
	@Override
	@Transactional
	public PedidoH changeStatus(int id) {
		try {
			PedidoH pedidoHTemp = pedidoHDao.findById(id).orElse(null);
			if(pedidoHTemp != null) {
				pedidoHTemp.setEstado(2);
				for(PedidoB pedidob : pedidoHTemp.getPedidoBId()) {
					ActualizarStock(pedidob.getProductoId().getId(), pedidob.getCantidad());
				}
				return pedidoHDao.save(pedidoHTemp);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	private boolean AgregarPedido(PedidoH pedidoH, int idPedidoH) {
		try {
			for(PedidoB pedidoB : pedidoH.getPedidoBId()) {
				PedidoB pb = new PedidoB();
				pb.setCantidad(pedidoB.getCantidad());
				pb.setSubtotal(pedidoB.getSubtotal());
				pb.setPedidoHId(idPedidoH);
				pb.setProductoId(productoDao.findById(pedidoB.getProductoId().getId()).orElse(null));
				pedidoBDao.save(pb);
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	
	private boolean ActualizarStock(int id, int cantidad) {
		try {
			Producto producto = productoDao.findById(id).orElse(null);
			if(producto != null) {
				producto.setCantidad(producto.getCantidad() + cantidad);
				if(productoDao.save(producto) != null)
					return true;
			}
			throw new Exception();
		} catch (Exception e) {
			
		}
		return false;
	}
}

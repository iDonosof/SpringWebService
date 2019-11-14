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
	@Transactional(rollbackFor = Exception.class)
	public PedidoH save(PedidoH pedidoH) {
		return pedidoH.getId() != 0 ? pedidoHDao.save(pedidoH) : AgregarPedido(pedidoH);
	}

	@Override
	@Transactional
	public void delete(int id) {
		pedidoHDao.deleteById(id);
	}
	
	@Override
	@Transactional
	public PedidoH changeStatus(int id) {
		PedidoH pedidoHTemp = pedidoHDao.findById(id).orElse(null);
		if(pedidoHTemp != null) {
			pedidoHTemp.setEstado(2);
			for(PedidoB pedidob : pedidoHTemp.getPedidoBId()) {
				ActualizarStock(pedidob.getProductoId().getId(), pedidob.getCantidad());
			}
			return pedidoHDao.save(pedidoHTemp);
		}
		return pedidoHTemp;
	}
	
	private PedidoH AgregarPedido(PedidoH pedidoH) {
		PedidoH pedidoHTemp = new PedidoH();
		pedidoHTemp.setDocumentoId(pedidoH.getDocumentoId());
		pedidoHTemp.setEstado(pedidoH.getEstado());
		pedidoHTemp.setProveedor(proveedorDao.findById(pedidoH.getProveedor().getRut()).orElse(null));
		pedidoHTemp.setTotal(pedidoH.getTotal());
		pedidoHTemp.setPedidoBId(new ArrayList<PedidoB>());
		pedidoHTemp = pedidoHDao.save(pedidoHTemp);
		if(pedidoH.getPedidoBId().size() != 0 && pedidoH.getPedidoBId() != null) {
			for(PedidoB pedidoB : pedidoH.getPedidoBId()) {
				PedidoB pb = new PedidoB();
				pb.setCantidad(pedidoB.getCantidad());
				pb.setPedidoHId(pedidoHTemp.getId());
				pb.setProductoId(productoDao.findById(pedidoB.getProductoId().getId()).orElse(null));
				pb.setSubtotal(pedidoB.getSubtotal());
				pedidoHTemp.getPedidoBId().add(pedidoBDao.save(pb));
			}
		}
		return pedidoHTemp;
	}
	
	private boolean ActualizarStock(int id, int cantidad) {
		Producto producto = productoDao.findById(id).orElse(null);
		if(producto != null) {
			producto.setCantidad(producto.getCantidad() + cantidad);
			if(productoDao.save(producto) != null)
				return true;
		}
		return false;
	}
}

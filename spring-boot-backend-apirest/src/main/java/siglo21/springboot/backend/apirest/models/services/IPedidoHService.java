package siglo21.springboot.backend.apirest.models.services;

import java.util.List;

import siglo21.springboot.backend.apirest.models.entity.PedidoH;

public interface IPedidoHService {
	public List<PedidoH> findAll();

	public PedidoH findById(int id);

	public PedidoH save(PedidoH pedidoH);

	public void delete(int id);
}

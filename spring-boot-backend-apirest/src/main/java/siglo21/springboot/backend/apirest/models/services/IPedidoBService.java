package siglo21.springboot.backend.apirest.models.services;

import java.util.List;

import siglo21.springboot.backend.apirest.models.entity.PedidoB;

public interface IPedidoBService {

	public List<PedidoB> findAll();

	public PedidoB findById(int id);

	public PedidoB save(PedidoB pedidoB);

	public void delete(int id);
}

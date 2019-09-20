package com.siglo21.springboot.backend.apirest.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PEDIDO_B")
public class PedidoB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private int id;
	
	@Column(name = "CANTIDAD")
	private int cantidad;
	
	@Column(name = "SUBTOTAL")
	private int subtotal;
	
	@Column(name = "PEDIDO_H_ID")
	/*@ManyToOne
	@JoinColumn(name = "ID")*/
	private int pedidoHId;
	
	@Column(name = "PRODUCTO_ID")
	/*@ManyToOne
	@JoinColumn(name = "ID")*/
	private int productoId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(int subtotal) {
		this.subtotal = subtotal;
	}

	public int getPedidoHId() {
		return pedidoHId;
	}

	public void setPedidoHId(int pedidoHId) {
		this.pedidoHId = pedidoHId;
	}

	public int getProductoId() {
		return productoId;
	}

	public void setProductoId(int productoId) {
		this.productoId = productoId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

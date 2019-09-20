package com.siglo21.springboot.backend.apirest.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "ORDEN_B")
public class OrdenB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private int id;
	
	@Column(name = "CANTIDAD")
	private int cantidad;
	
	@Column(name = "SUBTOTAL")
	private int subtotal;
	
	@Column(name = "ORDEN_H_ID")
	/*@ManyToOne
	@JoinColumn(name = "ID")*/
	private int ordenHId;
	
	@Column(name = "PLATILLO_ID")
	/*@ManyToOne
	@JoinColumn(name = "ID")*/
	private int platilloId;

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

	public int getOrdenHId() {
		return ordenHId;
	}

	public void setOrdenHId(int ordenHId) {
		this.ordenHId = ordenHId;
	}

	public int getPlatilloId() {
		return platilloId;
	}

	public void setPlatilloId(int platilloId) {
		this.platilloId = platilloId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

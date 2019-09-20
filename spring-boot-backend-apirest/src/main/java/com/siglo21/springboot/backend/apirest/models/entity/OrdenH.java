package com.siglo21.springboot.backend.apirest.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ORDEN_H")
public class OrdenH implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private int id;
	
	@Column(name = "TOTAL")
	private int total;
	
	@Column(name = "ESTADO")
	private int estado;
	
	@Column(name = "DOCUMENTO_ID")
	/*@ManyToOne
	@JoinColumn(name = "ID")*/
	private int documentoId;
	
	@Column(name = "MESA_ID")
	/*@ManyToOne
	@JoinColumn(name = "ID")*/
	private int mesaId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public int getDocumentoId() {
		return documentoId;
	}

	public void setDocumentoId(int documentoId) {
		this.documentoId = documentoId;
	}

	public int getMesaId() {
		return mesaId;
	}

	public void setMesaId(int mesaId) {
		this.mesaId = mesaId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

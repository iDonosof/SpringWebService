package siglo21.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "ORDEN_H")
public class OrdenH implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@Column(name = "TOTAL", nullable = false)
	private int total;

	@Column(name = "ESTADO", nullable = false)
	private int estado;

	@Column(name = "DOCUMENTO_ID", nullable = false)
	private int documentoId;

	@OneToMany(mappedBy = "ordenHId", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<OrdenB> ordenBId;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "MESA_ID", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Mesa mesaId;

	public List<OrdenB> getOrdenBId() {
		return ordenBId;
	}

	public void setOrdenBId(List<OrdenB> ordenBId) {
		this.ordenBId = ordenBId;
	}

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

	public Mesa getMesaId() {
		return mesaId;
	}

	public void setMesaId(Mesa mesaId) {
		this.mesaId = mesaId;
	}

	public int getDocumentoId() {
		return documentoId;
	}

	public void setDocumentoId(int documentoId) {
		this.documentoId = documentoId;
	}

}

package siglo21.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "DOCUMENTO")
public class Documento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@Column(name = "FECHA", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Column(name = "HORA", length = 5, nullable = false)
	private String hora;

	@Column(name = "TIPO", nullable = true)
	private int tipo;

	@OneToMany(mappedBy = "documentoId", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<OrdenH> ordenHId;

	@OneToMany(mappedBy = "documentoId", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<PedidoH> pedidoH;

	public List<OrdenH> getOrdenHId() {
		return ordenHId;
	}

	public void setOrdenHId(List<OrdenH> ordenHId) {
		this.ordenHId = ordenHId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public List<PedidoH> getPedidoH() {
		return pedidoH;
	}

	public void setPedidoH(List<PedidoH> pedidoH) {
		this.pedidoH = pedidoH;
	}

}

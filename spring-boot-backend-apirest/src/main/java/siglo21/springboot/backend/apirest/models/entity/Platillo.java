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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PLATILLO")
public class Platillo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "NOMBRE", length = 70, nullable = false)
	private String nombre;

	@Column(name = "TIEMPO", nullable = false)
	private int tiempo;

	@Column(name = "PRECIO", nullable = true)
	private int precio;

	@OneToMany(mappedBy = "platilloId", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<Ingrediente> ingredienteId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getTiempo() {
		return tiempo;
	}

	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}

	public List<Ingrediente> getIngredienteId() {
		return ingredienteId;
	}

	public void setIngredienteId(List<Ingrediente> ingredienteId) {
		this.ingredienteId = ingredienteId;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

}

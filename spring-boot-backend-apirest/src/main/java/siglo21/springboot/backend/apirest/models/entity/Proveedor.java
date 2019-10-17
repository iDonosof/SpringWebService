package siglo21.springboot.backend.apirest.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PROVEEDOR")
public class Proveedor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "RUT")
	private String rut;

	@Column(name = "NOMBRE", length = 70, nullable = false)
	private String nombre;

	@Column(name = "TELEFONO", length = 15, nullable = false)
	private String telefono;

	@Column(name = "DIRECCION", length = 255, nullable = false)
	private String direccion;

	@Column(name = "CORREO", length = 255, nullable = false)
	private String correo;

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

}

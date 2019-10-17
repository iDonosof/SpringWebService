package siglo21.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "USUARIO")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "RUT")
	private int rut;

	@Column(name = "DV")
	private char dv;

	@Column(name = "USERNAME", length = 70, nullable = false)
	private String username;

	@Column(name = "PASSWORD", length = 25, nullable = false)
	private String password;

	@Column(name = "NOMBRE", length = 70, nullable = false)
	private String nombre;

	@Column(name = "APELLIDO", length = 70, nullable = false)
	private String apellido;

	@Column(name = "ROL", nullable = false)
	private int rol;

	@Column(name = "CORREO", length = 255, nullable = false)
	private String correo;

	@Column(name = "FECHA_NACIMIENTO", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;

	public int getRut() {
		return rut;
	}

	public void setRut(int rut) {
		this.rut = rut;
	}

	public char getDv() {
		return dv;
	}

	public void setDv(char dv) {
		this.dv = dv;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getRol() {
		return rol;
	}

	public void setRol(int rol) {
		this.rol = rol;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

}

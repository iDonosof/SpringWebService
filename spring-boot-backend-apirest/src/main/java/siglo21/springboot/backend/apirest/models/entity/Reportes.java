package siglo21.springboot.backend.apirest.models.entity;

import java.io.Serializable;

import javax.persistence.Column;

public class Reportes implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "INGRESO_MENSUAL")
	private int ingresoMensual;

	@Column(name = "MES_MAYOR_INGRESO")
	private int mesMayorIngreso;

	public int getIngresoMensual() {
		return ingresoMensual;
	}

	public void setIngresoMensual(int ingresoMensual) {
		this.ingresoMensual = ingresoMensual;
	}

	public int getMesMayorIngreso() {
		return mesMayorIngreso;
	}

	public void setMesMayorIngreso(int mesMayorIngreso) {
		this.mesMayorIngreso = mesMayorIngreso;
	}

}

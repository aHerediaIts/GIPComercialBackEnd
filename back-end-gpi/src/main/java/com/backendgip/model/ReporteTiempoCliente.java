//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.model;

public class ReporteTiempoCliente {
	private String cliente;
	private String proyecto;
	private String fechaInicio;
	private String fechaFin;
	private Integer horasCotizadas;
	private Integer horasConsumidas;
	private Integer porcentajeAvance;
	private Proyecto proyectoB;

	public ReporteTiempoCliente() {
	}

	public String getCliente() {
		return this.cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getProyecto() {
		return this.proyecto;
	}

	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}

	public String getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Integer getHorasCotizadas() {
		return this.horasCotizadas;
	}

	public void setHorasCotizadas(Integer horasCotizadas) {
		this.horasCotizadas = horasCotizadas;
	}

	public Integer getHorasConsumidas() {
		return this.horasConsumidas;
	}

	public void setHorasConsumidas(Integer horasConsumidas) {
		this.horasConsumidas = horasConsumidas;
	}

	public Integer getPorcentajeAvance() {
		return this.porcentajeAvance;
	}

	public void setPorcentajeAvance(Integer porcentajeAvance) {
		this.porcentajeAvance = porcentajeAvance;
	}

	public Proyecto getProyectoB() {
		return this.proyectoB;
	}

	public void setProyectoB(Proyecto proyecto) {
		this.proyectoB = proyecto;
	}

	public String toString() {
		return "ReporteTiempoCliente [cliente=" + this.cliente + ", proyecto=" + this.proyecto + ", fechaInicio="
				+ this.fechaInicio + ", fechaFin=" + this.fechaFin + ", horasCotizadas=" + this.horasCotizadas
				+ ", horasConsumidas=" + this.horasConsumidas + ", porcentajeAvance=" + this.porcentajeAvance + "]";
	}
}

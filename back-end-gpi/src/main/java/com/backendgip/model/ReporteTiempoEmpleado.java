//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.model;

public class ReporteTiempoEmpleado {
	private String recurso;
	private String proyecto;
	private String descripcion;
	private String actividad;
	private Integer horas;
	private boolean interno;

	public ReporteTiempoEmpleado() {
	}

	public String getRecurso() {
		return this.recurso;
	}

	public void setRecurso(String recurso) {
		this.recurso = recurso;
	}

	public String getProyecto() {
		return this.proyecto;
	}

	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getActividad() {
		return this.actividad;
	}

	public void setActividad(String actividad) {
		this.actividad = actividad;
	}

	public boolean getProyectoInterno() {
		return this.interno;
	}

	public void setProyectoInterno(boolean interno) {
		this.interno = interno;
	}

	public Integer getHoras() {
		return this.horas;
	}

	public void setHoras(Integer horas) {
		this.horas = horas;
	}

	public String toString() {
		return "ReporteTiempoEmpleado [recurso=" + this.recurso + ", proyecto=" + this.proyecto + ", actividad="
				+ this.actividad + ", horas=" + this.horas + "]";
	}
}

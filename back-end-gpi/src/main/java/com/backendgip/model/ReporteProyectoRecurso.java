//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.model;

public class ReporteProyectoRecurso {
	String proyecto;
	String descripcion;
	String recurso;
	Integer horasReportadas;
	Proyecto proyectoB;

	public ReporteProyectoRecurso() {
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

	public String getRecurso() {
		return this.recurso;
	}

	public void setRecurso(String recurso) {
		this.recurso = recurso;
	}

	public Integer getHorasReportadas() {
		return this.horasReportadas;
	}

	public void setHorasReportadas(Integer horasReportadas) {
		this.horasReportadas = horasReportadas;
	}

	public String toString() {
		return "ReporteProyectoRecurso [proyecto=" + this.proyecto + ", recurso=" + this.recurso + ", horasReportadas="
				+ this.horasReportadas + "]";
	}
}

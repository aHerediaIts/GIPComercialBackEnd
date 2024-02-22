//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "novedad")
public class Novedad {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_novedad")
	private Integer id;
	@Column(name = "fecha_inicio_novedad")
	private LocalDate fechaInicio;
	@Column(name = "fecha_fin_novedad")
	private LocalDate fechaFin;
	@ManyToOne
	@JoinColumn(name = "fk_empleado")
	private Empleado empleado;
	@ManyToOne
	@JoinColumn(name = "fk_causas")
	private Causas causa;

	public Novedad() {
	}

	public Novedad(LocalDate fechaInicio, LocalDate fechaFin, Empleado empleado, Causas causa) {
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.empleado = empleado;
		this.causa = causa;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Empleado getEmpleado() {
		return this.empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public Causas getCausa() {
		return this.causa;
	}

	public void setCausa(Causas causa) {
		this.causa = causa;
	}

	public String toString() {
		return "Novedad [id=" + this.id + ", fechaInicio=" + this.fechaInicio + ", fechaFin=" + this.fechaFin
				+ ", empleado=" + this.empleado + ", causa=" + this.causa + "]";
	}
}

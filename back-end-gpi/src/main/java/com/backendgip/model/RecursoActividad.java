//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.model;

import java.io.Serializable;
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
@Table(name = "recurso_actividad")
public class RecursoActividad implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "pk_recurso_actividad")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private LocalDate fecha;
	@ManyToOne
	@JoinColumn(name = "fk_actividad_asignada")
	private ActividadAsignada actividad;
	@ManyToOne
	@JoinColumn(name = "fk_empleado")
	private Empleado empleado;
	private String asignador;

	public RecursoActividad() {
	}

	public RecursoActividad(LocalDate fecha, ActividadAsignada actividad, Empleado empleado) {
		this.fecha = fecha;
		this.actividad = actividad;
		this.empleado = empleado;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return this.fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public ActividadAsignada getActividad() {
		return this.actividad;
	}

	public void setActividad(ActividadAsignada actividad) {
		this.actividad = actividad;
	}

	public Empleado getEmpleado() {
		return this.empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public String getAsignador() {
		return this.asignador;
	}

	public void setAsignador(String asignador) {
		this.asignador = asignador;
	}

	public String toString() {
		return "RecursoActividad [id=" + this.id + ", fecha=" + this.fecha + ", actividad=" + this.actividad
				+ ", empleado=" + this.empleado + ", asignador=" + this.asignador + "]";
	}
}

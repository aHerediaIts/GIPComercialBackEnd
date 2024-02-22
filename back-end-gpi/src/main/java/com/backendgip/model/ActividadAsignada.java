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
@Table(name = "actividad_asignada")
public class ActividadAsignada implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "pk_actividad_asignada")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "fk_actividad")
	private Actividad actividad;
	@Column(name = "fecha_inicio")
	private LocalDate fechaInicio;
	@Column(name = "fecha_fin")
	private LocalDate fechaFin;
	@ManyToOne
	@JoinColumn(name = "fk_estado_actividad_asignada")
	private EstadoActividadAsig estado;
	@ManyToOne
	@JoinColumn(name = "fk_proyecto")
	private Proyecto proyecto;

	public ActividadAsignada() {
	}

	public ActividadAsignada(Actividad actividad, LocalDate fechaInicio, LocalDate fechaFin, EstadoActividadAsig estado,
			Proyecto proyecto) {
		this.actividad = actividad;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.estado = estado;
		this.proyecto = proyecto;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Actividad getActividad() {
		return this.actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
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

	public EstadoActividadAsig getEstado() {
		return this.estado;
	}

	public void setEstado(EstadoActividadAsig estado) {
		this.estado = estado;
	}

	public Proyecto getProyecto() {
		return this.proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	public String toString() {
		return "ActividadAsignada [id=" + this.id + ", actividad=" + this.actividad + ", fechaInicio="
				+ this.fechaInicio + ", fechaFin=" + this.fechaFin + ", estado=" + this.estado + ", proyecto="
				+ this.proyecto + "]";
	}
}

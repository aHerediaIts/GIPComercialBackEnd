package com.backendgip.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "especialidad_recurso")
public class EspecialidadRecurso {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_especialidad")
	private Integer id;
	@Column(name = "desc_especialidad")
	private String especialidad;

	public EspecialidadRecurso() {
	}

	public EspecialidadRecurso(String especialidad) {
		this.especialidad = especialidad;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEspecialidad() {
		return this.especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public String toString() {
		return "Especialidad [id=" + this.id + ", especialidad=" + this.especialidad + "]";
	}
}
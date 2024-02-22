//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "empleado_especialidad")
public class EmpleadoEspecialidad {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_empleado_especialidad")
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "fk_empleado")
	private Empleado empleado;
	@ManyToOne
	@JoinColumn(name = "fk_especialidad")
	private Especialidad especialidad;

	public EmpleadoEspecialidad() {
	}

	public EmpleadoEspecialidad(Empleado empleado, Especialidad especialidad) {
		this.empleado = empleado;
		this.especialidad = especialidad;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Empleado getEmpleado() {
		return this.empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public Especialidad getEspecialidad() {
		return this.especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}

	public String toString() {
		return "EmpleadoEspecialidad [id=" + this.id + ", empleado=" + this.empleado + ", especialidad="
				+ this.especialidad + "]";
	}
}

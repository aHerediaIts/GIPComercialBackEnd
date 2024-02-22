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
@Table(name = "empleado_rol")
public class EmpleadoRol {
	@Id
	@Column(name = "pk_empleado_rol")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	@ManyToOne
	@JoinColumn(name = "fk_empleado")
	public Empleado empleado;
	@ManyToOne
	@JoinColumn(name = "fk_rol")
	public Rol rol;

	public EmpleadoRol() {
	}

	public EmpleadoRol(Empleado empleado, Rol rol) {
		this.empleado = empleado;
		this.rol = rol;
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

	public Rol getRol() {
		return this.rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public String toString() {
		return "EmpleadoRol [id=" + this.id + ", empleado=" + this.empleado + ", rol=" + this.rol + "]";
	}
}

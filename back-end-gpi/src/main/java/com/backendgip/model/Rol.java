//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rol")
public class Rol implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "pk_rol")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String rol;
	private String descripcion;

	public Rol() {
	}

	public Rol(String rol, String descripcion) {
		this.rol = rol;
		this.descripcion = descripcion;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRol() {
		return this.rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String toString() {
		return "Rol [id=" + this.id + ", rol=" + this.rol + ", descripcion=" + this.descripcion + "]";
	}
}

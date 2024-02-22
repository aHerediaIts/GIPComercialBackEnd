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
import javax.persistence.Table;

@Entity
@Table(name = "dependencia_empleado")
public class DependenciaEmpleado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_dependencia")
	private Integer id;
	@Column(name = "desc_dependencia")
	private String dependencia;

	public DependenciaEmpleado() {
	}

	public DependenciaEmpleado(String dependencia) {
		this.dependencia = dependencia;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDependencia() {
		return this.dependencia;
	}

	public void setDependencia(String dependencia) {
		this.dependencia = dependencia;
	}

	public String toString() {
		return "DependenciaEmpleado [id=" + this.id + ", dependencia=" + this.dependencia + "]";
	}
}

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
@Table(name = "componente_desarrollo")
public class ComponenteDesarrollo implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "pk_componente_desarrollo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "desc_componente_desarrollo")
	private String componente;

	public ComponenteDesarrollo() {
	}

	public ComponenteDesarrollo(String componente) {
		this.componente = componente;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getComponente() {
		return this.componente;
	}

	public void setComponente(String componente) {
		this.componente = componente;
	}

	public String toString() {
		return "ComponenteDesarrollo [id=" + this.id + ", componente=" + this.componente + "]";
	}
}

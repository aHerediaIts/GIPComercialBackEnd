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
@Table(name = "estado_factura")
public class EstadoFactura implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "pk_estado_factura")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "desc_estado_factura")
	private String estado;

	public EstadoFactura() {
	}

	public EstadoFactura(String estado) {
		this.estado = estado;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String toString() {
		return "EstadoFactura [id=" + this.id + ", estado=" + this.estado + "]";
	}
}

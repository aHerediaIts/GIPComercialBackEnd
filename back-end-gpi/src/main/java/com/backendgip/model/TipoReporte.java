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
@Table(name = "tipo_reporte")
public class TipoReporte implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "pk_tipo_reporte")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "desc_tipo_reporte")
	private String tipo;

	public TipoReporte() {
	}

	public TipoReporte(String tipo) {
		this.tipo = tipo;
	}

	public Integer getId() {
		return this.id;
	}

	public void setIdTipoProyecto(Integer id) {
		this.id = id;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String toString() {
		return "TipoProyecto [id=" + this.id + ", tipo=" + this.tipo + "]";
	}
}

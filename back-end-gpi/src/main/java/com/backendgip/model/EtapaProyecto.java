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
@Table(name = "etapa_proyecto")
public class EtapaProyecto implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "pk_etapa_proyecto")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "desc_etapa_proyecto")
	private String etapa;

	public EtapaProyecto() {
	}

	public EtapaProyecto(String etapa) {
		this.etapa = etapa;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEtapa() {
		return this.etapa;
	}

	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}

	public String toString() {
		return "EtapaProyecto [id=" + this.id + ", etapa=" + this.etapa + "]";
	}
}

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
@Table(name = "causas")
public class Causas {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_causas")
	private Integer id;
	@Column(name = "desc_causas")
	private String causas;

	public Causas() {
	}

	public Causas(String causas) {
		this.causas = causas;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCausas() {
		return this.causas;
	}

	public void setCausas(String causas) {
		this.causas = causas;
	}

	public String toString() {
		return "Causas [id=" + this.id + ", causas=" + this.causas + "]";
	}
}

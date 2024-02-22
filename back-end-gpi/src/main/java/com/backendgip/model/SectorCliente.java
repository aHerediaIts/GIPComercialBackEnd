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
@Table(name = "sector_cliente")
public class SectorCliente implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "pk_sector_cliente")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "desc_cliente")
	private String sector;

	public SectorCliente() {
	}

	public SectorCliente(String sector) {
		this.sector = sector;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSector() {
		return this.sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String toString() {
		return "SectorCliente [id=" + this.id + ", sector=" + this.sector + "]";
	}
}

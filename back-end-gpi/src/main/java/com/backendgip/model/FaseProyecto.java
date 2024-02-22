//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.model;

import java.io.Serializable;
import java.util.Optional;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "fase_proyecto")
public class FaseProyecto implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "pk_fase_proyecto")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "desc_fase")
	private String fase;
	@Column(name = "propio_tabla")
	private Boolean base;
	@ManyToOne
	@JoinColumn(name = "fk_proyecto")
	private Proyecto proyecto;

	public FaseProyecto() {
	}

	public FaseProyecto(String fase, Boolean base, Proyecto proyecto) {
		this.fase = fase;
		this.base = base;
		this.proyecto = proyecto;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFase() {
		return this.fase;
	}

	public void setFase(String fase) {
		this.fase = fase;
	}

	public Boolean getBase() {
		return this.base;
	}

	public void setBase(Boolean base) {
		this.base = base;
	}

	public Proyecto getProyecto() {
		return this.proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	public String toString() {
		return "FaseProyecto [id=" + this.id + ", fase=" + this.fase + ", base=" + this.base + ", proyecto="
				+ Optional.ofNullable(this.proyecto != null ? this.proyecto.getNombre():null).orElse(null) + "]";
	}
}

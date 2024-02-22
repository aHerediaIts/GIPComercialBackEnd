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
@Table(name = "actividad")
public class Actividad implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "pk_Actividad")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "desc_actividad")
	private String actividad;
	@ManyToOne
	@JoinColumn(name = "fk_fase_proyecto")
	private FaseProyecto fase;
	@ManyToOne
	@JoinColumn(name = "fk_proyecto")
	private Proyecto proyecto;
	@Column(name = "propio_tabla")
	private Boolean base;

	public Actividad() {
	}

	public Actividad(String actividad, FaseProyecto fase, Proyecto proyecto, Boolean base) {
		this.actividad = actividad;
		this.fase = fase;
		this.proyecto = proyecto;
		this.base = base;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getActividad() {
		return this.actividad;
	}

	public void setActividad(String actividad) {
		this.actividad = actividad;
	}

	public FaseProyecto getFase() {
		return this.fase;
	}

	public void setFase(FaseProyecto fase) {
		this.fase = fase;
	}

	public Proyecto getProyecto() {
		return this.proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	public Boolean getBase() {
		return this.base;
	}

	public void setBase(Boolean base) {
		this.base = base;
	}

	public String toString() {
		return "Actividad [id=" + this.id + ", actividad=" + this.actividad + ", fase="
				+ Optional.ofNullable(this.fase != null ? this.fase.getFase() : null).orElse(null) + ", proyecto="
				+ Optional.ofNullable(this.proyecto != null ? this.proyecto.getNombre(): null).orElse(null) + ", base=" + this.base + "]";
	}
}

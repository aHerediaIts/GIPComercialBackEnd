package com.backendgip.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "perfiles_recurso")
public class PerfilesRecurso {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_perfil")
	private Integer id;
	@Column(name = "desc_perfil")
	private String perfil;
    @Column( name = "fk_especialidad")
    private Integer fk_especialidad;

    public PerfilesRecurso() {
	}

	public PerfilesRecurso(String perfil) {
		this.perfil = perfil;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public Integer getFk_especialidad() {
        return fk_especialidad;
    }

    public void setFk_especialidad(Integer fk_especialidad) {
        this.fk_especialidad = fk_especialidad;
    }

	@Override
	public String toString() {
		return "PerfilesRecurso [id=" + id + ", perfil=" + perfil + ", fk_especialidad=" + fk_especialidad + "]";
	}

	
}
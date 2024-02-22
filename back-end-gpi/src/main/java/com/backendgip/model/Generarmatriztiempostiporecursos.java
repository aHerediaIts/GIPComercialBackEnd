package com.backendgip.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

import org.hibernate.annotations.CollectionId;

@Entity
@Table(name = "tipo_recurso")
public class Generarmatriztiempostiporecursos implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id

    @Column(name = "pk_tipo_recurso")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column( name = "especialidad")
    private String especialidad;

    @Column( name = "perfil")
    private String perfil;

    @Column( name = "desc_cargo")
    private String desc_cargo;

    @Column(name = "costo_recurso")
    private double costo_recurso;

    public Generarmatriztiempostiporecursos(){
    }

    public Generarmatriztiempostiporecursos(Integer id) {
        this.id = id;
    }

    public Generarmatriztiempostiporecursos(Integer id, String especialidad, String perfil, String desc_cargo,
            double costo_recurso) {
        this.id = id;
        this.especialidad = especialidad;
        this.perfil = perfil;
        this.desc_cargo = desc_cargo;
        this.costo_recurso = costo_recurso;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getDesc_cargo() {
        return desc_cargo;
    }

    public void setDesc_cargo(String desc_cargo) {
        this.desc_cargo = desc_cargo;
    }

    public double getCosto_recurso() {
        return costo_recurso;
    }

    public void setCosto_recurso(double costo_recurso) {
        this.costo_recurso = costo_recurso;
    }

}

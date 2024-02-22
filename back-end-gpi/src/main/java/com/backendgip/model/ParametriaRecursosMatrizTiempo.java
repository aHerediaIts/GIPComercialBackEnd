
package com.backendgip.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "parametria_recursos_matriz_tiempo")
public class ParametriaRecursosMatrizTiempo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "pk_parametria_recursos_matriz_tiempo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_pk_especialidad")
    private EspecialidadRecurso especialidad;

    @ManyToOne
    @JoinColumn(name = "fk_pk_perfil")
    private PerfilesRecurso perfil;

    @Column(name = "tarifa_hora")
    private Integer tarifaHora;

    @Column(name = "tarifa_mensual")
    private Integer tarifaMensual;
    
    @Column(name = "descripcion")
    private String descripcion;


    public ParametriaRecursosMatrizTiempo() {
    }

    public ParametriaRecursosMatrizTiempo(Integer id) {
        this.id = id;
    }

    public ParametriaRecursosMatrizTiempo(Integer id, EspecialidadRecurso especialidad, PerfilesRecurso perfil, Integer tarifaHora, Integer tarifaMensual,
            String descripcion) {
        this.id = id;
        this.especialidad = especialidad;
        this.perfil = perfil;
        this.tarifaHora = tarifaHora;
        this.tarifaMensual = tarifaMensual;
        this.descripcion = descripcion;
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

    public EspecialidadRecurso getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(EspecialidadRecurso especialidad) {
        this.especialidad = especialidad;
    }

    public PerfilesRecurso getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilesRecurso perfil) {
        this.perfil = perfil;
    }

    public Integer getTarifaHora() {
        return tarifaHora;
    }

    public void setTarifaHora(Integer tarifaHora) {
        this.tarifaHora = tarifaHora;
    }

    public Integer getTarifaMensual() {
        return tarifaMensual;
    }

    public void setTarifaMensual(Integer tarifaMensual) {
        this.tarifaMensual = tarifaMensual;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "ParametriaRecursosMatrizTiempo [id=" + id + ", especialidad=" + especialidad + ", perfil=" + perfil
                + ", tarifaHora=" + tarifaHora + ", tarifaMensual=" + tarifaMensual + ", descripcion=" + descripcion
                + "]";
    }
    
}

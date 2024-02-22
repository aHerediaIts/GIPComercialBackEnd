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
@Table(name = "generacion_matriz_tiempo_general")
public class GeneracionMatrizTiempo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "pk_generacion_matriz_tiempo_general")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "fk_pk_matriz_tiempo")
    private MatrizTiempo matrizTiempo;
    @ManyToOne
    @JoinColumn(name = "fk_pk_version")
    private VersionMatriz version;
    @Column(name = "sprint")
    private Integer sprint;
    @Column(name = "duracion_dias_sprint")
    private Integer duracionDiasSprint;
    @Column(name = "total_horas_sprint")
    private Integer totalHorasSprint;
    @Column(name = "total_horas_recurso_sprint")
    private Integer totalHorasRecursoSprint;


    public GeneracionMatrizTiempo() {
    }

    public GeneracionMatrizTiempo(Integer id) {
        this.id = id;
    }

    public GeneracionMatrizTiempo(Integer id, MatrizTiempo matrizTiempo, VersionMatriz version,
    Integer sprint, Integer duracionDiasSprint, Integer totalHorasSprint, Integer totalHorasRecursoSprint,
    Integer recursoAsignadoSprint) {
        this.id = id;
        this.matrizTiempo = matrizTiempo;
        this.version = version;
        this.sprint = sprint;
        this.duracionDiasSprint = duracionDiasSprint;
        this.totalHorasSprint = totalHorasSprint;
        this.totalHorasRecursoSprint = totalHorasRecursoSprint;
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

    public MatrizTiempo getMatrizTiempo() {
        return matrizTiempo;
    }

    public void setMatrizTiempo(MatrizTiempo matrizTiempo) {
        this.matrizTiempo = matrizTiempo;
    }

    public VersionMatriz getVersion() {
        return version;
    }

    public void setVersion(VersionMatriz version) {
        this.version = version;
    }

    public Integer getSprint() {
        return sprint;
    }

    public void setSprint(Integer sprints) {
        this.sprint = sprints;
    }

    public Integer getDuracionDiasSprint() {
        return duracionDiasSprint;
    }

    public void setDuracionDiasSprint(Integer duracionDiasSprint) {
        this.duracionDiasSprint = duracionDiasSprint;
    }

    public Integer getTotalHorasSprint() {
        return totalHorasSprint;
    }

    public void setTotalHorasSprint(Integer totalHorasSprint) {
        this.totalHorasSprint = totalHorasSprint;
    }

    public Integer getTotalHorasRecursoSprint() {
        return totalHorasRecursoSprint;
    }

    public void setTotalHorasRecursoSprint(Integer totalHorasRecursoSprint) {
        this.totalHorasRecursoSprint = totalHorasRecursoSprint;
    }
    
}

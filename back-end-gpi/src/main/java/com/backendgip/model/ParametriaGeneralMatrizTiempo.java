//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

@Entity
@Table(name = "parametria_general_matriz_tiempo")
public class ParametriaGeneralMatrizTiempo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "pk_parametria_general_matriz_tiempo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "meses_maximo")
    private Integer mesesMaximo;
    @Column(name = "sprints_maximo")
    private Integer sprintsMaximo;
    @Column(name = "semanas_sprint_maximo")
    private Integer semanaSprintMaximo;
    @Column(name = "dias_sprint_maximo")
    private Integer diaSprintMaximo;
    @Column(name = "recursos_maximo")
    private Integer recursosMaximo;
    @Column(name = "numero_horas")
    private Integer numeroHoras;
    @Column(name = "fecha_update")
    private LocalDateTime fechaUpdate;

    public ParametriaGeneralMatrizTiempo() {
    }

    public ParametriaGeneralMatrizTiempo(Integer id) {
        this.id = id;
    }


    public ParametriaGeneralMatrizTiempo(Integer id, Integer mesesMaximo, Integer sprintsMaximo,
            Integer semanaSprintMaximo, Integer diaSprintMaximo, Integer recursosMaximo, Integer numeroHoras, LocalDateTime fechaUpdate) {
        this.id = id;
        this.mesesMaximo = mesesMaximo;
        this.sprintsMaximo = sprintsMaximo;
        this.semanaSprintMaximo = semanaSprintMaximo;
        this.diaSprintMaximo = diaSprintMaximo;
        this.recursosMaximo = recursosMaximo;
        this.numeroHoras = numeroHoras;
        this.fechaUpdate = fechaUpdate;
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

    public Integer getMesesMaximo() {
        return mesesMaximo;
    }

    public void setMesesMaximo(Integer mesesMaximo) {
        this.mesesMaximo = mesesMaximo;
    }

    public Integer getSprintsMaximo() {
        return sprintsMaximo;
    }

    public void setSprintsMaximo(Integer sprintsMaximo) {
        this.sprintsMaximo = sprintsMaximo;
    }

    public Integer getSemanaSprintMaximo() {
        return semanaSprintMaximo;
    }

    public void setSemanaSprintMaximo(Integer semanaSprintMaximo) {
        this.semanaSprintMaximo = semanaSprintMaximo;
    }

    public Integer getDiaSprintMaximo() {
        return diaSprintMaximo;
    }

    public void setDiaSprintMaximo(Integer diaSprintMaximo) {
        this.diaSprintMaximo = diaSprintMaximo;
    }

    public Integer getRecursosMaximo() {
        return recursosMaximo;
    }

    public void setRecursosMaximo(Integer recursosMaximo) {
        this.recursosMaximo = recursosMaximo;
    }

    public Integer getNumeroHoras() {
        return numeroHoras;
    }

    public void setNumeroHoras(Integer numeroHoras) {
        this.numeroHoras = numeroHoras;
    }
    
    public LocalDateTime getFechaUpdate() {
        return fechaUpdate;
    }

    public void setFechaUpdate(LocalDateTime fechaUpdate) {
        this.fechaUpdate = fechaUpdate;
    }

}

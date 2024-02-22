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
@Table(name = "matriz_tiempo")
public class MatrizTiempo implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "pk_matriz_tiempo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nombre_matriz")
    private String nombreMatriz;
    @Column(name = "fecha_creacion_matriz")
    private LocalDateTime fechaCreacionMatriz;
    

    public MatrizTiempo() {
    }

    public MatrizTiempo(Integer id) {
        this.id = id;
    }

    public MatrizTiempo(Integer id, String nombreMatriz, LocalDateTime fechaCreacionMatriz) {
        this.id = id;
        this.nombreMatriz = nombreMatriz;
        this.fechaCreacionMatriz = fechaCreacionMatriz;
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
    public String getNombreMatriz() {
        return nombreMatriz;
    }
    public void setNombreMatriz(String nombreMatriz) {
        this.nombreMatriz = nombreMatriz;
    }
    public LocalDateTime getFechaCreacionMatriz() {
        return fechaCreacionMatriz;
    }
    public void setFechaCreacionMatriz(LocalDateTime fechaCreacionMatriz) {
        this.fechaCreacionMatriz = fechaCreacionMatriz;
    }
}

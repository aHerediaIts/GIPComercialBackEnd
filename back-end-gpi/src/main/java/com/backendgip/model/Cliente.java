//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.model;

import java.io.Serializable;
import java.time.LocalDate;
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
@Table(name = "cliente")
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "pk_cliente")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nit;
    private String nomenclatura;
    @Column(name = "nombre_cliente")
    private String nombre;
    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;
    @ManyToOne
    @JoinColumn(name = "fk_estado_cliente")
    private EstadoCliente estado;
    @ManyToOne
    @JoinColumn(name = "fk_sector_cliente")
    private SectorCliente sector;
    @ManyToOne
    @JoinColumn(name = "fk_empleado")
    private Empleado gerenteCuenta;
    @Column(name = "validar_id_recurso")
    private Integer validadorIdRecurso;

    public Cliente() {
    }

    public Cliente(Integer id, String nit, String nomenclatura, String nombre, LocalDate fechaCreacion,
            EstadoCliente estado, SectorCliente sector, Empleado gerenteCuenta, Integer validadorIdRecurso) {
        this.id = id;
        this.nit = nit;
        this.nomenclatura = nomenclatura;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
        this.sector = sector;
        this.gerenteCuenta = gerenteCuenta;
        this.validadorIdRecurso = validadorIdRecurso;
    }



    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNit() {
        return this.nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNomenclatura() {
        return this.nomenclatura;
    }

    public void setNomenclatura(String nomenclatura) {
        this.nomenclatura = nomenclatura;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaCreacion() {
        return this.fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public EstadoCliente getEstado() {
        return this.estado;
    }

    public void setEstado(EstadoCliente estado) {
        this.estado = estado;
    }

    public SectorCliente getSector() {
        return this.sector;
    }

    public void setSector(SectorCliente sector) {
        this.sector = sector;
    }

    public Empleado getGerenteCuenta() {
        return this.gerenteCuenta;
    }

    public void setGerenteCuenta(Empleado gerenteCuenta) {
        this.gerenteCuenta = gerenteCuenta;
    }

    public Integer getValidadorIdRecurso() {
        return validadorIdRecurso;
    }

    public void setValidadorIdRecurso(Integer validadorIdRecurso) {
        this.validadorIdRecurso = validadorIdRecurso;
    }

    @Override
    public String toString() {
        return "Cliente [id=" + id + ", nit=" + nit + ", nomenclatura=" + nomenclatura + ", nombre=" + nombre
                + ", fechaCreacion=" + fechaCreacion + ", estado=" + estado + ", sector=" + sector + ", gerenteCuenta="
                + gerenteCuenta + ", validadorIdRecurso=" + validadorIdRecurso + "]";
    }

    
}

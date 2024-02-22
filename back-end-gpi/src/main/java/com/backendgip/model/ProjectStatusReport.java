//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "project_status_report")
public class ProjectStatusReport implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "pk_project_status_report")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "fk_pk_empleado")
    private Empleado empleado;
    @Column(name = "fecha_creacion_psr")
    private LocalDateTime fechaCreacionPsr;
    @Column(name = "estado_psr")
    private String estado;
    @ManyToOne
    @JoinColumn(name = "fk_pk_proyecto")
    private Proyecto proyecto;
    @Column(name = "codigo")
    private String codigo;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @ManyToOne
    @JoinColumn(name = "fk_pk_cliente")
    private Cliente cliente;
    @Column(name = "nombre_cliente")
    private String nombreCliente;
    @Column(name = "porcentaje_avance_esperado")
    private Integer porcentajeAvanceEsperado;
    @Column(name = "porcentaje_avance_real")
    private Integer porcentajeAvanceReal;
    @Column(name = "porcentaje_desviacion")
    private Integer porcentajeDesviacion;

    public ProjectStatusReport() {

    }

    public ProjectStatusReport(Integer id) {
        this.id = id;
    }

    public ProjectStatusReport(Integer id, Empleado empleado, LocalDateTime fechaCreacionPsr, String estado,
            Proyecto proyecto, String codigo, String nombre, String descripcion, Cliente cliente, String nombreCliente,
            Integer porcentajeAvanceEsperado, Integer porcentajeAvanceReal, Integer porcentajeDesviacion) {
        this.id = id;
        this.empleado = empleado;
        this.fechaCreacionPsr = fechaCreacionPsr;
        this.estado = estado;
        this.proyecto = proyecto;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cliente = cliente;
        this.nombreCliente = nombreCliente;
        this.porcentajeAvanceEsperado = porcentajeAvanceEsperado;
        this.porcentajeAvanceReal = porcentajeAvanceReal;
        this.porcentajeDesviacion = porcentajeDesviacion;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public LocalDateTime getFechaCreacionPsr() {
        return fechaCreacionPsr;
    }

    public void setFechaCreacionPsr(LocalDateTime fechaCreacionPsr) {
        this.fechaCreacionPsr = fechaCreacionPsr;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion(){
        return descripcion;
    }

    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Integer getPorcentajeAvanceEsperado() {
        return porcentajeAvanceEsperado;
    }

    public void setPorcentajeAvanceEsperado(Integer porcentajeAvanceEsperado) {
        this.porcentajeAvanceEsperado = porcentajeAvanceEsperado;
    }

    public Integer getPorcentajeAvanceReal() {
        return porcentajeAvanceReal;
    }

    public void setPorcentajeAvanceReal(Integer porcentajeAvanceReal) {
        this.porcentajeAvanceReal = porcentajeAvanceReal;
    }

    public Integer getPorcentajeDesviacion() {
        return porcentajeDesviacion;
    }

    public void setPorcentajeDesviacion(Integer porcentajeDesviacion) {
        this.porcentajeDesviacion = porcentajeDesviacion;
    }
}

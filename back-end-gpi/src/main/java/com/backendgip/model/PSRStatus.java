//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.model;

import java.io.Serializable;
import java.sql.Date;
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
@Table(name = "psr_status")
public class PSRStatus implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "pk_psr_status")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "fk_pk_project_status_report")
    private ProjectStatusReport projectStatusReport;
    @ManyToOne
    @JoinColumn(name = "fk_pk_empleado")
    private Empleado empleado;
    @Column(name = "comentario")
    private String comentario;
    @Column(name = "fecha_creacion_status")
    private LocalDateTime fechaCreacionCtatus;
	@Column(name = "es_comentario_gerencia")
	private Boolean comentarioGerencia;


    public PSRStatus() {
    }

    public PSRStatus(ProjectStatusReport projectStatusReport, Empleado empleado, LocalDateTime fechaCreacionCtatus,
            String comentario, Boolean comentarioGerencia) {
        this.projectStatusReport = projectStatusReport;
        this.empleado = empleado;
        this.fechaCreacionCtatus = fechaCreacionCtatus;
        this.comentario = comentario;
        this.comentarioGerencia = comentarioGerencia;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProjectStatusReport getPSRProyecto() {
        return this.projectStatusReport;
    }

    public void setPSRProyecto(ProjectStatusReport projectStatusReport) {
        this.projectStatusReport = projectStatusReport;
    }

    public Empleado getEmpleado() {
        return this.empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public String getComentario() {
        return this.comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDateTime getFechaCreacionStatus() {
        return this.fechaCreacionCtatus;
    }

    public void setFechaCreacionStatus(LocalDateTime fechaCreacionCtatus) {
        this.fechaCreacionCtatus = fechaCreacionCtatus;
    }

    public Boolean getComentarioGerencia() {
        return comentarioGerencia;
    }

    public void setComentarioGerencia(Boolean comentarioGerencia) {
        this.comentarioGerencia = comentarioGerencia;
    }
}

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
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
@Table(name = "reporte_tiempo")
public class ReporteTiempo implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "pk_reporte_tiempo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "fecha_reporte")
	private LocalDate fecha;
	private Integer horas;
	private Integer aprobador;
	private Date fechaA;
	private String justificacion;
	@ManyToOne
	@JoinColumn(name = "fk_proyecto")
	private Proyecto proyecto;
	@ManyToOne
	@JoinColumn(name = "fk_estado_reporte_tiempo")
	private EstadoReporteTiempo estado;
	@ManyToOne
	@JoinColumn(name = "fk_empleado")
	private Empleado empleado;
	@ManyToOne
	@JoinColumn(name = "fk_actividad")
	private ActividadAsignada actividad;
	private String asignador;

	public ReporteTiempo() {
	}

	public ReporteTiempo(LocalDate fecha, Integer horas, String justificacion, Proyecto proyecto,
			EstadoReporteTiempo estado, Empleado empleado, ActividadAsignada actividad) {
		this.fecha = fecha;
		this.horas = horas;
		this.justificacion = justificacion;
		this.proyecto = proyecto;
		this.estado = estado;
		this.empleado = empleado;
		this.actividad = actividad;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return this.fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Date getFechaA() {
		return this.fechaA;
	}

	public void setFechaA(Date fechaA) {
		this.fechaA = fechaA;
	}

	public Integer getHoras() {
		return this.horas;
	}

	public void setAprobador(Integer aprobador) {
		this.aprobador = aprobador;
	}

	public Integer getAprobador() {
		return this.aprobador;
	}

	public void setHoras(Integer horas) {
		this.horas = horas;
	}

	public String getJustificacion() {
		return this.justificacion;
	}

	public void setJustificacion(String justificacion) {
		this.justificacion = justificacion;
	}

	public Proyecto getProyecto() {
		return this.proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	public EstadoReporteTiempo getEstado() {
		return this.estado;
	}

	public void setEstado(EstadoReporteTiempo estado) {
		this.estado = estado;
	}

	public Empleado getEmpleado() {
		return this.empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public ActividadAsignada getActividad() {
		return this.actividad;
	}

	public void setActividad(ActividadAsignada actividad) {
		this.actividad = actividad;
	}

	public String getAsignador() {
		return this.asignador;
	}

	public void setAsignador(String asignador) {
		this.asignador = asignador;
	}

	public String toString() {
		return "ReporteTiempo [id=" + this.id + ", fecha=" + this.fecha + ", horas=" + this.horas + ", aprobador="
				+ this.aprobador + ", fechaA=" + this.fechaA + ", justificacion=" + this.justificacion + ", proyecto="
				+ Optional.ofNullable(this.proyecto != null ? this.proyecto.getNombre() : null).orElse(null) + ", estado="
				+ Optional.ofNullable(this.estado != null ? this.estado.getEstado() : null).orElse(null) + ", empleado="
				+ Optional.ofNullable(this.empleado != null ? this.empleado.getNombre() : null).orElse(null) + ", actividad="
				+ Optional.ofNullable(this.actividad != null ? this.actividad.getActividad() : null).orElse(null) + ", asignador="
				+ this.asignador + "]";
	}
}

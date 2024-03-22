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
@Table(name = "proyecto")
public class Proyecto implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "pk_proyecto")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String codigo;
	private String nombre;
	@Column(name = "costo_proyecto")
	private Integer costo;
	@Column(name = "descripcion")
	private String descripcion;
	@Column(name = "fecha_creacion")
	private LocalDate fechaCreacion;
	@Column(name = "fecha_aprobacion")
	private LocalDate fechaAprobacion;
	@Column(name = "fecha_inicio")
	private LocalDate fechaInicio;
	@Column(name = "fecha_fin")
	private LocalDate fechaFin;
	@Column(name = "horas_planeadas")
	private Integer horasPlaneadas;
	@Column(name = "horas_propuesta")
	private Integer horasPropuesta;
	@Column(name = "dir_cliente")
	private String directorClient;
	@Column(name = "rf_proyecto")
	private String rfProyecto;
	@ManyToOne
	@JoinColumn(name = "fk_tipo_proyecto")
	private TipoProyecto tipo;
	@ManyToOne
	@JoinColumn(name = "fk_etapa_proyecto")
	private EtapaProyecto etapa;
	@ManyToOne
	@JoinColumn(name = "fk_componente_desarrollo")
	private ComponenteDesarrollo componente;
	@ManyToOne
	@JoinColumn(name = "fk_cliente")
	private Cliente cliente;
	@ManyToOne
	@JoinColumn(name = "fk_estado_propuesta")
	private EstadoPropuesta estadoPropuesta;
	@ManyToOne
	@JoinColumn(name = "fk_estado_proyecto")
	private EstadoProyecto estadoProyecto;
	@ManyToOne
	@JoinColumn(name = "its")
	private Empleado directorIts;
	@ManyToOne
	@JoinColumn(name = "lider_project")
	private Empleado lider;
	private Boolean interno;
	private String creador;

	public Proyecto() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCosto() {
		return this.costo;
	}

	public void setCosto(Integer costo) {
		this.costo = costo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public LocalDate getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public LocalDate getFechaAprobacion() {
		return this.fechaAprobacion;
	}

	public void setFechaAprobacion(LocalDate fechaAprobacion) {
		this.fechaAprobacion = fechaAprobacion;
	}

	public LocalDate getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Integer getHorasPlaneadas() {
		return this.horasPlaneadas;
	}

	public void setHorasPlaneadas(Integer horasPlaneadas) {
		this.horasPlaneadas = horasPlaneadas;
	}

	public Integer getHorasPropuesta() {
		return this.horasPropuesta;
	}

	public void setHorasPropuesta(Integer horasPropuesta) {
		this.horasPropuesta = horasPropuesta;
	}

	public String getDirectorClient() {
		return this.directorClient;
	}

	public void setDirectorClient(String directorClient) {
		this.directorClient = directorClient;
	}

	public String getRfProyecto() {
		return rfProyecto;
	}

	public void setRfProyecto(String rfProyecto) {
		this.rfProyecto = rfProyecto;
	}

	public TipoProyecto getTipo() {
		return this.tipo;
	}

	public void setTipo(TipoProyecto tipo) {
		this.tipo = tipo;
	}

	public EtapaProyecto getEtapa() {
		return this.etapa;
	}

	public void setEtapa(EtapaProyecto etapa) {
		this.etapa = etapa;
	}

	public ComponenteDesarrollo getComponente() {
		return this.componente;
	}

	public void setComponente(ComponenteDesarrollo componente) {
		this.componente = componente;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public EstadoPropuesta getEstadoPropuesta() {
		return this.estadoPropuesta;
	}

	public void setEstadoPropuesta(EstadoPropuesta estadoPropuesta) {
		this.estadoPropuesta = estadoPropuesta;
	}

	public EstadoProyecto getEstadoProyecto() {
		return this.estadoProyecto;
	}

	public void setEstadoProyecto(EstadoProyecto estadoProyecto) {
		this.estadoProyecto = estadoProyecto;
	}

	public Empleado getDirectorIts() {
		return this.directorIts;
	}

	public void setDirectorIts(Empleado directorIts) {
		this.directorIts = directorIts;
	}

	public Empleado getLider() {
		return this.lider;
	}

	public void setLider(Empleado lider) {
		this.lider = lider;
	}

	public Boolean getInterno() {
		return this.interno;
	}

	public void setInterno(Boolean interno) {
		this.interno = interno;
	}

	public String getCreador() {
		return this.creador;
	}

	public void setCreador(String creador) {
		this.creador = creador;
	}

	@Override
	public String toString() {
		return "Proyecto [id=" + id + ", codigo=" + codigo + ", nombre=" + nombre + ", costo=" + costo
				+ ", descripcion=" + descripcion + ", fechaCreacion=" + fechaCreacion + ", fechaAprobacion="
				+ fechaAprobacion + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", horasPlaneadas="
				+ horasPlaneadas + ", horasPropuesta=" + horasPropuesta + ", directorClient=" + directorClient
				+ ", rfProyecto=" + rfProyecto + ", tipo=" + tipo + ", etapa=" + etapa + ", componente=" + componente
				+ ", cliente=" + cliente + ", estadoPropuesta=" + estadoPropuesta + ", estadoProyecto=" + estadoProyecto
				+ ", directorIts=" + directorIts + ", lider=" + lider + ", interno=" + interno + ", creador=" + creador
				+ "]";
	}

	
}

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "log_sistema")
public class LogSistema implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "pk_log_sistema")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String accion;
	@Column(name = "fecha_hora")
	private Date fechaHora;
	private String usuario;
	private String descripcion;
	@Column(name = "peticion_servidor")
	private String servidor;
	private String tabla;
	@Column(name = "id_afectado")
	private Integer idAccion;

	public LogSistema() {
	}

	public LogSistema(String accion, Date fechaHora, String usuario, String descripcion, String servidor, String tabla,
			Integer idAccion) {
		this.accion = accion;
		this.fechaHora = fechaHora;
		this.usuario = usuario;
		this.descripcion = descripcion;
		this.servidor = servidor;
		this.tabla = tabla;
		this.idAccion = idAccion;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccion() {
		return this.accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public Date getFechaHora() {
		return this.fechaHora;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getServidor() {
		return this.servidor;
	}

	public void setServidor(String servidor) {
		this.servidor = servidor;
	}

	public String getTabla() {
		return this.tabla;
	}

	public void setTabla(String tabla) {
		this.tabla = tabla;
	}

	public Integer getIdAccion() {
		return this.idAccion;
	}

	public void setIdAccion(Integer idAccion) {
		this.idAccion = idAccion;
	}

	public String toString() {
		return "LogSistema [id=" + this.id + ", accion=" + this.accion + ", fechaHora=" + this.fechaHora + ", usuario="
				+ this.usuario + ", descripcion=" + this.descripcion + ", servidor=" + this.servidor + ", tabla="
				+ this.tabla + ", idAccion=" + this.idAccion + "]";
	}
}

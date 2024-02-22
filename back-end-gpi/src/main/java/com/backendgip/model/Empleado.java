//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Optional;

@Entity
@Table(name = "empleado")
public class Empleado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_empleado")
	private Integer id;
	@Column(name = "numero_documento")
	private String numeroDoc;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "email")
	private String email;
	@Column(name = "nombre_usuario")
	private String nombreUsuario;
	private String password;
	@ManyToOne
	@JoinColumn(name = "fk_tipo_documento")
	private TipoDocumento tipoDoc;
	@ManyToOne
	@JoinColumn(name = "fk_dependencia")
	private DependenciaEmpleado dependencia;
	@ManyToOne
	@JoinColumn(name = "fk_cargo")
	private Cargo cargo;
	@ManyToOne
	@JoinColumn(name = "fk_estado_empleado")
	private EstadoEmpleado estado;

	public Empleado() {
	}

	public Empleado(String numeroDoc, String nombre, String email, String nombreUsuario, String password,
			TipoDocumento tipoDoc, DependenciaEmpleado dependencia, Cargo cargo, EstadoEmpleado estado) {
		this.numeroDoc = numeroDoc;
		this.nombre = nombre;
		this.email = email;
		this.nombreUsuario = nombreUsuario;
		this.password = password;
		this.tipoDoc = tipoDoc;
		this.dependencia = dependencia;
		this.cargo = cargo;
		this.estado = estado;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumeroDoc() {
		return this.numeroDoc;
	}

	public void setNumeroDoc(String numeroDoc) {
		this.numeroDoc = numeroDoc;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombreUsuario() {
		return this.nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public TipoDocumento getTipoDoc() {
		return this.tipoDoc;
	}

	public void setTipoDoc(TipoDocumento tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public DependenciaEmpleado getDependencia() {
		return this.dependencia;
	}

	public void setDependencia(DependenciaEmpleado dependencia) {
		this.dependencia = dependencia;
	}

	public Cargo getCargo() {
		return this.cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public EstadoEmpleado getEstado() {
		return this.estado;
	}

	public void setEstado(EstadoEmpleado estado) {
		this.estado = estado;
	}

	public String toString() {
		return "Empleado [id=" + this.id + ", numeroDoc=" + this.numeroDoc + ", nombre=" + this.nombre + ", email="
				+ this.email + ", nombreUsuario=" + this.nombreUsuario + ", password=" + this.password + ", tipoDoc="
				+ this.tipoDoc + ", dependencia="
				+ Optional.ofNullable(this.dependencia != null ? this.dependencia.getDependencia() : null).orElse(null) + ", cargo="
				+ Optional.ofNullable(this.cargo != null ? this.cargo.getCargo() : null).orElse(null) + ", estado="
				+ Optional.ofNullable(this.estado != null ? this.estado.getEstado() : null).orElse(null) + "]";
	}
}

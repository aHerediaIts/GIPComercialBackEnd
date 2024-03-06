package com.backendgip.security.models;



import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="usuario")
public class Usuario implements Serializable{


	private static final long serialVersionUID = 4560999894106704922L;
	
	@Id
	@Column(name="idUsuario")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idUsuario;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="apellido")
	private String apellido;
	
	@Column(name="clave")
	private String password;
	
	@Column(name="usuario")
	private String username;
	
	@Column(name="correo")
	private String correo;
	
	@Column(name="activo")
	private boolean enabled;
	
	@Column(name="fechaCreacion")
	private LocalDateTime fechaCreacion;
	
	@Column(name="cambiarClave")
	private boolean cambiarClave;
	
	@Column(name="fechaCambioclave")
	private LocalDateTime fechaCambioclave;	
	
	@Column(name="numeroDocumento")
	String numeroDocumento;


	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "usuario")
    private List<UsuarioRol> usuarioRoles;
	
	public Usuario() {
	}
	public Usuario(long idUsuario, String nombre, String apellido, String password, String usuario,String correo,boolean enabled,LocalDateTime fechaCambioclave,boolean cambiarClave,LocalDateTime fechaCreacion,String numeroDocumento) {


		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.apellido = apellido;
		this.password = password;
		this.username = usuario;
		this.correo=correo;
		this.enabled=enabled;
		this.fechaCambioclave=fechaCambioclave;
		this.cambiarClave=cambiarClave;
		this.fechaCreacion=fechaCreacion;
		this.numeroDocumento=numeroDocumento;
	}
	
	
	public long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUserName(String usuario) {
		this.username = usuario;
	}


    public List<UsuarioRol> getUsuarioRoles() {
        return usuarioRoles;
    }


    public void setUsuarioRoles(List<UsuarioRol> usuarioRoles) {
        this.usuarioRoles = usuarioRoles;
    }
    	
	public void setEnabled(boolean enabled) {
		 this.enabled=enabled;
	}
	
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}		
	
	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public boolean isCambiarClave() {
		return cambiarClave;
	}
	public void setCambiarClave(boolean cambiarClave) {
		this.cambiarClave = cambiarClave;
	}
	public LocalDateTime getFechaCambioclave() {
		return fechaCambioclave;
	}
	public void setFechaCambioclave(LocalDateTime fechaCambioclave) {
		this.fechaCambioclave = fechaCambioclave;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", nombre=" + nombre + ", apellido=" + apellido + ", password="
				+ password + ", username=" + username + ", usuarioRoles=" + usuarioRoles + "]";
	}
}
		






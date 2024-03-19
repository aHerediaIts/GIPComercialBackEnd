package com.backendgip.security.models;



import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.backendgip.model.Empleado;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

	@ManyToOne
	@JoinColumn(name="fkEmpleadoAsociado")
	private Empleado empleadoAsociado;
	
	
	@Column(name="clave")
	private String password;
	
	@Column(name="usuario")
	private String username;
	
	@Column(name="correo")
	private String correo;
	
	@Column(name="activo")
	private boolean enabled;
	
	@Column(name="fechaCreacion")
	private LocalDate fechaCreacion;
	

	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "usuario")
    private List<UsuarioRol> usuarioRoles;
	
	public Usuario() {
	}


	public Usuario(long idUsuario, Empleado empleadoAsociado, String password, String username, String correo,
			boolean enabled, LocalDate fechaCreacion, List<UsuarioRol> usuarioRoles) {
		this.idUsuario = idUsuario;
		this.empleadoAsociado = empleadoAsociado;
		this.password = password;
		this.username = username;
		this.correo = correo;
		this.enabled = enabled;
		this.fechaCreacion = fechaCreacion;
		this.usuarioRoles = usuarioRoles;
	}

	public Usuario(Empleado empleadoAsociado, String password, String username, String correo,
			boolean enabled, LocalDate fechaCreacion, List<UsuarioRol> usuarioRoles) {
		this.empleadoAsociado = empleadoAsociado;
		this.password = password;
		this.username = username;
		this.correo = correo;
		this.enabled = enabled;
		this.fechaCreacion = fechaCreacion;
		this.usuarioRoles = usuarioRoles;
	}



	public long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Empleado getEmpleadoAsociado() {
		return empleadoAsociado;
	}

	public void setEmpleadoAsociado(Empleado empleadoAsociado) {
		this.empleadoAsociado = empleadoAsociado;
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
	
	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
/* 
	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", nombre=" + nombre + ", apellido=" + apellido + ", password="
				+ password + ", username=" + username + ", usuarioRoles=" + usuarioRoles + "]";
	}*/


	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", empleadoAsociado=" + empleadoAsociado + ", password=" + password
				+ ", username=" + username + ", correo=" + correo + ", enabled=" + enabled + ", fechaCreacion="
				+ fechaCreacion + ", usuarioRoles=" + usuarioRoles + "]";
	}	

	
}
		






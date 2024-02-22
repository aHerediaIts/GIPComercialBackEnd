//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.dto;

import com.backendgip.model.Empleado;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;

public class JwtDto {
	private String token;
	private String bearer = "Bearer";
	private String nombreUsuario;
	private Empleado empleado;
	private Collection<? extends GrantedAuthority> authorities;

	public JwtDto(String token, String nombreUsuario, Collection<? extends GrantedAuthority> authorities,
			Empleado empleado) {
		this.token = token;
		this.nombreUsuario = nombreUsuario;
		this.authorities = authorities;
		this.empleado = empleado;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getBearer() {
		return this.bearer;
	}

	public void setBearer(String bearer) {
		this.bearer = bearer;
	}

	public String getNombreUsuario() {
		return this.nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public Empleado getEmpleado() {
		return this.empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
}

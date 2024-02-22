//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.model;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UsuarioPrincipal implements UserDetails {
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String nombreUsuario;
	private String email;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;

	public UsuarioPrincipal(String nombre, String nombreUsuario, String email, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.nombre = nombre;
		this.nombreUsuario = nombreUsuario;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}

	public static UsuarioPrincipal build(Empleado empleado, List<Rol> roles) {
		List<GrantedAuthority> authorities = (List) roles.stream().map((rol) -> {
			return new SimpleGrantedAuthority(rol.getRol());
		}).collect(Collectors.toList());
		return new UsuarioPrincipal(empleado.getNombre(), empleado.getNombreUsuario(), empleado.getEmail(),
				empleado.getPassword(), authorities);
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	public String getPassword() {
		return this.password;
	}

	public String getUsername() {
		return this.nombreUsuario;
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return true;
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
}

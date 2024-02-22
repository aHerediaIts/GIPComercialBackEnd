//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.imp;

import com.backendgip.model.Empleado;
import com.backendgip.model.Rol;
import com.backendgip.model.UsuarioPrincipal;
import com.backendgip.service.EmpleadoService;
import com.backendgip.service.RolService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
	@Autowired
	private EmpleadoService empleadoService;
	@Autowired
	private RolService rolService;

	public UserDetailsServiceImp() {
	}

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Empleado empleado = (Empleado) this.empleadoService.getByNombreUsuario(username).get();
		List<Rol> roles = this.rolService.findRolesByEmpleado(empleado);
		return UsuarioPrincipal.build(empleado, roles);
	}
}

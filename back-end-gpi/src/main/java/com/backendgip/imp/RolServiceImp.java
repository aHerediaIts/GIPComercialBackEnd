//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.imp;

import com.backendgip.model.Empleado;
import com.backendgip.model.EmpleadoRol;
import com.backendgip.model.Rol;
import com.backendgip.repository.RolRepository;
import com.backendgip.service.EmpleadoRolService;
import com.backendgip.service.RolService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolServiceImp implements RolService {
	@Autowired
	private RolRepository rolRepository;
	@Autowired
	private EmpleadoRolService empRolService;

	public RolServiceImp() {
	}

	public Optional<Rol> getByRol(String rol) {
		return this.rolRepository.findByRol(rol);
	}

	public Rol save(Rol rol) {
		return (Rol) this.rolRepository.save(rol);
	}

	public List<Rol> findAll() {
		return (List) this.rolRepository.findAll();
	}

	public List<Rol> findRolesByEmpleado(Empleado empleado) {
		List<EmpleadoRol> empRoles = this.empRolService.findByEmpleado(empleado);
		List<Rol> roles = new ArrayList();
		Iterator var4 = empRoles.iterator();

		while (var4.hasNext()) {
			EmpleadoRol e = (EmpleadoRol) var4.next();
			roles.add(e.getRol());
		}

		return roles;
	}

	public Rol findById(Integer idRol) {
		return (Rol) this.rolRepository.findById(idRol).get();
	}
}

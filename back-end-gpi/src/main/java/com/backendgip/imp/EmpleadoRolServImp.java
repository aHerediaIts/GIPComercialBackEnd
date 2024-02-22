//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.imp;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.Empleado;
import com.backendgip.model.EmpleadoRol;
import com.backendgip.model.Rol;
import com.backendgip.repository.EmpleadoRolRepository;
import com.backendgip.service.EmpleadoRolService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoRolServImp implements EmpleadoRolService {
	@Autowired
	private EmpleadoRolRepository rolRepository;

	public EmpleadoRolServImp() {
	}

	public EmpleadoRol save(EmpleadoRol rol) {
		return (EmpleadoRol) this.rolRepository.save(rol);
	}

	public List<EmpleadoRol> findAll() {
		return (List) this.rolRepository.findAll();
	}

	public List<EmpleadoRol> findByEmpleado(Empleado empleado) {
		return this.rolRepository.findByEmpleado(empleado);
	}

	public EmpleadoRol findById(Integer id) {
		return (EmpleadoRol) this.rolRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("EmpleadoRol no encontrado con id: " + id);
		});
	}

	public void delete(EmpleadoRol rol) {
		this.rolRepository.delete(rol);
	}

	public List<EmpleadoRol> findByRol(Rol rol) {
		return this.rolRepository.findByRol(rol);
	}
}

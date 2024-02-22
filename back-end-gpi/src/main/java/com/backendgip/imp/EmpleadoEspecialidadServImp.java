//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.imp;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.Empleado;
import com.backendgip.model.EmpleadoEspecialidad;
import com.backendgip.repository.EmpleadoEspecialidadRepository;
import com.backendgip.service.EmpleadoEspecialidadService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoEspecialidadServImp implements EmpleadoEspecialidadService {
	@Autowired
	private EmpleadoEspecialidadRepository empleadoRepository;

	public EmpleadoEspecialidadServImp() {
	}

	public List<EmpleadoEspecialidad> getEmpleado() {
		return (List) this.empleadoRepository.findAll();
	}

	public EmpleadoEspecialidad saveEmpleado(EmpleadoEspecialidad empleado) {
		return (EmpleadoEspecialidad) this.empleadoRepository.save(empleado);
	}

	public void deleteEmpleado(EmpleadoEspecialidad estado) {
		this.empleadoRepository.delete(estado);
	}

	public EmpleadoEspecialidad getEmpleadoById(Integer id) {
		return (EmpleadoEspecialidad) this.empleadoRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado el recurso solicitado.");
		});
	}

	public List<EmpleadoEspecialidad> findByEmpleado(Empleado empleado) {
		return this.empleadoRepository.findByEmpleado(empleado);
	}

	public void deleteListByEmpleado(Empleado empleado) {
		List<EmpleadoEspecialidad> toDelete = this.empleadoRepository.findByEmpleado(empleado);
		this.empleadoRepository.deleteAll(toDelete);
	}
}

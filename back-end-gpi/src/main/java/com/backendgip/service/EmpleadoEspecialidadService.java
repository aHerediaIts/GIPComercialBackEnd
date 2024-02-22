//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.service;

import com.backendgip.model.Empleado;
import com.backendgip.model.EmpleadoEspecialidad;
import java.util.List;

public interface EmpleadoEspecialidadService {
	List<EmpleadoEspecialidad> getEmpleado();

	EmpleadoEspecialidad saveEmpleado(EmpleadoEspecialidad empleado);

	void deleteEmpleado(EmpleadoEspecialidad empleado);

	EmpleadoEspecialidad getEmpleadoById(Integer id);

	List<EmpleadoEspecialidad> findByEmpleado(Empleado empleado);

	void deleteListByEmpleado(Empleado empleado);
}

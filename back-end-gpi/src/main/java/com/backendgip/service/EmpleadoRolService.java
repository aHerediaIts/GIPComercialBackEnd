//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.service;

import com.backendgip.model.Empleado;
import com.backendgip.model.EmpleadoRol;
import com.backendgip.model.Rol;
import java.util.List;

public interface EmpleadoRolService {
	EmpleadoRol save(EmpleadoRol rol);

	List<EmpleadoRol> findAll();

	List<EmpleadoRol> findByEmpleado(Empleado empleado);

	EmpleadoRol findById(Integer id);

	void delete(EmpleadoRol rol);

	List<EmpleadoRol> findByRol(Rol rol);
}

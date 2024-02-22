//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.service;

import com.backendgip.model.DependenciaEmpleado;
import java.util.List;

public interface DependenciaEmpleadoService {
	List<DependenciaEmpleado> getDependencia();

	DependenciaEmpleado saveDependencia(DependenciaEmpleado dependencia);

	void deleteDependencia(DependenciaEmpleado dependencia);

	DependenciaEmpleado getDependenciaById(Integer id);
}

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.service;

import com.backendgip.model.Especialidad;
import java.util.List;

public interface EspecialidadService {
	List<Especialidad> getEspecialidad();

	Especialidad saveEspecialidad(Especialidad especialidad);

	void deleteEspecialidad(Especialidad especialidad);

	Especialidad getEspecialidadById(Integer id);
}

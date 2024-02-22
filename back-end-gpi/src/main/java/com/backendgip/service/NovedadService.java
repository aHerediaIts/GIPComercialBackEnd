//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.service;

import com.backendgip.model.Empleado;
import com.backendgip.model.Novedad;
import java.util.List;

public interface NovedadService {
	List<Novedad> findAll();

	Novedad save(Novedad novedad);

	void delete(Novedad novedad);

	Novedad findById(Integer id);

	List<Novedad> findByEmpleado(Empleado empleado);
}

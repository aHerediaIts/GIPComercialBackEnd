//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.service;

import com.backendgip.model.EstadoEmpleado;
import java.util.List;

public interface EstadoEmpleadoService {
	List<EstadoEmpleado> getEstado();

	EstadoEmpleado saveEstado(EstadoEmpleado estado);

	void deleteEstado(EstadoEmpleado estado);

	EstadoEmpleado getEstadoById(Integer id);
}

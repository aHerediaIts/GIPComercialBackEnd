//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.service;

import com.backendgip.model.EstadoProyecto;
import java.util.List;

public interface EstadoProyectoService {
	List<EstadoProyecto> getEstados();

	EstadoProyecto saveEstado(EstadoProyecto estado);

	void deleteEstado(EstadoProyecto estado);

	EstadoProyecto getEstadoById(Integer id);

	List<EstadoProyecto> getEstadosPRP();

	List<EstadoProyecto> getEstadosCRN();
}

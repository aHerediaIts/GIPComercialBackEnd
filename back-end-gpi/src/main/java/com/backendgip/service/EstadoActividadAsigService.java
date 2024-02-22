//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.service;

import com.backendgip.model.EstadoActividadAsig;
import java.util.List;

public interface EstadoActividadAsigService {
	List<EstadoActividadAsig> getEstados();

	EstadoActividadAsig saveEstado(EstadoActividadAsig estado);

	void deleteEstado(EstadoActividadAsig estado);

	EstadoActividadAsig getEstadoById(Integer idEstado);
}

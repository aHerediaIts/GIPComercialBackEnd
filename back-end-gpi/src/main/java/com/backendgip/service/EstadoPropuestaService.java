//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.service;

import com.backendgip.model.EstadoPropuesta;
import java.util.List;

public interface EstadoPropuestaService {
	List<EstadoPropuesta> getEstados();

	EstadoPropuesta saveEstado(EstadoPropuesta estado);

	void deleteEstado(EstadoPropuesta estado);

	EstadoPropuesta getEstadoById(Integer id);
}

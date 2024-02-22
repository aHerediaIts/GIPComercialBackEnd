//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.service;

import com.backendgip.model.EstadoCliente;
import java.util.List;

public interface EstadoClienteService {
	List<EstadoCliente> getEstados();

	EstadoCliente saveEstado(EstadoCliente estado);

	void deleteEstado(EstadoCliente estado);

	EstadoCliente getEstadoById(Integer idEstado);
}

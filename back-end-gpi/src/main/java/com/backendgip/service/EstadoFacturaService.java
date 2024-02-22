//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.service;

import com.backendgip.model.EstadoFactura;
import java.util.List;

public interface EstadoFacturaService {
	List<EstadoFactura> getEstadoFactura();

	EstadoFactura saveEstadoFactura(EstadoFactura estadoFactura);

	void deleteEstadoFactura(EstadoFactura estadoFactura);

	EstadoFactura getEstadoFacturaById(Integer idEstadoFactura);
}

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.service;

import com.backendgip.model.Facturacion;
import com.backendgip.model.Proyecto;
import java.util.List;

public interface FacturacionService {
	List<Facturacion> getFacturacion();

	Facturacion saveFacturacion(Facturacion facturacion);

	void deleteFacturacion(Facturacion facturacion);

	Facturacion getFacturacionById(Integer id);

	List<Facturacion> getCobrosByProyecto(Proyecto proyecto);
}

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.service;

import com.backendgip.model.EstadoReporteTiempo;
import java.util.List;

public interface EstadoReporteTiempoService {
	List<EstadoReporteTiempo> getEstadoReporteTiempo();

	EstadoReporteTiempo saveEstadoReporteTiempo(EstadoReporteTiempo estadoReporteTiempo);

	void deleteEstadoReporteTiempo(EstadoReporteTiempo estadoReporteTiempo);

	EstadoReporteTiempo getEstadoReporteTiempoById(Integer idEstadoReporteTiempo);
}

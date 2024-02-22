//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.imp;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.EstadoReporteTiempo;
import com.backendgip.repository.EstadoReporteTiempoRepository;
import com.backendgip.service.EstadoReporteTiempoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadoReporteTiempoServImp implements EstadoReporteTiempoService {
	@Autowired
	private EstadoReporteTiempoRepository estadoReporteTiempoRepository;

	public EstadoReporteTiempoServImp() {
	}

	public List<EstadoReporteTiempo> getEstadoReporteTiempo() {
		List<EstadoReporteTiempo> estadoReporteTiempo = (List) this.estadoReporteTiempoRepository.findAll();
		return estadoReporteTiempo;
	}

	public EstadoReporteTiempo saveEstadoReporteTiempo(EstadoReporteTiempo estadoReporteTiempo) {
		return (EstadoReporteTiempo) this.estadoReporteTiempoRepository.save(estadoReporteTiempo);
	}

	public void deleteEstadoReporteTiempo(EstadoReporteTiempo estadoReporteTiempo) {
		this.estadoReporteTiempoRepository.delete(estadoReporteTiempo);
	}

	public EstadoReporteTiempo getEstadoReporteTiempoById(Integer idEstadoReporteTiempo) {
		return (EstadoReporteTiempo) this.estadoReporteTiempoRepository.findById(idEstadoReporteTiempo)
				.orElseThrow(() -> {
					return new ResourceNotFoundException(
							"No se ha encontrado el estado reporte tiempo con el id: " + idEstadoReporteTiempo);
				});
	}
}

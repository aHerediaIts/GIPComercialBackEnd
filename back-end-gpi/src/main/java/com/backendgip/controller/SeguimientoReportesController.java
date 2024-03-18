package com.backendgip.controller;

import com.backendgip.model.TipoReporte;

import com.backendgip.repository.TipoReporteRepository;
import com.backendgip.service.ProyectoService;
import com.backendgip.service.TipoReporteService;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.backendgip.model.RecursoActividad;
import com.backendgip.service.RecursoActividadService;

@RestController
@Transactional
@RequestMapping({ "/api/reportes" })
public class SeguimientoReportesController {
    @Autowired
    private RecursoActividadService recursoActividadService;
    
	@Autowired
	private TipoReporteRepository tipoRepository;

    public SeguimientoReportesController(){
    }

    @GetMapping({ "/tipos" })
	public List<TipoReporte> getAllTipos() {
		return (List) this.tipoRepository.findAll();
	}

	@GetMapping("/reporte")
	public List<RecursoActividad> getAllProyectos() {
		List<RecursoActividad> reportesentrada = this.recursoActividadService.getRecursoActividad();
		List<RecursoActividad> reportesInactivos = new ArrayList<>();
		
		for (RecursoActividad reporte : reportesentrada) {
			// Verificar si el proyecto está inactivo (estado "CERRADO")
			if ("CERRADO".equals(reporte.getActividad().getProyecto().getEstadoProyecto().getEstado())) {
				reportesInactivos.add(reporte); // Agregar el proyecto a la lista de inactivos
			}
		}
		
		return reportesInactivos; // Devolver solo los proyectos inactivos
	}
	 

	
}
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
import com.backendgip.model.Proyecto;
import com.backendgip.repository.ProyectoRepository;
import com.backendgip.service.ProyectoService;

@RestController
@Transactional
@RequestMapping({ "/api/reportes" })
public class SeguimientoReportesController {
    @Autowired
    private RecursoActividadService recursoActividadService;
	@Autowired
	private TipoReporteRepository tipoRepository;
    @Autowired
    private ProyectoRepository pRepository;
    @Autowired
    private ProyectoService proyectoService;

    public SeguimientoReportesController(){
    }

    @GetMapping({ "/tipos" })
	public List<TipoReporte> getAllTipos() {
		return (List) this.tipoRepository.findAll();
	}

    @GetMapping("/reporte/inactivos")
	public List<RecursoActividad> getReporteInactivo() {
        List<RecursoActividad> reportesentrada = this.recursoActividadService.getRecursoActividad();
        List<RecursoActividad> resportessalida = new ArrayList<>();
        for(RecursoActividad reportes: reportesentrada){
            if(reportes.getActividad().getEstado().getId() == 4){
                resportessalida.add(reportes); 
            }
        }
        return reportesentrada;
    }

    @GetMapping("/proyectos")
	public List<Proyecto> getlistProyectos() {
        List<Proyecto> proyectos = (List) this.pRepository.findAll();
        List<Proyecto> proyectossalida = new ArrayList<>();
        for(Proyecto proyecto: proyectos){
            if(proyecto.getRfProyecto() != null){
                proyectossalida.add(proyecto); 
            }
        }
        return proyectossalida;
    }
}

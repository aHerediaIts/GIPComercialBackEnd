package com.backendgip.controller;

import com.backendgip.model.TipoReporte;

import com.backendgip.repository.TipoReporteRepository;
import com.backendgip.service.ProyectoService;
import com.backendgip.service.TipoReporteService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.backendgip.model.RecursoActividad;
import com.backendgip.service.RecursoActividadService;
import com.backendgip.model.Proyecto;
import com.backendgip.repository.ProyectoRepository;
import com.backendgip.service.ProyectoService;
import com.backendgip.service.ActividadAsignadaService;
import com.backendgip.model.ActividadAsignada;

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
	private ActividadAsignadaService actividadAsignada;
	@Autowired
	private ProyectoService proyectoservice; 

    public SeguimientoReportesController(){
    }

    @GetMapping({ "/tipos" })
	public List<TipoReporte> getAllTipos() {
		return (List) this.tipoRepository.findAll();
	}

    @GetMapping("/reporte/inactivos/{fechaInicio}/{fechaFin}/{rf_proyecto}")
	public List<RecursoActividad> getReporteInactivo(@PathVariable String rf_proyecto, @PathVariable String fechaInicio, @PathVariable String fechaFin) {
		List<RecursoActividad> resportessalida = new ArrayList<>();
		List<RecursoActividad> recursosActividad = this.buscarActividades(rf_proyecto, fechaInicio, fechaFin);
		for(RecursoActividad recursos_actividad: recursosActividad){
			if(recursos_actividad.getActividad().getProyecto().getEstadoProyecto().getId() == 8 || "CERRADO".equals(recursos_actividad.getActividad().getProyecto().getEstadoProyecto().getEstado())){
				resportessalida.add( recursos_actividad );
			}
		}
        return resportessalida;
    }

	@GetMapping("/reporte/anual/{fechaInicio}/{fechaFin}/{rf_proyecto}")
	public List<RecursoActividad> getAllProyectosAnuales(@PathVariable String rf_proyecto, @PathVariable String fechaInicio, @PathVariable String fechaFin) {
		List<RecursoActividad> resportessalida = new ArrayList<>();
		List<RecursoActividad> recursosActividad = this.buscarActividades(rf_proyecto, fechaInicio, fechaFin);
		for(RecursoActividad recursos_actividad: recursosActividad){
				resportessalida.add(recursos_actividad);
		}
		return resportessalida;
	}

	@GetMapping("/reporte/alfa/{fechaInicio}/{fechaFin}/{rf_proyecto}")
	public List<RecursoActividad> getAllProyectosIngenierosyalfa(@PathVariable String rf_proyecto, @PathVariable String fechaInicio, @PathVariable String fechaFin) {
		List<RecursoActividad> resportessalida = new ArrayList<>();
		List<RecursoActividad> recursosActividad = this.buscarActividades(rf_proyecto, fechaInicio, fechaFin);
		for(RecursoActividad recursos_actividad: recursosActividad){
			if ("CAFAM".equals(recursos_actividad.getActividad().getProyecto().getCliente().getNombre())) {
				resportessalida.add(recursos_actividad);
			}
		}
        return resportessalida;
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

	 public LocalDate stringToLocalDate(String string) {
        String[] fechaInicioArray = string.split("-");
        LocalDate fechaI = LocalDate.of(Integer.parseInt(fechaInicioArray[0]), Integer.parseInt(fechaInicioArray[1]),
                Integer.parseInt(fechaInicioArray[2]));
        return fechaI;
    }

	public List<RecursoActividad> buscarActividades( String rf_proyecto, String fechaInicio, String fechaFin){
		List<RecursoActividad> recursosActividad = new ArrayList<>();
		LocalDate fechaI = this.stringToLocalDate(fechaInicio);
		LocalDate fechaF = this.stringToLocalDate(fechaFin);
		List<Proyecto> proyectoecontrado = this.proyectoservice.getProyectos();
		for(Proyecto proyecto: proyectoecontrado){
			if( rf_proyecto.equals(proyecto.getRfProyecto())){
				List<ActividadAsignada> Actividades = this.actividadAsignada.getActividadFechasProyecto(fechaI, fechaF, proyecto);
				for(ActividadAsignada actividad: Actividades){
					recursosActividad= this.recursoActividadService.findByActividad(actividad);
			    }
		    }
		}
        return recursosActividad;
	}
}

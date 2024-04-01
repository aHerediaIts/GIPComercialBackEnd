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
import com.backendgip.model.ReporteTiempo;
import com.backendgip.service.RecursoActividadService;
import com.backendgip.service.ReporteTiempoService;
import com.backendgip.model.Proyecto;
import com.backendgip.repository.ProyectoRepository;
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
	@Autowired
    private ReporteTiempoService reporteTiempoService;

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
				resportessalida.add(recursos_actividad);
		}
        return resportessalida;
	}

	@GetMapping("/reporte/control-horas/{fechaInicio}/{fechaFin}")
	public List<ReporteTiempo> getReporteTiempo(@PathVariable String fechaInicio,@PathVariable String fechaFin) {
		LocalDate fechaI = this.stringToLocalDate(fechaInicio);
		LocalDate fechaF = this.stringToLocalDate(fechaFin);
		LocalDate fechaActual = fechaI.plusDays(1);
		List<ReporteTiempo> reportesSalida = new ArrayList<>();
		List<ReporteTiempo> reporte = this.reporteTiempoService.getReporteTiempoFechaAfter(fechaActual);
			System.out.println(fechaActual);
			System.out.println(reporte);
			for(ReporteTiempo report: reporte){
				if( report != null){
					reportesSalida.add(report);
				}
			}

        while (!fechaActual.isAfter(fechaF)) {
			List<ReporteTiempo> reportes = this.reporteTiempoService.getReporteTiempoFechaAfter(fechaActual);
			System.out.println(fechaActual);
			System.out.println(reportes);
			for(ReporteTiempo report: reportes){
				if( report != null){
					reportesSalida.add(report);
				}
			}
			fechaActual = fechaActual.plusDays(1);
        }
		return reportesSalida;
	}

	@GetMapping("/proyectos")
	public List<String> getlistProyectos() {
        List<Proyecto> proyectos = (List) this.pRepository.findAll();
        List<String> proyectossalida = new ArrayList<>();
        for(Proyecto proyecto: proyectos){
            if(proyecto.getRfProyecto() != null){
                proyectossalida.add(proyecto.getRfProyecto()); 
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
		List<RecursoActividad> recursosecontrados = new ArrayList<>();
		LocalDate fechaI = this.stringToLocalDate(fechaInicio);
		LocalDate fechaF = this.stringToLocalDate(fechaFin);
		List<Proyecto> proyectos = this.proyectoservice.getProyectos();
		
		for(Proyecto proyecto: proyectos){
			if( rf_proyecto.equals(proyecto.getRfProyecto())){
				List<ActividadAsignada> Actividades = this.actividadAsignada.getActividadFechasProyecto(fechaI, fechaF, proyecto);
				for(ActividadAsignada actividad: Actividades){
					List<RecursoActividad> recursosActividad= this.recursoActividadService.findByActividad(actividad);
					for(RecursoActividad recursos: recursosActividad){
						if(recursos != null){
							recursosecontrados.add( recursos );
						}
					}
			    }
		    }
			if( "VACIO".equals(rf_proyecto)){
				System.out.println("entra");
				List<ActividadAsignada> Actividades = this.actividadAsignada.getActividadFechasProyecto(fechaI, fechaF, proyecto);
				for(ActividadAsignada actividad: Actividades){
						List<RecursoActividad> recursosActividad = this.recursoActividadService.findByActividad(actividad);	
					for(RecursoActividad recursos: recursosActividad){
						if(recursos != null){
							recursosecontrados.add( recursos );
						}
					}	
			    }
			}
		}
		
        return recursosecontrados;
	}

	public  List<LocalDate> recorrerFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        List<LocalDate> fechasRecorridas = new ArrayList<>();

        fechasRecorridas.add(fechaInicio);
        
        LocalDate fechaActual = fechaInicio.plusDays(1); // Empezar desde el día siguiente a la fecha de inicio
        while (!fechaActual.isAfter(fechaFin)) {
			System.out.println(fechaActual);
            fechasRecorridas.add(fechaActual);
            fechaActual = fechaActual.plusDays(1);
        }
        System.out.println(fechasRecorridas);
        return fechasRecorridas;
    }

	
}

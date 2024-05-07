package com.backendgip.controller;

import com.backendgip.model.TipoReporte;
import com.backendgip.model.ValorTotalRecurso;
import com.backendgip.repository.TipoReporteRepository;
import com.backendgip.service.ProyectoService;
import com.backendgip.service.TipoReporteService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.formula.ptg.Ptg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.backendgip.model.RecursoActividad;
import com.backendgip.model.ReporteAnual;

import com.backendgip.model.ActividadProyectoVencer;
import com.backendgip.model.ReporteControlHoras;
import com.backendgip.model.ReporteTiempo;
import com.backendgip.service.RecursoActividadService;
import com.backendgip.service.ReporteTiempoService;
import com.backendgip.model.Proyecto;
import com.backendgip.repository.ProyectoRepository;
import com.backendgip.service.ActividadAsignadaService;
import com.backendgip.service.ParametriaRecursosMatrizTiempoService;
import com.backendgip.model.ActividadAsignada;
import com.backendgip.model.ParametriaRecursosMatrizTiempo;

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
	@Autowired
    private ParametriaRecursosMatrizTiempoService parametriaRecursosMatrizTiempoService;

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
			if("CERRADO".equals(recursos_actividad.getActividad().getProyecto().getEstadoProyecto().getEstado()) ||
			"FINALIZADO".equals(recursos_actividad.getActividad().getProyecto().getEstadoProyecto().getEstado()) ||
			"CANCELADO".equals(recursos_actividad.getActividad().getProyecto().getEstadoProyecto().getEstado())){
				resportessalida.add( recursos_actividad );
			}
		}
        return resportessalida;
    }

	@GetMapping("/reporte/anual/{fechaInicio}/{fechaFin}/{rf_proyecto}")
	public List<Proyecto> getAllProyectosAnuales(@PathVariable String rf_proyecto, @PathVariable String fechaInicio, @PathVariable String fechaFin) {
		LocalDate fechaI = this.stringToLocalDate(fechaInicio);
		LocalDate fechaF = this.stringToLocalDate(fechaFin);
		LocalDate fechaActual = fechaI;
		List<Proyecto> resportesSalida = new ArrayList<>();
		List<Proyecto> reportesFecha = new ArrayList<>();
		List<Proyecto> proyectosRf = this.proyectoservice.findByRfProyecto(rf_proyecto);
		if( "VACIO".equals(rf_proyecto)){
			while (!fechaActual.isAfter(fechaF)) {
				reportesFecha = this.proyectoservice.getFechaInicioList(fechaActual);
				for(Proyecto reporte: reportesFecha){
					if(reporte != null){
						
							resportesSalida.add(reporte);
						
					}
				}
				fechaActual = fechaActual.plusDays(1);	
			}
		}else{
			while (!fechaActual.isAfter(fechaF)) {
				reportesFecha = this.proyectoservice.getFechaProyectoInicioList(fechaI, proyectosRf);
				for(Proyecto reporte: reportesFecha){
					if(reporte != null){
						
							resportesSalida.add(reporte);

					}
				}
				fechaActual = fechaActual.plusDays(1);	
			}
		}
		return resportesSalida;
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

	@GetMapping("/reporte/control-horas/{fechaInicio}/{fechaFin}/{rf_proyecto}")
	public List<ReporteControlHoras> getReporteTiempo(@PathVariable String rf_proyecto, @PathVariable String fechaInicio, @PathVariable String fechaFin) {
		LocalDate fechaI = this.stringToLocalDate(fechaInicio);
		LocalDate fechaF = this.stringToLocalDate(fechaFin);
		LocalDate fechaActual = fechaI;
		LocalDate fechaValidadorMes = fechaI;
		List<ReporteControlHoras> reportesSalida = new ArrayList<>();
		List<ReporteTiempo> reportesFecha = new ArrayList<>();
		List<ReporteTiempo> reportes = new ArrayList<>();
		List<Proyecto> proyectos = this.proyectoservice.findByRfProyecto(rf_proyecto);

		if( "VACIO".equals(rf_proyecto)){
			while (!fechaActual.isAfter(fechaF)) {
				reportes = this.reporteTiempoService.getReporteTiempoFecha(fechaActual);
				for(ReporteTiempo reporte: reportes){
					if(reporte != null){
						reportesFecha.add(reporte);
					}
				}
				fechaActual = fechaActual.plusDays(1);	
			}
			
		}else{
			while (!fechaActual.isAfter(fechaF)) {
				reportes = this.reporteTiempoService.getReporteTiempoFechaRf(fechaActual, proyectos);	
				for(ReporteTiempo reporte: reportes){
					if(reporte != null){
						reportesFecha.add(reporte);
					}
				}				
				fechaActual = fechaActual.plusDays(1);
			}
		}
		while (!fechaValidadorMes.isAfter(fechaF)) {
			int valorTotal;
			for (int i = 0; i < reportesFecha.size(); i++) {
				ReporteControlHoras controlHoras =  new ReporteControlHoras();
				valorTotal = 0;
				ReporteTiempo reporteActual = reportesFecha.get(i);
				valorTotal = reporteActual.getHoras();
				for (int j = i + 1; j < reportesFecha.size(); j++) {
					ReporteTiempo otroReporte = reportesFecha.get(j);
					if(reporteActual.getFecha().getMonth() == otroReporte.getFecha().getMonth()){
						if (reporteActual.getEmpleado() == otroReporte.getEmpleado()) {
							if(reporteActual.getProyecto() == otroReporte.getProyecto()){
								valorTotal = valorTotal + otroReporte.getHoras();
								reportesFecha.remove(j);
							}
						}
					}	
				}
				controlHoras.setReporte(reporteActual);
				controlHoras.setTotalHoras(valorTotal);
				reportesFecha.remove(i);
				reportesSalida.add(controlHoras);
			}
			fechaValidadorMes = fechaValidadorMes.plusDays(1);
		}

		return reportesSalida;
	}

	@GetMapping("/reporte/control-total-horas/{fechaInicio}/{fechaFin}/{rf_proyecto}")
	public List<ValorTotalRecurso> getReportesTotales(@PathVariable String rf_proyecto, @PathVariable String fechaInicio, @PathVariable String fechaFin) {
    LocalDate fechaI = this.stringToLocalDate(fechaInicio);
    LocalDate fechaF = this.stringToLocalDate(fechaFin);
    LocalDate fechaActual = fechaI;
    List<ValorTotalRecurso> reportesSalida = new ArrayList<>();
    List<ReporteTiempo> reportes = new ArrayList<>();
    List<Proyecto> proyectos = this.proyectoservice.findByRfProyecto(rf_proyecto);
    List<ParametriaRecursosMatrizTiempo> ParametriaRecursosMatrizTiempo = this.parametriaRecursosMatrizTiempoService.getParametriaRecursos();

    if ("VACIO".equals(rf_proyecto)) {
        while (!fechaActual.isAfter(fechaF)) {
            reportes = this.reporteTiempoService.getReporteTiempoFecha(fechaActual);
            for (ReporteTiempo reporte : reportes) {
                ValorTotalRecurso valorTotalRecurso = new ValorTotalRecurso();
                valorTotalRecurso.setReporteTiempo(reporte);
                for (ParametriaRecursosMatrizTiempo parametria : ParametriaRecursosMatrizTiempo) {
                    if (reporte.getEmpleado().equals(parametria.getEmpleado())) {
                        double total = parametria.getTarifaHora() * reporte.getHoras();
                        valorTotalRecurso.setTotal(total);
                        break; 
                    }
                }
                reportesSalida.add(valorTotalRecurso);
            }
            fechaActual = fechaActual.plusDays(1);
        }
    } else {
        while (!fechaActual.isAfter(fechaF)) {
            reportes = this.reporteTiempoService.getReporteTiempoFechaRf(fechaActual, proyectos);
            for (ReporteTiempo reporte : reportes) {
                ValorTotalRecurso valorTotalRecurso = new ValorTotalRecurso();
                valorTotalRecurso.setReporteTiempo(reporte);
                for (ParametriaRecursosMatrizTiempo parametria : ParametriaRecursosMatrizTiempo) {
                    if (reporte.getEmpleado().equals(parametria.getEmpleado())) {
                        double total = parametria.getTarifaHora() * reporte.getHoras();
                        valorTotalRecurso.setTotal(total);
                        break; 
                    }
                }
                reportesSalida.add(valorTotalRecurso);
            }
            fechaActual = fechaActual.plusDays(1);
        }
    }
    return reportesSalida;
	}
	
	@GetMapping("/reporte/actividad-proyecto-vencer/{fechaInicio}/{fechaFin}/{rf_proyecto}")
	public List<ActividadProyectoVencer> getActividadesProyectoVencer(
		@PathVariable String rf_proyecto,
		@PathVariable String fechaInicio,
		@PathVariable String fechaFin
	) {
		// Obtener recursos de actividad según los criterios de filtro
		List<RecursoActividad> recursosActividad = buscarActividades(rf_proyecto, fechaInicio, fechaFin);
	
		// Lista para almacenar las actividades de proyecto que cumplen con los criterios
		List<ActividadProyectoVencer> actividadesProyectoVencer = new ArrayList<>();
	
		// Iterar sobre los recursos de actividad
		for (RecursoActividad recursoActividad : recursosActividad) {
			// Obtener el proyecto asociado al recurso de actividad
			Proyecto proyecto = recursoActividad.getActividad().getProyecto();
			// Verificar si el proyecto coincide con el filtro de rf_proyecto
			if ("VACIO".equals(rf_proyecto) || proyecto.getRfProyecto().equals(rf_proyecto)) {
				// Verificar si la fecha de inicio de la actividad está dentro del rango especificado
				LocalDate fechaInicioActividad = recursoActividad.getActividad().getFechaInicio();
				LocalDate fechaFinActividad = recursoActividad.getActividad().getFechaFin();
				LocalDate fechaInicioFiltro = LocalDate.parse(fechaInicio);
				LocalDate fechaFinFiltro = LocalDate.parse(fechaFin);
				if (fechaInicioActividad.isEqual(fechaInicioFiltro) || fechaInicioActividad.isAfter(fechaInicioFiltro)) {
					if (fechaFinActividad.isEqual(fechaFinFiltro) || fechaFinActividad.isBefore(fechaFinFiltro)) {
						// Agregar la actividad de proyecto junto con el recurso de actividad a la lista de resultados
						actividadesProyectoVencer.add(new ActividadProyectoVencer(proyecto, recursoActividad));
					}
				}
			}
		}
	
		return actividadesProyectoVencer;
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
            fechasRecorridas.add(fechaActual);
            fechaActual = fechaActual.plusDays(1);
        }
        return fechasRecorridas;
    }

	
}

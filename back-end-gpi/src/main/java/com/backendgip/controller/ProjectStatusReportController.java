//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.controller;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.Cargo;
import com.backendgip.model.ComponenteDesarrollo;
import com.backendgip.model.Empleado;
import com.backendgip.model.LogSistema;
import com.backendgip.model.PSRStatus;
import com.backendgip.model.ProjectStatusReport;
import com.backendgip.model.ProjectStatusReportComentarios;
import com.backendgip.model.Proyecto;
import com.backendgip.service.EmpleadoService;
import com.backendgip.service.LogSistemaService;
import com.backendgip.service.PSRStatusService;
import com.backendgip.service.ProjectStatusReportService;
import com.backendgip.service.ProyectoService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
@RequestMapping({ "/api" })
public class ProjectStatusReportController {
    @Autowired
    private ProjectStatusReportService psrservice;
    @Autowired
    private LogSistemaService logService;
    @Autowired
    private PSRStatusService PSRStatusService;
    @Autowired
    private ProyectoService proyectoService;
    @Autowired
    private EmpleadoService empleadoService;

    String edad;

    public LocalDate stringToLocalDate(String string) {
        String[] fechaInicioArray = string.split("-");
        LocalDate fechaI = LocalDate.of(Integer.parseInt(fechaInicioArray[0]), Integer.parseInt(fechaInicioArray[1]),
                Integer.parseInt(fechaInicioArray[2]));
        return fechaI;
    }

    public ProjectStatusReportController() {
    }

    @GetMapping({ "/projectstatusreports" })
    public List<ProjectStatusReport> getProjectStatusReports() {
        return this.psrservice.getProjectStatusReports();
    }

    @GetMapping({ "/projectstatusreports/getProjectsForPsr" })
    public List<Proyecto> getProjectsForPsr() {
        List<Proyecto> proyectos = this.proyectoService.getProyectos();
        List<Proyecto> proyectosActivos = new ArrayList();
        for (Proyecto proyecto : proyectos) {
            if (proyecto.getEstadoProyecto().getId() != 6 && proyecto.getEstadoProyecto().getId() != 8 && proyecto.getEstadoProyecto().getId() != 9) {
                proyectosActivos.add(proyecto);
            }
        }
        return proyectosActivos;
    }

    @GetMapping({ "/projectstatusreports/getProjectsForPsrFechaInicioFechaFin/{fechaInicio}/{fechaFin}" })
    public List<Proyecto> getProjectsForPsrFechaInicioFechaFin(@PathVariable String fechaInicio,@PathVariable String fechaFin) {

        LocalDate fechaI = this.stringToLocalDate(fechaInicio);
        LocalDate fechaF = this.stringToLocalDate(fechaFin);
		List<Proyecto> psr = (List<Proyecto>) this.getProjectsForPsr();
		List<Proyecto> proyectosFiltradosPorFechaFiltroFechaNula = new ArrayList();
		List<Proyecto> proyectosFiltradosPorFecha = new ArrayList();

		for (Proyecto proyecto : psr) {
			if(proyecto.getFechaInicio() != null && proyecto.getFechaFin() != null){
			proyectosFiltradosPorFechaFiltroFechaNula.add(proyecto);
			}}

		for (Proyecto proyecto : proyectosFiltradosPorFechaFiltroFechaNula) {
			if((proyecto.getFechaInicio().equals(fechaI) && proyecto.getFechaFin().equals(fechaF)) || (proyecto.getFechaInicio().isAfter(fechaI) && proyecto.getFechaFin().isBefore(fechaF))){
				proyectosFiltradosPorFecha.add(proyecto);
			}}
		return 	proyectosFiltradosPorFecha;
    }


    @GetMapping({ "/projectstatusreports/getProjectsByLiderAsignadoForPsr/{idEmpleado}" })
    public List<Proyecto> getProjectsByLiderAsignadoForPsr(@PathVariable Integer idEmpleado) {
        Empleado lider = this.empleadoService.getEmpleadoById(idEmpleado);
        List<Proyecto> proyectos = this.proyectoService.findByLider(lider);
        List<Proyecto> proyectosActivos = new ArrayList();
        for (Proyecto proyecto : proyectos) {
            if (proyecto.getEstadoProyecto().getId() != 6 && proyecto.getEstadoProyecto().getId() != 8 && proyecto.getEstadoProyecto().getId() != 9) {
                proyectosActivos.add(proyecto);
            }
        }
        return proyectosActivos;
    }

    @GetMapping({ "/projectstatusreports/getProjectsByLiderAsignadoForPsr/searchBetweenFechaInicioAndFechaFin/{fechaInicio}/{fechaFin}/{idEmpleado}" })
    public List<Proyecto>getProjectsByLiderAsignadoForPsrFechaInicioFechaFin(@PathVariable String fechaInicio,@PathVariable String fechaFin,@PathVariable Integer idEmpleado) {

        LocalDate fechaI = this.stringToLocalDate(fechaInicio);
        LocalDate fechaF = this.stringToLocalDate(fechaFin);
		Empleado lider = this.empleadoService.getEmpleadoById(idEmpleado);
        List<Proyecto> psr = this.proyectoService.findByLider(lider);
		List<Proyecto> proyectosFiltradosPorFechaFiltroFechaNula = new ArrayList();
		List<Proyecto> proyectosFiltradosPorFecha = new ArrayList();

		for (Proyecto proyecto : psr) {
			if(proyecto.getFechaInicio() != null && proyecto.getFechaFin() != null){
			proyectosFiltradosPorFechaFiltroFechaNula.add(proyecto);
			}}

		for (Proyecto proyecto : proyectosFiltradosPorFechaFiltroFechaNula) {
			if((proyecto.getFechaInicio().equals(fechaI) && proyecto.getFechaFin().equals(fechaF)) || (proyecto.getFechaInicio().isAfter(fechaI) && proyecto.getFechaFin().isBefore(fechaF))){
				proyectosFiltradosPorFecha.add(proyecto);
			}}
		return 	proyectosFiltradosPorFecha;
    }

    @GetMapping({ "/projectstatusreports/searchBetweenFechaInicioAndFechaFin/{fechaInicio}/{fechaFin}" })
    public List<ProjectStatusReport> getProjectStatusReportsBetweenFechaInicioAndFechaFin(@PathVariable String fechaInicio,@PathVariable String fechaFin) {
                LocalDate fechaI = this.stringToLocalDate(fechaInicio);
                LocalDate fechaF = this.stringToLocalDate(fechaFin);
        return this.psrservice.getProjectStatusReportsByFechaInicio(fechaI,fechaF);
    }

    @GetMapping({"/projectstatusreports/findPsrByFeachaCreacionAndCodigoProyecto/{fechaCreacionPsr}/{codigoProyecto}" })
    public ProjectStatusReportComentarios FindPsrByFeachaCreacionAndCodigoProyecto(@PathVariable String fechaCreacionPsr, @PathVariable String codigoProyecto) {
        LocalDate fechaPsr = this.stringToLocalDate(fechaCreacionPsr);
        ProjectStatusReportComentarios projectStatusReportComentario = new ProjectStatusReportComentarios();
        projectStatusReportComentario.projectStatusReport = this.psrservice.findByFechaCreacionPsrAndCodigo(fechaPsr,codigoProyecto);
        if (projectStatusReportComentario.projectStatusReport != null) {
            List<PSRStatus> comentarios = this.PSRStatusService.findByProjectStatusReportId(projectStatusReportComentario.projectStatusReport.getId());
            projectStatusReportComentario.psrStatus = comentarios.get(0);
        }
        return projectStatusReportComentario;
    }
    
    @PostMapping({ "/projectstatusreports" })
    public ResponseEntity<?> savePsr(@RequestBody ProjectStatusReport[] psr) {
        LocalDateTime fechaDate = LocalDateTime.now(ZoneId.of("America/Bogota"));
        for (int i = 0; i < psr.length; i++) {
            psr[i].setFechaCreacionPsr(fechaDate);
            this.psrservice.saveProjectStatusReport(psr[i]);
        }
        LogSistema log = new LogSistema();
        log.setAccion("CREATE");
        log.setFechaHora(new Date());
        log.setTabla(ProjectStatusReport.class.toString());
        log.setDescripcion(psr.toString());
        this.logService.saveLog(log);
        return ResponseEntity.ok(psr);
    }

    @PostMapping({ "/projectstatusreports/saveWithComment" })
    public ResponseEntity<?> savePsrWithComment(@RequestBody ProjectStatusReportComentarios[] psrConNota) {
        LocalDateTime fechaDate = LocalDateTime.now(ZoneId.of("America/Bogota"));
        ProjectStatusReport projectStatusReport = new ProjectStatusReport();

        for (int i = 0; i < psrConNota.length; i++) {
        ProjectStatusReport projectStatusReportExistente = new ProjectStatusReport();
        projectStatusReportExistente = this.psrservice.findByFechaCreacionPsrAndCodigo(fechaDate.toLocalDate(),psrConNota[i].projectStatusReport.getCodigo());
            
        if(projectStatusReportExistente != null){
                
                List<PSRStatus> comentarios = this.PSRStatusService.findByProjectStatusReportId(projectStatusReportExistente.getId());
                PSRStatus comentarioActualizado  = comentarios.get(0);

                LogSistema log = new LogSistema();
                log.setAccion("UPDATE");
                log.setFechaHora(new Date());
                log.setTabla(ProjectStatusReport.class.toString());
                log.setDescripcion(psrConNota[i].toString());
                this.logService.saveLog(log);
                comentarioActualizado.setComentario(psrConNota[i].psrStatus.getComentario());
                this.PSRStatusService.savePSRStatus(comentarioActualizado);
            }else{

            psrConNota[i].projectStatusReport.setFechaCreacionPsr(fechaDate);
            psrConNota[i].psrStatus.setFechaCreacionStatus(fechaDate);
            projectStatusReport = this.psrservice.saveProjectStatusReport(psrConNota[i].projectStatusReport);
            psrConNota[i].psrStatus.setPSRProyecto(new ProjectStatusReport(projectStatusReport.getId()));
            this.PSRStatusService.savePSRStatus(psrConNota[i].psrStatus);
                
            LogSistema log = new LogSistema();
            log.setAccion("CREATE");
            log.setFechaHora(new Date());
            log.setTabla(ProjectStatusReport.class.toString());
            log.setDescripcion(psrConNota[i].toString());
            this.logService.saveLog(log);

            }
  
        }
         
        return ResponseEntity.ok(psrConNota);
    }



}

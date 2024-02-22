//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.controller;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.*;
import com.backendgip.repository.*;
import com.backendgip.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.*;

@RestController
@Transactional
@RequestMapping({"/api/proyectos/reporte-tiempo"})
public class ReporteTiempoController {
    @Autowired
    private ReporteTiempoService reporteTiempoService;
    @Autowired
    private ReporteTiempoRepository reporteTiempoRepository;
    @Autowired
    private EstadoReporteTiempoService estadoService;
    @Autowired
    private LogSistemaService logService;
    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Autowired
    private ProyectoRepository proyectoRepository;
    @Autowired
    private EstadoReporteTiempoRepository estadoReporteTiempoRepository;
    @Autowired
    private EstadoReporteTiempoService estadoReporteTiempoService;
    @Autowired
    private RecursoActividadRepository recursoActRepository;
    @Autowired
    private ActividadAsignadaService actAsigService;
    @Autowired
    private ProyectoService proyectoService;
    @Autowired
    private EmpleadoService empleadoService;
    @Autowired
    private EmpleadoRolService empleadoRolService;
    @Autowired
    private ActividadAsignadaService actividadAsigService;
    @Autowired
    private MailService mailService;
    @Autowired
    private RecursoActividadService recursoActService;

    public ReporteTiempoController() {
    }

    public List<Empleado> getEmpleadosByArray(String[] empleadosArray) {
        List<Empleado> empleados = new ArrayList();

        for (int i = 0; i < empleadosArray.length; ++i) {
            Empleado empleado = this.empleadoService.getEmpleadoById(Integer.parseInt(empleadosArray[i]));
            empleados.add(empleado);
        }

        return empleados;
    }

    public LocalDate stringToLocalDate(String string) {
        String[] fechaInicioArray = string.split("-");
        LocalDate fechaI = LocalDate.of(Integer.parseInt(fechaInicioArray[0]), Integer.parseInt(fechaInicioArray[1]),
                Integer.parseInt(fechaInicioArray[2]));
        return fechaI;
    }

    public List<ReporteTiempo> getReportesBetweenFechaInicioAndFechaFin(LocalDate fechaInicio, LocalDate fechaFin,
                                                                        Integer id) {
        Empleado ids = this.empleadoService.getEmpleadoById(id);
        List<ReporteTiempo> reportes = this.reporteTiempoService.findByEmpleado(ids);
        List<ReporteTiempo> reporte = new ArrayList();
        Iterator var7 = reportes.iterator();

        while (true) {
            ReporteTiempo r;
            do {
                if (!var7.hasNext()) {
                    return reporte;
                }

                r = (ReporteTiempo) var7.next();
            } while (!r.getFecha().isEqual(fechaFin) && !r.getFecha().isEqual(fechaInicio)
                    && (!r.getFecha().isBefore(fechaFin) || !r.getFecha().isAfter(fechaInicio)));

            reporte.add(r);
        }
    }

    @GetMapping({"/reportes"})
    public List<ReporteTiempo> getAllReporteTiempo() {
        return this.reporteTiempoService.getReporteTiempo();
    }

    @GetMapping({"/reportes/ultimos-dos-meses"})
    public List<ReporteTiempo> getAllReporteTiempoFechaAfter() {
        LocalDate fechaActual = LocalDate.now(ZoneId.of("America/Bogota")).minusMonths(2);
        return this.reporteTiempoService.getReporteTiempoFechaAfter(fechaActual);
    }

    @GetMapping("/reportes/empleado")
    public List<ReporteTiempo> getAllReporteTiempoByIdEmpleados(@RequestParam List<Integer> recursosAdd) {
        List<ReporteTiempo> reportesFiltrados = new ArrayList<>();
    
        List<ReporteTiempo> reportes = this.reporteTiempoService.getReporteTiempo();
        
        for (Integer empleadoId : recursosAdd) {
            for (ReporteTiempo reporte : reportes) {
                if (reporte.getEmpleado().getId() == empleadoId && reporte.getEstado().getId() == 1) {
                    reportesFiltrados.add(reporte);
                }
            }
        }
    
        return reportesFiltrados;
    }

    @GetMapping({"/reportes/paginado"})
    public List<ReporteTiempo> getAllReporteTiempoPaging(@RequestParam String page, @RequestParam String size) {
        try {
            if (validateNumberFormat(page) && validateNumberFormat(size)) {
                int pageInt = Integer.parseInt(page);
                int sizeInt = Integer.parseInt(size);
                if (pageInt >= 0 && sizeInt > 0) {
                    return this.reporteTiempoService.getReporteTiempoPaging(pageInt, sizeInt);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }


    @PostMapping({"/reportes/enviar-int"})
    public ResponseEntity<?> enviarProyectoInt(@RequestBody ReporteTiempo reporte) {
        LocalDate fechaActual = LocalDate.now(ZoneId.of("America/Bogota"));
        ActividadAsignada actividad = reporte.getActividad();
        actividad.setFechaInicio(reporte.getFecha());
        actividad.setFechaFin(reporte.getFecha());
        if (reporte.getFecha().isAfter(fechaActual)) {
            return ResponseEntity.badRequest().body("La fecha del reporte no puede ser mayor a la fecha actual");
        } else {
            this.actividadAsigService.saveActividad(actividad);
            reporte.setEstado(this.estadoService.getEstadoReporteTiempoById(1));
            ReporteTiempo createdReporte = this.reporteTiempoService.saveReporteTiempo(reporte);
            return new ResponseEntity(createdReporte, HttpStatus.OK);
        }
    }

    @PostMapping({"/reportes/enviar"})
    public ResponseEntity<?> enviar(@RequestBody ReporteTiempo reporte) {
        LocalDate fechaActual = LocalDate.now(ZoneId.of("America/Bogota"));
        if (reporte.getFecha().isAfter(fechaActual)) {
            return ResponseEntity.badRequest().body("La fecha del reporte no puede ser mayor a la fecha actual");
        } else if (!this.reporteTiempoService.validFechaReporteActividadAsig(reporte)) {
            return ResponseEntity.badRequest().body("La fecha del reporte no coincide con la actividad seleccionada.");
        } else if (this.reporteTiempoService.existsByEmpleadoAndActividadAndFecha(reporte.getEmpleado(),
                reporte.getActividad(), reporte.getFecha())) {
            return ResponseEntity.badRequest().body("Reporte ya existe con la fecha registrada");
        } else {
            reporte.setAsignador(((RecursoActividad) this.recursoActService
                    .findByEmpleadoAndActividad(reporte.getEmpleado(), reporte.getActividad()).get(0)).getAsignador());
            reporte.setEstado(this.estadoService.getEstadoReporteTiempoById(1));
            ReporteTiempo createdReporte = this.reporteTiempoService.saveReporteTiempo(reporte);
            LogSistema log = new LogSistema();
            log.setAccion("CREATE");
            log.setFechaHora(new Date());
            log.setTabla(ReporteTiempo.class.toString());
            log.setIdAccion(createdReporte.getId());
            log.setDescripcion(createdReporte.toString());
            this.logService.saveLog(log);
            return new ResponseEntity(createdReporte, HttpStatus.OK);
        }
    }

    @PutMapping({"/reportes/aprobar/{id}/{fechaA}/{aprobador}"})
    public ResponseEntity<?> aprobar(@PathVariable Integer id, @PathVariable Date fechaA,
                                     @PathVariable Integer aprobador) {
        ReporteTiempo reporte = (ReporteTiempo) this.reporteTiempoRepository.findById(id).orElseThrow(() -> {
            return new ResourceNotFoundException(" No existe Reporte de Tiempo con el id: " + id);
        });
        LogSistema log = new LogSistema();
        log.setAccion("UPDATE");
        log.setDescripcion(reporte.toString());
        log.setFechaHora(new Date());
        log.setIdAccion(reporte.getId());
        log.setTabla(reporte.getClass().toString());

        String emailEmpleado = reporte.getEmpleado().getEmail();
        LocalDate fechaReporte = reporte.getFecha();
        String descripcionProyecto = reporte.getProyecto().getDescripcion();
        this.mailService.sendSimpleMail(emailEmpleado, "REPORTE APROBADO",
                "Buen Dia.<br><br>Su reporte del dia " + fechaReporte + " del proyecto "
                        + descripcionProyecto
                        + " ha sido aprobado satisfactoriamente.<br><br><br>Gerencia De Proyectos Y Servicios.<br>Its Solution SAS.");
        this.logService.saveLog(log);
        reporte.setEstado(this.estadoService.getEstadoReporteTiempoById(2));
        reporte.setFechaA(fechaA);
        reporte.setAprobador(aprobador);
        if (reporte.getProyecto().getEtapa().getEtapa().equalsIgnoreCase("PRP")) {
            Proyecto proyecto = reporte.getProyecto();
            proyecto.setHorasPropuesta(this.proyectoService.getHorasPropuesta(proyecto) + reporte.getHoras());
            this.proyectoService.saveProyecto(proyecto);
        }

        ReporteTiempo reporteAprobado = this.reporteTiempoService.saveReporteTiempo(reporte);
        return new ResponseEntity(reporteAprobado, HttpStatus.OK);
    }

    @PutMapping({"/reportes/devolver/{id}"})
    public ResponseEntity<?> devolver(@PathVariable Integer id, @RequestBody ReporteTiempo reporteDetails) {
        ReporteTiempo reporte = (ReporteTiempo) this.reporteTiempoRepository.findById(id).orElseThrow(() -> {
            return new ResourceNotFoundException(" No existe Reporte de Tiempo con el id: " + id);
        });
        LogSistema log = new LogSistema();
        log.setAccion("UPDATE");
        log.setDescripcion(reporte.toString());
        log.setFechaHora(new Date());
        log.setIdAccion(reporte.getId());
        log.setTabla(reporte.getClass().toString());

        String emailEmpleado = reporte.getEmpleado().getEmail();
        LocalDate fechaReporte = reporte.getFecha();
        String descripcionProyecto = reporte.getProyecto().getDescripcion();
        this.mailService.sendSimpleMail(emailEmpleado, "REPORTE REPROBADO",
                "Buen Dia.<br><br>Su reporte del dia " + fechaReporte + " del proyecto "
                        + descripcionProyecto
                        + " ha sido devuelto, por favor revisar.<br><br><br>Gerencia De Proyectos Y Servicios.<br>Its Solution SAS.");

        this.logService.saveLog(log);
        reporte.setEstado(this.estadoService.getEstadoReporteTiempoById(3));
        reporte.setJustificacion(reporteDetails.getJustificacion());
        ReporteTiempo reporteDevuelto = this.reporteTiempoService.saveReporteTiempo(reporte);
        return new ResponseEntity(reporteDevuelto, HttpStatus.OK);
    }

    @PutMapping({"/reportes/reenviar/{id}"})
    public ResponseEntity<?> reenviar(@PathVariable Integer id, @RequestBody ReporteTiempo reporteDetails) {
        ReporteTiempo reporte = (ReporteTiempo) this.reporteTiempoRepository.findById(id).orElseThrow(() -> {
            return new ResourceNotFoundException(" No existe Reporte de Tiempo con el id: " + id);
        });
        reporteDetails.setEstado(this.estadoService.getEstadoReporteTiempoById(1));
        LogSistema log = new LogSistema();
        log.setAccion("UPDATE");
        log.setDescripcion(reporte.toString());
        log.setFechaHora(new Date());
        log.setIdAccion(reporte.getId());
        log.setTabla(reporte.getClass().toString());
        this.logService.saveLog(log);
        reporte.setHoras(reporteDetails.getHoras());
        reporte.setEstado(reporteDetails.getEstado());
        reporte.setJustificacion(reporteDetails.getJustificacion());
        if (reporte.getEstado().getEstado().equalsIgnoreCase("APROBADO")
                && reporte.getProyecto().getEtapa().getEtapa().equalsIgnoreCase("PRP")) {
            Proyecto proyecto = reporte.getProyecto();
            proyecto.setHorasPropuesta(this.proyectoService.getHorasPropuesta(proyecto) + reporte.getHoras());
            this.proyectoService.saveProyecto(proyecto);
        }

        ReporteTiempo reporteReenviado = this.reporteTiempoService.saveReporteTiempo(reporte);
        return new ResponseEntity(reporteReenviado, HttpStatus.OK);
    }

    @GetMapping({"/reportes/{id}"})
    public ResponseEntity<ReporteTiempo> getReporteTiempoById(@PathVariable Integer id) {
        ReporteTiempo reporteTiempo = (ReporteTiempo) this.reporteTiempoRepository.findById(id).orElseThrow(() -> {
            return new ResourceNotFoundException(" No existe Reporte de Tiempo con el id: " + id);
        });
        return ResponseEntity.ok(reporteTiempo);
    }

    @DeleteMapping({"/reportes/{id}"})
    public ResponseEntity<Map<String, Boolean>> deleteReporteTiempo(@PathVariable Integer id) {
        ReporteTiempo reporteTiempo = (ReporteTiempo) this.reporteTiempoRepository.findById(id).orElseThrow(() -> {
            return new ResourceNotFoundException("No existe el ReporteTiempo con el id: " + id);
        });
        this.reporteTiempoRepository.deleteById(reporteTiempo.getId());
        LogSistema log = new LogSistema();
        log.setAccion("DELETE");
        log.setFechaHora(new Date());
        log.setIdAccion(reporteTiempo.getId());
        log.setDescripcion(reporteTiempo.toString());
        log.setTabla(ReporteTiempo.class.toString());
        this.logService.saveLog(log);
        Map<String, Boolean> response = new HashMap();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @GetMapping({"/reportes/search/proyecto/{idProyecto}"})
    public ResponseEntity<?> findByProyecto(@PathVariable Integer idProyecto) {
        Proyecto proyecto = (Proyecto) this.proyectoRepository.findById(idProyecto).orElseThrow(() -> {
            return new ResourceNotFoundException("Proyecto no encontrado con id: " + idProyecto);
        });
        return this.reporteTiempoRepository.findByProyecto(proyecto).isEmpty()
                ? new ResponseEntity("No se encontraron resultado en la busqueda", HttpStatus.BAD_REQUEST)
                : new ResponseEntity(this.reporteTiempoRepository.findByProyecto(proyecto), HttpStatus.OK);
    }

    @GetMapping({"/reportes/search/estado/{idEstado}"})
    public ResponseEntity<?> findByEstado(@PathVariable Integer idEstado) {
        EstadoReporteTiempo estado = (EstadoReporteTiempo) this.estadoReporteTiempoRepository.findById(idEstado)
                .orElseThrow(() -> {
                    return new ResourceNotFoundException("Estado no encontrado con id: " + idEstado);
                });
        return this.reporteTiempoRepository.findByEstado(estado).isEmpty()
                ? new ResponseEntity("No se encontraron resultado en la busqueda", HttpStatus.BAD_REQUEST)
                : new ResponseEntity(this.reporteTiempoRepository.findByEstado(estado), HttpStatus.OK);
    }

    @GetMapping({"/reportes/search/proyecto-estado/{idProyecto}/{idEstado}"})
    public ResponseEntity<?> findByProyectoAndEstado(@PathVariable Integer idProyecto, @PathVariable Integer idEstado) {
        Proyecto proyecto = (Proyecto) this.proyectoRepository.findById(idProyecto).orElseThrow(() -> {
            return new ResourceNotFoundException("Proyecto no encontrado con id: " + idProyecto);
        });
        EstadoReporteTiempo estado = (EstadoReporteTiempo) this.estadoReporteTiempoRepository.findById(idEstado)
                .orElseThrow(() -> {
                    return new ResourceNotFoundException("Estado no encontrado con id: " + idEstado);
                });
        return this.reporteTiempoRepository.findByProyectoAndEstado(proyecto, estado).isEmpty()
                ? new ResponseEntity("No se encontraron resultado en la busqueda", HttpStatus.BAD_REQUEST)
                : new ResponseEntity(this.reporteTiempoRepository.findByProyectoAndEstado(proyecto, estado),
                HttpStatus.OK);
    }

    @GetMapping({"/reportes/search/proyecto-empleado/{idProyecto}/{idEmpleado}"})
    public ResponseEntity<?> findByProyectoAndEmpleado(@PathVariable Integer idProyecto, @PathVariable Integer idEmpleado) {
        Proyecto proyecto = (Proyecto) this.proyectoRepository.findById(idProyecto).orElseThrow(() -> {
            return new ResourceNotFoundException("Proyecto no encontrado con id: " + idProyecto);
        });
            Empleado empleado = (Empleado) this.empleadoRepository.findById(idEmpleado).orElseThrow(() -> {
                    return new ResourceNotFoundException("Estado no encontrado con id: " + idEmpleado);
                });
        return this.reporteTiempoRepository.findByProyectoAndEmpleado(proyecto, empleado).isEmpty()
                ? new ResponseEntity("No se encontraron resultado en la busqueda", HttpStatus.BAD_REQUEST)
                : new ResponseEntity(this.reporteTiempoRepository.findByProyectoAndEmpleado(proyecto, empleado),
                HttpStatus.OK);
    }

        @GetMapping({"/reportes/search/proyecto-estado-empleado/{idProyecto}/{idEstado}/{idEmpleado}"})
    public ResponseEntity<?> findByProyectoAndEstadoAndEmpleado(@PathVariable Integer idProyecto,@PathVariable Integer idEstado, @PathVariable Integer idEmpleado) {
        Proyecto proyecto = (Proyecto) this.proyectoRepository.findById(idProyecto).orElseThrow(() -> {
            return new ResourceNotFoundException("Proyecto no encontrado con id: " + idProyecto);});
            
        EstadoReporteTiempo estado = (EstadoReporteTiempo) this.estadoReporteTiempoRepository.findById(idEstado).orElseThrow(() -> {
                return new ResourceNotFoundException("Estado no encontrado con id: " + idEstado);});
                
        Empleado empleado = (Empleado) this.empleadoRepository.findById(idEmpleado).orElseThrow(() -> {
            return new ResourceNotFoundException("Estado no encontrado con id: " + idEmpleado);});

        return this.reporteTiempoRepository.findByProyectoAndEstadoAndEmpleado(proyecto, estado ,empleado).isEmpty()
                ? new ResponseEntity("No se encontraron resultado en la busqueda", HttpStatus.BAD_REQUEST)
                : new ResponseEntity(this.reporteTiempoRepository.findByProyectoAndEstadoAndEmpleado(proyecto, estado, empleado),
                        HttpStatus.OK);
    }

    @GetMapping({ "/reportes/search/empleado-estado/{idEmpleado}/{idEstado}" })
    public ResponseEntity<?> findByEmpleadoAndEstado(@PathVariable Integer idEmpleado, @PathVariable Integer idEstado) {
        Empleado empleado = (Empleado) this.empleadoRepository.findById(idEmpleado).orElseThrow(() -> {
            return new ResourceNotFoundException("Empleado no encontrado con id: " + idEmpleado);
        });
        EstadoReporteTiempo estado = (EstadoReporteTiempo) this.estadoReporteTiempoRepository.findById(idEstado)
                .orElseThrow(() -> {
                    return new ResourceNotFoundException("Estado no encontrado con id: " + idEstado);
                });
        return this.reporteTiempoRepository.findByEmpleadoAndEstado(empleado, estado).isEmpty()
                ? new ResponseEntity("No se encontraron resultado en la busqueda", HttpStatus.BAD_REQUEST)
                : new ResponseEntity(this.reporteTiempoRepository.findByEmpleadoAndEstado(empleado, estado),
                        HttpStatus.OK);
    }


    @GetMapping({"/reportes/search/mes-actual-empleado/{idEmpleado}"})
    public ResponseEntity<?> findByFechaBetweenEmpleado(@PathVariable Integer idEmpleado) {
        Empleado empleado = (Empleado) this.empleadoRepository.findById(idEmpleado).orElseThrow(() -> {
            return new ResourceNotFoundException("Empleado no encontrado con id: " + idEmpleado);
        });

        LocalDate fechaActual = LocalDate.now();
        LocalDate primerDiaDelMes = fechaActual.withDayOfMonth(1);
        int ultimoDiaDelMes = YearMonth.from(fechaActual).lengthOfMonth();
        LocalDate ultimoDiaDelMesDate = fechaActual.withDayOfMonth(ultimoDiaDelMes);

        return this.reporteTiempoRepository.findByEmpleadoAndFechaBetween(empleado, primerDiaDelMes, ultimoDiaDelMesDate).isEmpty()
                ? new ResponseEntity("No se encontraron resultado en la busqueda", HttpStatus.BAD_REQUEST)
                : new ResponseEntity(this.reporteTiempoRepository.findByEmpleadoAndFechaBetween(empleado, primerDiaDelMes, ultimoDiaDelMesDate),
                HttpStatus.OK);
    }

    @GetMapping({ "/reportes/search/horas-empleado-dia/{idEmpleado}/{fecha}" })
    public ResponseEntity<?> findHorasEmpleadoDia(@PathVariable Integer idEmpleado, @PathVariable String fecha) {
        Integer totalHoras = 0;
        LocalDate fechaBuscar = this.stringToLocalDate(fecha);
        Empleado empleado = (Empleado) this.empleadoRepository.findById(idEmpleado).orElseThrow(() -> {
            return new ResourceNotFoundException("Empleado no encontrado con id: " + idEmpleado);
        });
        for (ReporteTiempo reporte : this.reporteTiempoRepository.findByEmpleadoAndFecha(empleado, fechaBuscar)) {
            totalHoras = totalHoras + reporte.getHoras();
        }
        return new ResponseEntity(totalHoras, HttpStatus.OK);
    }

    @GetMapping({"/reportes/pendientes/empleado/{idEmpleado}"})
    public ResponseEntity<?> findPendingReportes(@PathVariable Integer idEmpleado) {
        Empleado empleado = (Empleado) this.empleadoRepository.findById(idEmpleado).orElseThrow(() -> {
            return new ResourceNotFoundException("Empleado no encontrado con id: " + idEmpleado);
        });
        List<ReporteTiempo> pendingReportes = new ArrayList();
        List<RecursoActividad> actividades = this.recursoActRepository.findByEmpleado(empleado);
        Iterator var5 = actividades.iterator();

        while (var5.hasNext()) {
            RecursoActividad a = (RecursoActividad) var5.next();
            if (!this.reporteTiempoRepository.existsByEmpleadoAndFechaAndActividad(empleado, a.getFecha(), a.getActividad())) {
                ReporteTiempo reporte = new ReporteTiempo();
                reporte.setActividad(a.getActividad());
                reporte.setEmpleado(empleado);
                reporte.setEstado((EstadoReporteTiempo) null);
                reporte.setFecha(a.getFecha());
                reporte.setHoras((Integer) null);
                reporte.setProyecto(a.getActividad().getProyecto());
                pendingReportes.add(reporte);
            }
        }

        return ResponseEntity.ok(pendingReportes);
    }

    @GetMapping({"/reportes/find-by-actividad/{idActividad}"})
    public ResponseEntity<?> findByActividad(@PathVariable Integer idActividad) {
        ActividadAsignada actividad = this.actAsigService.getActividadById(idActividad);
        return ResponseEntity.ok(this.reporteTiempoService.findByActividad(actividad));
    }

    @GetMapping({"/reportes/find-by-empleado/{idEmpleado}"})
    public ResponseEntity<?> findByEmpleado(@PathVariable Integer idEmpleado) {
        Empleado empleado = this.empleadoService.getEmpleadoById(idEmpleado);
        return ResponseEntity.ok(this.reporteTiempoService.findByEmpleado(empleado));
    }

    @GetMapping({"/reportes/find-by-empleados/{empleados}"})
    public ResponseEntity<?> findByEmpleadoNombre(@PathVariable String empleados) {
        List<ReporteTiempo> reportes = new ArrayList();
        List<Empleado> empleado = this.empleadoRepository.findByNombreContaining(empleados);
        Iterator var4 = empleado.iterator();

        while (true) {
            List reportesEmp;
            do {
                if (!var4.hasNext()) {
                    return ResponseEntity.ok(reportes);
                }

                Empleado emp = (Empleado) var4.next();
                reportesEmp = this.reporteTiempoRepository.findByEmpleado(emp);
            } while (reportesEmp == null);

            Iterator var7 = reportesEmp.iterator();

            while (var7.hasNext()) {
                ReporteTiempo rep = (ReporteTiempo) var7.next();
                if (rep.getEstado().getId() == 1) {
                    reportes.add(rep);
                }
            }
        }
    }

    @GetMapping({"/reportes/find-by-proyecto-int"})
    public ResponseEntity<?> findByProyectoInt() {
        List<ReporteTiempo> allReportes = this.reporteTiempoService.getReporteTiempo();
        List<ReporteTiempo> reportes = new ArrayList();
        Iterator var3 = allReportes.iterator();

        while (var3.hasNext()) {
            ReporteTiempo r = (ReporteTiempo) var3.next();
            EmpleadoRol rol = (EmpleadoRol) this.empleadoRolService.findByEmpleado(r.getEmpleado()).get(0);
            if (r.getProyecto().getInterno() && r.getEstado().getId() == 1
                    && rol.getRol().getRol().equalsIgnoreCase("ROL_LP")) {
                reportes.add(r);
            }
        }

        return ResponseEntity.ok(reportes);
    }

    @GetMapping({"/reportes/find-by-lider/{idLider}"})
    public ResponseEntity<?> findByLider(@PathVariable Integer idLider) {
        Empleado lider = this.empleadoService.getEmpleadoById(idLider);
        List<ReporteTiempo> allReportes = this.reporteTiempoService
                .findByEstado((EstadoReporteTiempo) this.estadoReporteTiempoRepository.findById(1).get());
        List<ReporteTiempo> reportes = new ArrayList();
        Iterator var5 = allReportes.iterator();

        while (var5.hasNext()) {
            ReporteTiempo r = (ReporteTiempo) var5.next();
            if (this.isAsignadorReporte(r.getActividad(), lider)) {
                reportes.add(r);
            }
        }

        return ResponseEntity.ok(reportes);
    }

    public boolean isAsignadorReporte(ActividadAsignada actividad, Empleado asignador) {
        List<RecursoActividad> recursosAct = this.recursoActService.findByActividad(actividad);
        boolean flag = false;
        Iterator var5 = recursosAct.iterator();

        while (var5.hasNext()) {
            RecursoActividad reAct = (RecursoActividad) var5.next();
            if (reAct.getAsignador().equalsIgnoreCase(asignador.getNombre())) {
                flag = true;
            }
        }

        return flag;
    }

    @GetMapping({"/reportes/searchByEmpleado/{fechaInicio}/{fechaFin}/{id}"})
    public ResponseEntity<?> getRecursosAprobacionTiempos(@PathVariable String fechaInicio,
                                                          @PathVariable String fechaFin, @PathVariable Integer id) {
        LocalDate fechaI = this.stringToLocalDate(fechaInicio);
        LocalDate fechaF = this.stringToLocalDate(fechaFin);
        LocalDate fechaA = LocalDate.now(ZoneId.of("America/Bogota"));
        if (this.empleadoService.getFechasBetween(fechaI, fechaF).size() > 31) {
            return ResponseEntity.badRequest().body("El rango de fechas no puede ser mayor a 31 días");
        } else if (!fechaI.isAfter(fechaA) && !fechaF.isAfter(fechaA)) {
            return !fechaI.isAfter(fechaF) && !fechaF.isBefore(fechaI)
                    ? ResponseEntity.ok(this.getReportesBetweenFechaInicioAndFechaFin(fechaI, fechaF, id))
                    : ResponseEntity.badRequest().body(
                    "La fecha inicio no puede ser mayor a la fecha fin y la fecha fin no puede ser menor a la fecha inicio");
        } else {
            return ResponseEntity.badRequest()
                    .body("La fecha inicio o la fecha fin no puede ser mayor a la fecha actual");
        }
    }

    @GetMapping({"reportes/recursos-planeados"})
    public ResponseEntity<?> getReportesByEstado() {
        List<Empleado> emp = this.empleadoService.getEmpleado();
        Empleado empleado = (Empleado) this.empleadoService.getEmpleado();
        List<RecursoActividad> reportesPendientes = this.recursoActRepository.findByEmpleado(empleado);
        List<ReporteTiempo> reportes = new ArrayList();
        Iterator var5 = reportesPendientes.iterator();

        while (var5.hasNext()) {
            RecursoActividad ra = (RecursoActividad) var5.next();
            if (!this.reporteTiempoRepository.existsByEmpleadoAndFecha(empleado, ra.getFecha())) {
                ReporteTiempo reporte = new ReporteTiempo();
                reporte.setEmpleado(empleado);
                reporte.setActividad(ra.getActividad());
                reporte.setEstado((EstadoReporteTiempo) null);
                reporte.setFecha(ra.getFecha());
                reporte.setHoras((Integer) null);
                reporte.setProyecto(ra.getActividad().getProyecto());
                reportes.add(reporte);
            }
        }

        return ResponseEntity.ok(reportes);
    }

    private boolean validateNumberFormat(String number) {
        try {
            Integer.parseInt(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

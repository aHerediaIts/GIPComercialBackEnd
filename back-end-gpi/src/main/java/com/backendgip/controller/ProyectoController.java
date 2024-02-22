//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.controller;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.Cliente;
import com.backendgip.model.ComponenteDesarrollo;
import com.backendgip.model.Empleado;
import com.backendgip.model.EmpleadoRol;
import com.backendgip.model.EstadoProyecto;
import com.backendgip.model.LogSistema;
import com.backendgip.model.Proyecto;
import com.backendgip.repository.ActividadAsignadaRepository;
import com.backendgip.repository.ActividadRepository;
import com.backendgip.repository.EmpleadoRepository;
import com.backendgip.repository.FacturacionRepository;
import com.backendgip.repository.FaseProyectoRepository;
import com.backendgip.repository.ProyectoRepository;
import com.backendgip.service.ClienteService;
import com.backendgip.service.ComponenteDesarrolloService;
import com.backendgip.service.EmpleadoRolService;
import com.backendgip.service.EmpleadoService;
import com.backendgip.service.EstadoPropuestaService;
import com.backendgip.service.EstadoProyectoService;
import com.backendgip.service.EtapaProyectoService;
import com.backendgip.service.LogSistemaService;
import com.backendgip.service.MailService;
import com.backendgip.service.ProyectoService;
import com.backendgip.service.ReporteTiempoService;
import com.backendgip.service.RolService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
@RequestMapping({"/api"})
public class ProyectoController {
    @Autowired
    private ProyectoService proyectoService;
    @Autowired
    private MailService mailService;
    @Autowired
    private ProyectoRepository proyectoRepository;
    @Autowired
    private EtapaProyectoService etapaService;
    @Autowired
    private ComponenteDesarrolloService componenteService;
    @Autowired
    private EstadoPropuestaService estadoPropuestaService;
    @Autowired
    private EstadoProyectoService estadoProyectoService;
    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Autowired
    private LogSistemaService logService;
    @Autowired
    private ActividadAsignadaRepository actividadAsigRepository;
    @Autowired
    private FacturacionRepository facturacionRepository;
    @Autowired
    private FaseProyectoRepository faseRepository;
    @Autowired
    private ActividadRepository actividadRepository;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private EmpleadoService empleadoService;
    @Autowired
    private RolService rolService;
    @Autowired
    private EmpleadoRolService empleadoRolService;
    @Autowired
    private ReporteTiempoService reporteTiempoService;
    public boolean c = false;

    public LocalDate stringToLocalDate(String string) {
        String[] fechaInicioArray = string.split("-");
        LocalDate fechaI = LocalDate.of(Integer.parseInt(fechaInicioArray[0]), Integer.parseInt(fechaInicioArray[1]),
                Integer.parseInt(fechaInicioArray[2]));
        return fechaI;
    }

    public ProyectoController() {
    }

    @GetMapping({"/proyectos"})
    public List<Proyecto> getAllProyectos() {
        return this.proyectoService.getProyectos();
    }

    @GetMapping({"/proyectos/asignados/{idEmpleado}"})
    public ResponseEntity<?> findByLiderAsignado(@PathVariable Integer idEmpleado) {
        Empleado lider = this.empleadoService.getEmpleadoById(idEmpleado);
        return ResponseEntity.ok(this.proyectoService.findByLider(lider));
    }

    
    @GetMapping({"/proyectos/searchBetweenFechaInicioAndFechaFin/{fechaInicio}/{fechaFin}"})
    public List<Proyecto> getAllProyectosFechaInicioFechaFin(@PathVariable String fechaInicio,@PathVariable String fechaFin) {
        LocalDate fechaI = this.stringToLocalDate(fechaInicio);
        LocalDate fechaF = this.stringToLocalDate(fechaFin);
        return this.proyectoService.getProyectosByFechaInicioFechaFin(fechaI,fechaF);
    }

    @GetMapping({"/proyectos/asignados/searchBetweenFechaInicioAndFechaFin/{fechaInicio}/{fechaFin}/{idEmpleado}"})
    public ResponseEntity<?> findByLiderAsignadoFechaInicioFechaFin(@PathVariable String fechaInicio,@PathVariable String fechaFin,@PathVariable Integer idEmpleado) {
        Empleado lider = this.empleadoService.getEmpleadoById(idEmpleado);
        LocalDate fechaI = this.stringToLocalDate(fechaInicio);
        LocalDate fechaF = this.stringToLocalDate(fechaFin);
        return ResponseEntity.ok(this.proyectoService.findByLiderFechaInicioFechaFin(fechaI,fechaF,lider));
    }

    @PostMapping({"/proyectos"})
    public ResponseEntity<?> saveProyecto(@RequestBody Proyecto proyecto) {
        proyecto.setNombre(this.getNombreConcat(proyecto));
        proyecto.setEtapa(this.etapaService.getEtapaById(proyecto.getEtapa().getId()));
        proyecto.setEstadoPropuesta(this.estadoPropuestaService.getEstadoById(proyecto.getEstadoPropuesta().getId()));
        if (this.proyectoRepository.existsByNombre(proyecto.getNombre())) {
            return ResponseEntity.badRequest().body("¡El nombre de proyecto ya existe!");
        } else if (this.proyectoRepository.existsByCodigoAndCliente(proyecto.getCodigo(), proyecto.getCliente())) {
            return ResponseEntity.badRequest().body("!El cliente ya tiene ese código asignado¡");
        } else if (this.validEstadoProyecto(proyecto) != null) {
            return this.validEstadoProyecto(proyecto);
        } else {
            if (proyecto.getEtapa().getEtapa().equalsIgnoreCase("CRN")) {
                if (this.validDatesProyecto(proyecto) != null) {
                    return this.validDatesProyecto(proyecto);
                }
            } else if (proyecto.getEtapa().getEtapa().equalsIgnoreCase("PRP")) {
                proyecto.setFechaAprobacion((LocalDate) null);
                proyecto.setFechaInicio((LocalDate) null);
                proyecto.setFechaFin((LocalDate) null);
                proyecto.setHorasPlaneadas(1);
                proyecto.setHorasPropuesta((Integer) null);
                proyecto.setComponente((ComponenteDesarrollo) null);
                proyecto.setDirectorClient((String) null);
                proyecto.setDirectorIts((Empleado) null);
                proyecto.setLider((Empleado) null);
            }

            LocalDate fechaCreacion = LocalDate.now(ZoneId.of("America/Bogota"));
            proyecto.setFechaCreacion(fechaCreacion);
            proyecto.setInterno(Boolean.FALSE);
            Proyecto createdProyecto = this.proyectoService.saveProyecto(proyecto);
            LogSistema log = new LogSistema();
            log.setAccion("CREATE");
            log.setFechaHora(new Date());
            log.setTabla(Proyecto.class.toString());
            log.setIdAccion(createdProyecto.getId());
            log.setDescripcion(createdProyecto.toString());
            this.logService.saveLog(log);
            return ResponseEntity.ok(createdProyecto);
        }
    }

    @PutMapping({"/proyectos/{id}/{creator}"})
    public ResponseEntity<?> updateProyecto(@PathVariable Integer id, @RequestBody Proyecto proyectoDetails,
                                            @PathVariable Integer creator) {
        proyectoDetails.setEtapa(this.etapaService.getEtapaById(proyectoDetails.getEtapa().getId()));
        proyectoDetails.setEstadoPropuesta(
                this.estadoPropuestaService.getEstadoById(proyectoDetails.getEstadoPropuesta().getId()));
        proyectoDetails.setNombre(this.getNombreConcat(proyectoDetails));
        Proyecto proyecto = (Proyecto) this.proyectoRepository.findById(id).orElseThrow(() -> {
            return new ResourceNotFoundException("Proyecto no encontrado con id: " + id);
        });
        if (this.proyectoRepository.existsByNombre(proyectoDetails.getNombre())
            && proyecto.getId().intValue() != proyectoDetails.getId().intValue()) {
            return ResponseEntity.badRequest().body("¡El nombre de proyecto ya existe!");
        } else if (this.validEstadoProyecto(proyectoDetails) != null) {
            return this.validEstadoProyecto(proyectoDetails);
        } else {
            Proyecto updatedProyecto = null;
            LogSistema log;
            if (proyectoDetails.getEtapa().getEtapa().equalsIgnoreCase("CRN")) {
                if (this.validDatesProyecto(proyectoDetails) != null) {
                    return this.validDatesProyecto(proyectoDetails);
                } else if (this.validDifferentDates(proyecto, proyectoDetails)
                        || !this.actividadRepository.existsByProyecto(proyecto)
                        && !this.facturacionRepository.existsByProyecto(proyecto)) {
                    this.projectDataChange(proyectoDetails, creator);
                    proyecto.setCliente(proyectoDetails.getCliente());
                    proyecto.setCodigo(proyectoDetails.getCodigo());
                    proyecto.setEtapa(proyectoDetails.getEtapa());
                    proyecto.setTipo(proyectoDetails.getTipo());
                    proyecto.setNombre(proyectoDetails.getNombre());
                    proyecto.setEstadoPropuesta(proyectoDetails.getEstadoPropuesta());
                    proyecto.setEstadoProyecto(proyectoDetails.getEstadoProyecto());
                    proyecto.setHorasPlaneadas(proyectoDetails.getHorasPlaneadas());
                    proyecto.setDescripcion(proyectoDetails.getDescripcion());
                    proyecto.setCosto(proyectoDetails.getCosto());
                    proyecto.setComponente(proyectoDetails.getComponente());
                    proyecto.setLider(proyectoDetails.getLider());
                    proyecto.setFechaAprobacion(proyectoDetails.getFechaAprobacion());
                    proyecto.setFechaInicio(proyectoDetails.getFechaInicio());
                    proyecto.setFechaFin(proyectoDetails.getFechaFin());
                    proyecto.setDirectorClient(proyectoDetails.getDirectorClient());
                    proyecto.setDirectorIts(proyectoDetails.getDirectorIts());
                    log = new LogSistema();
                    log.setAccion("UPDATE");
                    log.setDescripcion(proyecto.toString());
                    log.setFechaHora(new Date());
                    log.setIdAccion(proyecto.getId());
                    log.setTabla(proyecto.getClass().toString());
                    this.logService.saveLog(log);
                    updatedProyecto = this.proyectoService.saveProyecto(proyecto);
                    return ResponseEntity.ok(updatedProyecto);
                } else {
                    return ResponseEntity.badRequest().body(
                            "No se puede modificar las fechas ya que tiene actividades y/o facturaciones asignadas");
                }
            } else {
                log = new LogSistema();
                log.setAccion("UPDATE");
                log.setDescripcion(proyecto.toString());
                log.setFechaHora(new Date());
                log.setIdAccion(proyecto.getId());
                log.setTabla(proyecto.getClass().toString());
                this.logService.saveLog(log);
                this.projectDataChange(proyectoDetails, creator);
                proyecto.setCliente(proyectoDetails.getCliente());
                proyecto.setCodigo(proyectoDetails.getCodigo());
                proyecto.setEtapa(proyectoDetails.getEtapa());
                proyecto.setTipo(proyectoDetails.getTipo());
                proyecto.setNombre(proyectoDetails.getNombre());
                proyecto.setEstadoPropuesta(proyectoDetails.getEstadoPropuesta());
                proyecto.setEstadoProyecto(proyectoDetails.getEstadoProyecto());
                proyecto.setDescripcion(proyectoDetails.getDescripcion());
                proyecto.setFechaAprobacion((LocalDate) null);
                proyecto.setFechaInicio((LocalDate) null);
                proyecto.setFechaFin((LocalDate) null);
                proyecto.setHorasPlaneadas(1);
                proyecto.setHorasPropuesta((Integer) null);
                proyecto.setComponente((ComponenteDesarrollo) null);
                proyecto.setDirectorClient((String) null);
                proyecto.setDirectorIts((Empleado) null);
                proyecto.setLider((Empleado) null);
                updatedProyecto = this.proyectoService.saveProyecto(proyecto);
                return ResponseEntity.ok(updatedProyecto);
            }
        }
    }

    @GetMapping({"/proyectos/{id}"})
    public ResponseEntity<Proyecto> getProyectosById(@PathVariable Integer id) {
        Proyecto proyecto = (Proyecto) this.proyectoRepository.findById(id).orElseThrow(() -> {
            return new ResourceNotFoundException("ID " + id + " NO ENCONTRADO");
        });
        return ResponseEntity.ok(proyecto);
    }

    @DeleteMapping({"/proyectos/{id}"})
    public ResponseEntity<?> deleteProyecto(@PathVariable Integer id) {
        Proyecto proyecto = (Proyecto) this.proyectoRepository.findById(id).orElseThrow(() -> {
            return new ResourceNotFoundException("Proyecto no encontrado con id: " + id);
        });
        if (this.actividadAsigRepository.existsByProyecto(proyecto)) {
            return ResponseEntity.badRequest()
                    .body("No se puede eliminar el Proyecto, Tiene relacion con Actividad Asignada.");
        } else if (this.facturacionRepository.existsByProyecto(proyecto)) {
            return ResponseEntity.badRequest()
                    .body("No se puede eliminar el Proyecto, Tiene relacion con Facturacion.");
        } else if (this.faseRepository.existsByProyecto(proyecto)) {
            return ResponseEntity.badRequest()
                    .body("No se puede eliminar el Proyecto, Tiene relacion con Fase proyecto.");
        } else if (this.actividadRepository.existsByProyecto(proyecto)) {
            return ResponseEntity.badRequest()
                    .body("No se puede eliminar el Proyecto, Tiene relacion con Actividad proyecto.");
        } else {
            LogSistema log = new LogSistema();
            log.setAccion("DELETE");
            log.setFechaHora(new Date());
            log.setIdAccion(proyecto.getId());
            log.setDescripcion(proyecto.toString());
            log.setTabla(Proyecto.class.toString());
            this.logService.saveLog(log);
            this.proyectoService.deleteProyecto(proyecto);
            Map<String, Boolean> response = new HashMap();
            response.put("deleted", Boolean.TRUE);
            return ResponseEntity.ok(response);
        }
    }

    @GetMapping({"/proyectos/reporte-tiempo/disponibles/{idEmpleado}"})
    public List<Proyecto> findProyectosForReporteTiempo(@PathVariable Integer idEmpleado) {
        Empleado empleado = (Empleado) this.empleadoRepository.findById(idEmpleado).orElseThrow(() -> {
            return new ResourceNotFoundException("Empleado no encontrado con id: " + idEmpleado);
        });
        List<Proyecto> proyectos = this.proyectoService.findProyectosForReporteTiempo(empleado);

        return proyectos;
    }

    @GetMapping({"/proyectos/proyectos-by-cliente/{idCliente}"})
    public ResponseEntity<?> findByCliente(@PathVariable Integer idCliente) {
        Cliente cliente = this.clienteService.getClienteById(idCliente);
        return ResponseEntity.ok(this.proyectoService.findByCliente(cliente));
    }

    @GetMapping({"/proyectos/proyectos-by-codigo/{codigo}"})
    public ResponseEntity<?> findByCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(this.proyectoService.findByCodigo(codigo));
    }

    @GetMapping({"/proyectos/proyectos-by-componente/{idComponente}"})
    public ResponseEntity<?> findByComponente(@PathVariable Integer idComponente) {
        ComponenteDesarrollo componente = this.componenteService.getComponenteById(idComponente);
        return ResponseEntity.ok(this.proyectoService.findByComponente(componente));
    }

    @GetMapping({"/proyectos/proyectos-by-codigo-cliente/{codigo}/{idCliente}"})
    public ResponseEntity<?> findByCodigoAndCliente(@PathVariable String codigo, @PathVariable Integer idCliente) {
        Cliente cliente = this.clienteService.getClienteById(idCliente);
        return ResponseEntity.ok(this.proyectoService.findByCodigoAndCliente(codigo, cliente));
    }

    @GetMapping({"/proyectos/proyectos-by-codigo-componente/{codigo}/{idComponente}"})
    public ResponseEntity<?> findByCodigoAndComponente(@PathVariable String codigo,
                                                       @PathVariable Integer idComponente) {
        ComponenteDesarrollo componente = this.componenteService.getComponenteById(idComponente);
        return ResponseEntity.ok(this.proyectoService.findByCodigoAndComponente(codigo, componente));
    }

    @GetMapping({"/proyectos/proyectos-by-codigo-cliente-componente/{codigo}/{idCliente}/{idComponente}"})
    public ResponseEntity<?> findByCodigoAndClienteAndComponente(@PathVariable String codigo,
                                                                 @PathVariable Integer idCliente, @PathVariable Integer idComponente) {
        Cliente cliente = this.clienteService.getClienteById(idCliente);
        ComponenteDesarrollo componente = this.componenteService.getComponenteById(idComponente);
        return ResponseEntity.ok(this.proyectoService.findByCodigoAndClienteAndComponente(codigo, cliente, componente));
    }

    @GetMapping({"/proyectos/proyectos-by-componente-cliente/{idComponente}/{idCliente}"})
    public ResponseEntity<?> findByComponenteAndCliente(@PathVariable Integer idComponente,
                                                        @PathVariable Integer idCliente) {
        ComponenteDesarrollo componente = this.componenteService.getComponenteById(idComponente);
        Cliente cliente = this.clienteService.getClienteById(idCliente);
        return ResponseEntity.ok(this.proyectoService.findByComponenteAndCliente(componente, cliente));
    }

    public String getNombreConcat(Proyecto proyecto) {
        if (proyecto.getEtapa().getId() == 1) {
            return "REG-PRP-" + proyecto.getCliente().getNomenclatura() + "-" + proyecto.getCodigo();
        } else {
            return proyecto.getEtapa().getId() == 2
                    ? "REG-CRN-" + proyecto.getCliente().getNomenclatura() + "-" + proyecto.getCodigo()
                    : "REG-" + proyecto.getEtapa().getEtapa() + "-" + proyecto.getCliente().getNomenclatura() + "-"
                    + proyecto.getCodigo();
        }
    }

    public ResponseEntity<?> validDatesProyecto(Proyecto proyecto) {
        if (proyecto.getFechaAprobacion().isAfter(proyecto.getFechaInicio())) {
            return ResponseEntity.badRequest().body("La Fecha Aprobación debe ser menor o igual a la Fecha Inicio.");
        } else {
            return proyecto.getFechaInicio().isBefore(proyecto.getFechaAprobacion())
                    ? ResponseEntity.badRequest().body("La Fecha Inicio debe ser mayor o igual a la Fecha Aprobación.")
                    : null;
        }
    }

    public boolean validDifferentDates(Proyecto oldProyecto, Proyecto newProyecto) {
        if (oldProyecto.getFechaAprobacion() != null && newProyecto.getFechaAprobacion() != null
                && !oldProyecto.getFechaAprobacion().isEqual(newProyecto.getFechaAprobacion())) {
            return false;
        } else if (oldProyecto.getFechaInicio() != null && newProyecto.getFechaInicio() != null
                && !oldProyecto.getFechaInicio().isEqual(newProyecto.getFechaInicio())) {
            return false;
        } else {
            return oldProyecto.getFechaFin() == null || newProyecto.getFechaFin() == null
                    || oldProyecto.getFechaFin().isEqual(newProyecto.getFechaFin());
        }
    }

    public ResponseEntity<?> validEstadoProyecto(Proyecto proyecto) {
        List<EstadoProyecto> estadosPRP = this.estadoProyectoService.getEstadosPRP();
        List<EstadoProyecto> estadosCRN = this.estadoProyectoService.getEstadosCRN();
        if (proyecto.getEtapa().getEtapa().equalsIgnoreCase("PRP")
                && estadosCRN.toString().contains(proyecto.getEstadoProyecto().toString())) {
            return ResponseEntity.badRequest().body("El estado seleccionado no corresponde con la etapa selecionada.");
        } else {
            return proyecto.getEtapa().getEtapa().equalsIgnoreCase("CRN")
                    && estadosPRP.toString().contains(proyecto.getEstadoProyecto().toString())
                    ? ResponseEntity.badRequest()
                    .body("El estado seleccionado no corresponde con la etapa selecionada.")
                    : null;
        }
    }

    @PostMapping({"/proyectos/internos"})
    public ResponseEntity<?> saveProyectoInt(@RequestBody Proyecto proyecto) {
        LocalDate fechaActual = LocalDate.now(ZoneId.of("America/Bogota"));
        if (proyecto.getFechaInicio().isAfter(proyecto.getFechaFin())) {
            return ResponseEntity.badRequest().body("La fecha inicio debe ser menor a la fecha fin.");
        } else if (proyecto.getFechaAprobacion().isAfter(proyecto.getFechaInicio())) {
            return ResponseEntity.badRequest().body("La fecha de aprobacion debe ser menor a la fecha inicio.");
        } else {
            proyecto.setNombre(this.getNombreConcat(proyecto));
            if (this.proyectoService.existsByNombre(proyecto.getNombre())) {
                return ResponseEntity.badRequest().body("Ya existe un proyecto registrado con ese nombre.");
            } else {
                proyecto.setFechaCreacion(fechaActual);
                Proyecto createdProyecto = this.proyectoService.saveProyecto(proyecto);
                return ResponseEntity.ok(createdProyecto);
            }
        }
    }

    @PutMapping({"/proyectos/internos/{idProyecto}"})
    public ResponseEntity<?> updateProyectoInt(@PathVariable Integer idProyecto,
                                               @RequestBody Proyecto proyectoDetails) {
        Proyecto proyecto = this.proyectoService.getProyectoById(idProyecto);
        proyecto.setCliente(proyectoDetails.getCliente());
        proyecto.setCodigo(proyectoDetails.getCodigo());
        proyecto.setComponente(proyectoDetails.getComponente());
        proyecto.setNombre(this.getNombreConcat(proyectoDetails));
        proyecto.setDescripcion(proyectoDetails.getDescripcion());
        proyecto.setDirectorIts(proyectoDetails.getDirectorIts());
        proyecto.setEstadoPropuesta(proyectoDetails.getEstadoPropuesta());
        proyecto.setEstadoProyecto(proyectoDetails.getEstadoProyecto());
        proyecto.setEtapa(proyectoDetails.getEtapa());
        proyecto.setFechaAprobacion(proyectoDetails.getFechaAprobacion());
        proyecto.setFechaInicio(proyectoDetails.getFechaInicio());
        proyecto.setFechaFin(proyectoDetails.getFechaFin());
        proyecto.setLider(proyectoDetails.getLider());
        proyecto.setTipo(proyectoDetails.getTipo());
        if (proyecto.getFechaInicio().isAfter(proyecto.getFechaFin())) {
            return ResponseEntity.badRequest().body("La fecha inicio debe ser menor a la fecha fin.");
        } else if (proyecto.getFechaAprobacion().isAfter(proyecto.getFechaInicio())) {
            return ResponseEntity.badRequest().body("La fecha de aprobacion debe ser menor a la fecha inicio.");
        } else {
            proyecto.setNombre(this.getNombreConcat(proyectoDetails));
            if (this.proyectoService.existsByNombre(proyecto.getNombre()) && proyectoDetails.getId() != idProyecto) {
                return ResponseEntity.badRequest().body("Ya existe un proyecto registrado con ese nombre.");
            } else {
                Proyecto updatedProyecto = this.proyectoService.saveProyecto(proyecto);
                return ResponseEntity.ok(updatedProyecto);
            }
        }
    }

    @DeleteMapping({"/proyectos/internos/{idProyecto}"})
    public ResponseEntity<?> deleteProyectoInt(@PathVariable Integer idProyecto) {
        Proyecto proyecto = this.proyectoService.getProyectoById(idProyecto);
        if (this.reporteTiempoService.findByProyecto(proyecto).size() > 0) {
            return ResponseEntity.badRequest()
                    .body("Este proyecto no se puede eliminar, tiene relacion con reporte de tiempos.");
        } else {
            this.proyectoService.deleteProyecto(proyecto);
            Map<Boolean, String> response = new HashMap();
            response.put(Boolean.TRUE, "DELETED");
            return ResponseEntity.ok(response);
        }
    }

    public void projectDataChange(Proyecto proyecto, Integer creator) {
        Proyecto oldProyecto = this.proyectoService.getProyectoById(proyecto.getId());
        String ca = "<style>\r\n    table {\r\n      border-collapse: collapse;\r\n      width: 100%;\r\n      border: solid #ccc 1px;\r\n    }\r\n    th, td {\r\n    text-align: center;\r\n    padding: 8px;\r\n    }\r\n    tr:nth-child(even){background-color: #f2f2f2}\r\n    th {\r\n      background-color: #59c3ec;\r\n      color: white;\r\n    }\r\n    </style>\r\n<body>\r\n";
        String mod = " ha modificado el proyecto " + oldProyecto.getNombre() + "-" + oldProyecto.getDescripcion()
                + ". <br><br>";
        try {
            String MsgPrp;
            if (oldProyecto.getEtapa().getId().equals(1)) {
                MsgPrp = this.tablePrpProject(oldProyecto);
            } else {
                MsgPrp = this.tableCrnProject(oldProyecto);
            }

            MsgPrp = MsgPrp + "<br>Los Datos Del Proyecto Que Cambiaron Fueron: <br> <br>";
            MsgPrp = MsgPrp + this.tableChangeProyect(proyecto);
            if (this.c) {
                String MsgLider;
                if (oldProyecto.getEtapa().getId().equals(1) && proyecto.getEtapa().getId().equals(2)) {
                    MsgLider = this.tableCrnProject(proyecto);
                    this.mailService.sendSimpleMail(
                            this.empleadoService.getEmpleadoById(proyecto.getLider().getId()).getEmail(),
                            "ASIGNADO A PROYECTO " + proyecto.getNombre() + "-" + proyecto.getDescripcion(),
                            "Buen Dia.<br><br> Usted a sido asignado como lider al proyecto " + proyecto.getNombre() + "-"
                                    + proyecto.getDescripcion() + "<br><br>" + ca + MsgLider);
                } else if (oldProyecto.getEtapa().getId().equals(2) && proyecto.getEtapa().getId().equals(1)) {
                    this.mailService.sendSimpleMail(oldProyecto.getLider().getEmail(),
                            "REMOVIDO DE PROYECTO " + proyecto.getNombre() + "-" + proyecto.getDescripcion(),
                            "Buen Dia.<br><br> Usted a sido Removido como lider del proyecto " + proyecto.getNombre() + "-"
                                    + proyecto.getDescripcion()
                                    + " Por favor revisar.<br><br><br>Gerencia De Proyectos Y Servicios.<br>Its Solution SAS.");
                } else if (proyecto.getEtapa().getId().equals(2) && oldProyecto.getEtapa().getId().equals(2)
                        && !oldProyecto.getLider().getId().equals(proyecto.getLider().getId())) {
                    MsgLider = this.tableCrnProject(proyecto);
                    this.mailService.sendSimpleMail(oldProyecto.getLider().getEmail(),
                            "REMOVIDO DE PROYECTO " + proyecto.getNombre() + "-" + proyecto.getDescripcion(),
                            "Buen Dia.<br><br> Usted a sido Removido como lider del proyecto " + proyecto.getNombre() + "-"
                                    + proyecto.getDescripcion()
                                    + " Por favor revisar.<br><br><br>Gerencia De Proyectos Y Servicios.<br>Its Solution SAS.");
                    this.mailService.sendSimpleMail(
                            this.empleadoService.getEmpleadoById(proyecto.getLider().getId()).getEmail(),
                            "ASIGNADO A PROYECTO " + proyecto.getNombre() + "-" + proyecto.getDescripcion(),
                            "Buen Dia.<br><br> Usted a sido asignado como lider al proyecto " + proyecto.getNombre() + "-"
                                    + proyecto.getDescripcion() + "<br><br>" + ca + MsgLider);
                } else {
                    if (proyecto.getEtapa().getId().equals(2) && proyecto.getLider().getId().equals(creator)) {
                        this.mailService.sendSimpleMail(this.empleadoService.getEmpleadoById(creator).getEmail(),
                                "CAMBIO DATOS DE PROYECTO " + oldProyecto.getNombre() + "-" + oldProyecto.getDescripcion(),
                                ca + "Se" + mod + MsgPrp);
                    }

                    if (proyecto.getEtapa().getId().equals(2) && !proyecto.getLider().getId().equals(creator)) {
                        this.mailService.sendSimpleMail(this.empleadoService.getEmpleadoById(creator).getEmail(),
                                "CAMBIO DATOS DE PROYECTO " + oldProyecto.getNombre() + "-" + oldProyecto.getDescripcion(),
                                ca + this.empleadoService.getEmpleadoById(creator).getNombre() + mod + MsgPrp);
                    }
                }

                List<EmpleadoRol> LGerentes = this.empleadoRolService.findByRol(this.rolService.findById(4));

                for (int i = 0; i < LGerentes.size(); ++i) {
                    if (((EmpleadoRol) LGerentes.get(i)).getEmpleado().getId() == creator) {
                        this.mailService.sendSimpleMail(((EmpleadoRol) LGerentes.get(i)).getEmpleado().getEmail(),
                                "CAMBIO DATOS DE PROYECTO " + oldProyecto.getNombre() + "-" + oldProyecto.getDescripcion(),
                                ca + "Se" + mod + MsgPrp);
                    } else {
                        this.mailService.sendSimpleMail(((EmpleadoRol) LGerentes.get(i)).getEmpleado().getEmail(),
                                "CAMBIO DATOS DE PROYECTO " + oldProyecto.getNombre() + "-" + oldProyecto.getDescripcion(),
                                ca + this.empleadoService.getEmpleadoById(creator).getNombre() + mod + MsgPrp);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Exception at projectDataChange");
            e.printStackTrace();
        }
    }

    public String tableChangeProyect(Proyecto proyecto) {
        Proyecto oldProyecto = this.proyectoService.getProyectoById(proyecto.getId());
        String Table = "<body>\r\n    <table><thead>\r\n        <tr>\r\n        <th>Proyecto</th>\r\n        <th>"
                + proyecto.getNombre() + "-" + proyecto.getDescripcion()
                + "</th>\r\n    </tr>\r\n    </thead>\r\n    <tbody>\r\n";
        try {
            if (!oldProyecto.getCliente().getId().equals(proyecto.getCliente().getId())) {
                Table = Table + "        <tr>\r\n          <td> Cliente </td>\r\n          <td>"
                        + proyecto.getCliente().getNombre() + "</td>\r\n        </tr>\r\n";
                this.c = true;
            }

            if (!oldProyecto.getCodigo().equals(proyecto.getCodigo())) {
                Table = Table + "        <tr>\r\n          <td> Codigo Del Proyecto </td>\r\n          <td>"
                        + proyecto.getCodigo() + "</td>\r\n        </tr>\r\n";
                this.c = true;
            }

            if (!oldProyecto.getEtapa().getId().equals(proyecto.getEtapa().getId())) {
                if (proyecto.getEtapa().getId().equals(1)) {
                    Table = Table + "        <tr>\r\n          <td> Con Etapa </td>\r\n          <td>("
                            + proyecto.getEtapa().getEtapa() + ") Propuesta Real De Proyecto</td>\r\n        </tr>\r\n";
                } else {
                    Table = Table + "        <tr>\r\n          <td> Con Etapa </td>\r\n          <td>("
                            + proyecto.getEtapa().getEtapa() + ") Caso Real De Proyecto</td>\r\n        </tr>\r\n";
                }

                this.c = true;
            }

            if (!oldProyecto.getTipo().getId().equals(proyecto.getTipo().getId())) {
                Table = Table + "        <tr>\r\n          <td> Tipo De Proyecto </td>\r\n          <td>"
                        + proyecto.getTipo().getTipo() + "</td>\r\n        </tr>\r\n";
                this.c = true;
            }

            if (!oldProyecto.getEstadoPropuesta().getId().equals(proyecto.getEstadoPropuesta().getId())) {
                Table = Table + "        <tr>\r\n          <td> Estado De La Propuesta </td>\r\n          <td>"
                        + proyecto.getEstadoPropuesta().getEstado() + "</td>\r\n        </tr>\r\n";
                this.c = true;
            }

            if (!oldProyecto.getEstadoProyecto().getId().equals(proyecto.getEstadoProyecto().getId())) {
                Table = Table + "        <tr>\r\n          <td> Estado Del Proyecto </td>\r\n          <td>"
                        + proyecto.getEstadoProyecto().getEstado() + "</td>\r\n        </tr>\r\n";
                this.c = true;
            }

            if (proyecto.getEtapa().getId().equals(1)) {
                if (!oldProyecto.getDescripcion().equals(proyecto.getDescripcion())) {
                    Table = Table + "        <tr>\r\n          <td> La Descripcion Del Proyecto </td>\r\n          <td>"
                            + proyecto.getDescripcion() + "</td>\r\n        </tr>\r\n";
                    this.c = true;
                }

                Table = Table + "        </tbody>\r\n      </table>\r\n";
                return Table;
            } else {
                if (!oldProyecto.getHorasPlaneadas().equals(proyecto.getHorasPlaneadas())) {
                    Table = Table + "        <tr>\r\n          <td> Horas Aprobadas </td>\r\n          <td>"
                            + proyecto.getHorasPlaneadas() + "</td>\r\n        </tr>\r\n";
                    this.c = true;
                }

                if (!oldProyecto.getCosto().equals(proyecto.getCosto())) {
                    Table = Table + "        <tr>\r\n          <td> Valor Aprobado Cliente </td>\r\n          <td>"
                            + proyecto.getCosto() + "</td>\r\n        </tr>\r\n";
                    this.c = true;
                }

                if (oldProyecto.getComponente() == null
                        || !oldProyecto.getComponente().getId().equals(proyecto.getComponente().getId())) {
                    Table = Table + "        <tr>\r\n          <td> Componente </td>\r\n          <td>"
                            + this.componenteService.getComponenteById(proyecto.getComponente().getId()).getComponente()
                            + "</td>\r\n        </tr>\r\n";
                    this.c = true;
                }

                if (oldProyecto.getLider() == null || !oldProyecto.getLider().getId().equals(proyecto.getLider().getId())) {
                    Table = Table + "        <tr>\r\n          <td> lider Del Proyecto </td>\r\n          <td>"
                            + this.empleadoService.getEmpleadoById(proyecto.getLider().getId()).getNombre()
                            + "</td>\r\n        </tr>\r\n";
                    this.c = true;
                }

                if (oldProyecto.getFechaAprobacion() == null
                        || !oldProyecto.getFechaAprobacion().equals(proyecto.getFechaAprobacion())) {
                    Table = Table + "        <tr>\r\n          <td> Fecha De Aprobacion </td>\r\n          <td>"
                            + proyecto.getFechaAprobacion() + "</td>\r\n        </tr>\r\n";
                    this.c = true;
                }

                if (oldProyecto.getFechaInicio() == null
                        || !oldProyecto.getFechaInicio().equals(proyecto.getFechaInicio())) {
                    Table = Table + "        <tr>\r\n          <td> Fecha De Inicio </td>\r\n          <td>"
                            + proyecto.getFechaInicio() + "</td>\r\n        </tr>\r\n";
                    this.c = true;
                }

                if (oldProyecto.getDirectorClient() == null
                        || !oldProyecto.getDirectorClient().equals(proyecto.getDirectorClient())) {
                    Table = Table + "        <tr>\r\n          <td> Director Asignado Cliente </td>\r\n          <td>"
                            + proyecto.getDirectorClient() + "</td>\r\n        </tr>\r\n";
                    this.c = true;
                }

                if (oldProyecto.getDirectorIts() == null
                        || !oldProyecto.getDirectorIts().getId().equals(proyecto.getDirectorIts().getId())) {
                    Table = Table + "        <tr>\r\n          <td> Director Asignado ITS </td>\r\n          <td>"
                            + this.empleadoService.getEmpleadoById(proyecto.getDirectorIts().getId()).getNombre()
                            + "</td>\r\n        </tr>\r\n";
                    this.c = true;
                }

                if (!oldProyecto.getDescripcion().equals(proyecto.getDescripcion())) {
                    Table = Table + "        <tr>\r\n          <td> Descripcion Del Proyecto </td>\r\n          <td>"
                            + proyecto.getDescripcion() + "</td>\r\n        </tr>\r\n";
                    this.c = true;
                }

                Table = Table + "        </tbody>\r\n      </table>\r\n";
            }
        } catch (Exception e) {
            System.out.println("Exception at tableChangeProyect");
            e.printStackTrace();
        }
        return Table;
    }

    public String tablePrpProject(Proyecto proyecto) {
        String Table = "<body>\r\n    <table><thead>\r\n        <tr>\r\n        <th>Proyecto</th>\r\n        <th>"
                + proyecto.getNombre() + "-" + proyecto.getDescripcion()
                + "</th>\r\n    </tr>\r\n    </thead>\r\n    <tbody>\r\n        <tr>\r\n          <td> Cliente </td>\r\n          <td>"
                + proyecto.getCliente().getNombre()
                + "</td>\r\n        </tr>\r\n        <tr>\r\n          <td> Codigo Del Proyecto </td>\r\n          <td>"
                + proyecto.getCodigo()
                + "</td>\r\n        </tr>\r\n        <tr>\r\n          <td> Con Etapa </td>\r\n          <td>("
                + proyecto.getEtapa().getEtapa()
                + ") Propuesta Real De Proyecto</td>\r\n        </tr>\r\n        <tr>\r\n          <td> Tipo De Proyecto </td>\r\n          <td>"
                + proyecto.getTipo().getTipo()
                + "</td>\r\n        </tr>\r\n        <tr>\r\n          <td> Estado De La Propuesta </td>\r\n          <td>"
                + proyecto.getEstadoPropuesta().getEstado()
                + "</td>\r\n        </tr>\r\n        <tr>\r\n          <td> Estado Del Proyecto </td>\r\n          <td>"
                + proyecto.getEstadoProyecto().getEstado()
                + "</td>\r\n        </tr>\r\n        <tr>\r\n          <td> Descripcion Del Proyecto </td>\r\n          <td>"
                + proyecto.getDescripcion() + "</td>\r\n        </tr>\r\n        </tbody>\r\n      </table>\r\n";
        return Table;
    }

    public String tableCrnProject(Proyecto proyecto) {
        String Table = "    <table><thead>\r\n        <tr>\r\n        <th>Proyecto</th>\r\n        <th>"
                + proyecto.getNombre() + "-" + proyecto.getDescripcion()
                + "</th>\r\n    </tr>\r\n    </thead>\r\n    <tbody>\r\n        <tr>\r\n          <td> Cliente </td>\r\n          <td>"
                + proyecto.getCliente().getNombre()
                + "</td>\r\n        </tr>\r\n        <tr>\r\n          <td> codigo Del Proyecto </td>\r\n          <td>"
                + proyecto.getCodigo()
                + "</td>\r\n        </tr>\r\n        <tr>\r\n          <td> Con Etapa </td>\r\n          <td>("
                + proyecto.getEtapa().getEtapa()
                + ") Caso Real De Negocio </td>\r\n        </tr>\r\n        <tr>\r\n          <td> Tipo De Proyecto </td>\r\n          <td>"
                + proyecto.getTipo().getTipo()
                + "</td>\r\n        </tr>\r\n        <tr>\r\n          <td> Estado De La Propuesta </td>\r\n          <td>"
                + proyecto.getEstadoPropuesta().getEstado()
                + "</td>\r\n        </tr>\r\n        <tr>\r\n          <td> Estado Del Proyecto </td>\r\n          <td>"
                + proyecto.getEstadoProyecto().getEstado()
                + "</td>\r\n        </tr>\r\n        <tr>\r\n          <td> Horas Aprobadas </td>\r\n          <td>"
                + proyecto.getHorasPlaneadas()
                + "</td>\r\n        </tr>\r\n        <tr>\r\n          <td> Valor Aprobado Cliente </td>\r\n          <td>"
                + proyecto.getCosto() + "</td>\r\n        </tr>\r\n";
        if (proyecto.getComponente().getComponente() != null) {
            Table = Table + "        <tr>\r\n          <td> Componente </td>\r\n          <td>"
                    + proyecto.getComponente().getComponente() + "</td>\r\n        </tr>\r\n";
        } else {
            Table = Table + "        <tr>\r\n          <td> Componente </td>\r\n          <td>"
                    + this.componenteService.getComponenteById(proyecto.getComponente().getId()).getComponente()
                    + "</td>\r\n        </tr>\r\n";
        }

        if (proyecto.getLider().getNombre() != null) {
            Table = Table + "        <tr>\r\n          <td> Componente </td>\r\n          <td>"
                    + proyecto.getLider().getNombre() + "</td>\r\n        </tr>\r\n";
        } else {
            Table = Table + "        <tr>\r\n          <td> Componente </td>\r\n          <td>"
                    + this.empleadoService.getEmpleadoById(proyecto.getLider().getId()).getNombre()
                    + "</td>\r\n        </tr>\r\n";
        }

        Table = Table + "        <tr>\r\n          <td> Fecha De Aprobacion </td>\r\n          <td>"
                + proyecto.getFechaAprobacion()
                + "</td>\r\n        </tr>\r\n        <tr>\r\n          <td> Fecha De Inicio </td>\r\n          <td>"
                + proyecto.getFechaInicio()
                + "</td>\r\n        </tr>\r\n        </tr>\r\n        <tr>\r\n          <td> Director Asignado Cliente </td>\r\n          <td>"
                + proyecto.getDirectorClient() + "</td>\r\n        </tr>\r\n";
        if (proyecto.getDirectorIts().getNombre() != null) {
            Table = Table + "        <tr>\r\n          <td> Director Asignado ITS </td>\r\n          <td>"
                    + proyecto.getDirectorIts().getNombre() + "</td>\r\n        </tr>\r\n";
        } else {
            Table = Table + "        <tr>\r\n          <td> Director Asignado ITS </td>\r\n          <td>"
                    + this.empleadoService.getEmpleadoById(proyecto.getDirectorIts().getId()).getNombre()
                    + "</td>\r\n        </tr>\r\n";
        }

        Table = Table + "        <tr>\r\n          <td> Descripcion Del Proyecto </td>\r\n          <td>"
                + proyecto.getDescripcion() + "</td>\r\n        </tr>\r\n        </tbody>\r\n      </table>\r\n";
        return Table;
    }
}

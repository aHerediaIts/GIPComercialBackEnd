//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.controller;

import com.backendgip.model.Empleado;
import com.backendgip.model.Facturacion;
import com.backendgip.model.Proyecto;
import com.backendgip.model.ReporteFacturacionCliente;
import com.backendgip.model.ReporteProyectoRecurso;
import com.backendgip.model.ReporteTiempo;
import com.backendgip.model.ReporteTiempoCliente;
import com.backendgip.model.ReporteTiempoEmpleado;
import com.backendgip.service.ClienteService;
import com.backendgip.service.EmpleadoService;
import com.backendgip.service.FacturacionService;
import com.backendgip.service.ProyectoService;
import com.backendgip.service.RecursoActividadService;
import com.backendgip.service.ReporteTiempoService;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Transactional
@RestController
@RequestMapping({"/api/reportes"})
public class ReporteAppController {
    @Autowired
    private ReporteTiempoService reporteTiempoService;
    @Autowired
    private EmpleadoService empleadoService;
    @Autowired
    private ProyectoService proyectoService;
    @Autowired
    private FacturacionService facturacionService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private RecursoActividadService recursoActividadService;

    public ReporteAppController() {
    }

    @GetMapping({"/tiempos-reportados/{fechaInicio}/{fechaFin}/{empleadosString}"})
    public ResponseEntity<?> findAllReportesTiemposReportados(@PathVariable String fechaInicio,
                                                              @PathVariable String fechaFin, @PathVariable String empleadosString) {
        List<ReporteTiempoEmpleado> reportes = new ArrayList();
        String[] empleadosArray = empleadosString.split(",");
        LocalDate fechaI = this.stringToLocalDate(fechaInicio);
        LocalDate fechaF = this.stringToLocalDate(fechaFin);
        List<Empleado> empleados = this.getEmpleadosByArray(empleadosArray);
        Iterator var9 = empleados.iterator();

        while (var9.hasNext()) {
            Empleado e = (Empleado) var9.next();
            List<ReporteTiempo> reportesT = this.getReportesBetweenFechaInicioAndFechaFinAndEmpleados(fechaI, fechaF,
                    e);
            System.out.println(reportesT.size());
            List<String> proyectosXReporte = new ArrayList();
            Iterator var13 = reportesT.iterator();

            while (var13.hasNext()) {
                ReporteTiempo r = (ReporteTiempo) var13.next();
                if (r.getEstado().getEstado().equalsIgnoreCase("APROBADO")
                        && !proyectosXReporte.contains(r.getProyecto().getDescripcion())) {
                    proyectosXReporte.add(r.getProyecto().getDescripcion());
                    ReporteTiempoEmpleado reporte = new ReporteTiempoEmpleado();
                    reporte.setRecurso(e.getNombre());
                    reporte.setProyecto(r.getProyecto().getNombre());
                    reporte.setDescripcion(r.getProyecto().getDescripcion());
                    reporte.setActividad(r.getActividad().getActividad().getActividad());
                    reporte.setHoras(this.getTotalHorasReportesProyectoRecurso(r.getEmpleado(), r.getProyecto(), fechaI,
                            fechaF));
                    reportes.add(reporte);
                }
            }
        }

        return ResponseEntity.ok(reportes);
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

    public List<ReporteTiempo> getReportesBetweenFechaInicioAndFechaFinAndEmpleados(LocalDate fechaInicio,
                                                                                    LocalDate fechaFin, Empleado empleado) {
        List<ReporteTiempo> reporteslist = this.reporteTiempoService.findByEmpleado(empleado);
        List<ReporteTiempo> reportes = new ArrayList();
        Iterator var6 = reporteslist.iterator();

        while (true) {
            ReporteTiempo r;
            do {
                do {
                    if (!var6.hasNext()) {
                        return reportes;
                    }

                    r = (ReporteTiempo) var6.next();
                } while (!r.getFecha().isAfter(fechaInicio) && !r.getFecha().isEqual(fechaInicio));
            } while (!r.getFecha().isBefore(fechaFin) && !r.getFecha().isEqual(fechaFin));

            reportes.add(r);
        }
    }

    public List<ReporteTiempo> getReportesBetweenFechaInicioAndFechaFin(LocalDate fechaInicio, LocalDate fechaFin) {
        List<Empleado> allEmpleados = this.empleadoService.getEmpleado();
        List<ReporteTiempo> reportes = new ArrayList();
        List<LocalDate> fechas = this.empleadoService.getFechasBetween(fechaInicio, fechaFin);
        Iterator var6 = allEmpleados.iterator();

        while (var6.hasNext()) {
            Empleado e = (Empleado) var6.next();
            Iterator var8 = fechas.iterator();

            while (var8.hasNext()) {
                LocalDate f = (LocalDate) var8.next();
                if (!this.reporteTiempoService.existsByEmpleadoAndFecha(e, f)
                        && this.recursoActividadService.existsByEmpleadoAndFecha(e, f)) {
                    ReporteTiempo reporte = new ReporteTiempo();
                    reporte.setEmpleado(e);
                    reporte.setFecha(f);
                    reportes.add(reporte);
                }
            }
        }

        return reportes;
    }

    @GetMapping({"/clientes/facturacion/{fechaInicio}/{fechaFin}/{clientesString}"})
    public ResponseEntity<?> findAllReportesFacturacionCliente(@PathVariable String fechaInicio,
                                                               @PathVariable String fechaFin, @PathVariable String clientesString) {
        LocalDate fechaI = this.stringToLocalDate(fechaInicio);
        LocalDate fechaF = this.stringToLocalDate(fechaFin);
        String[] clientesArray = clientesString.split(",");
        List<Proyecto> proyectos = this.getProyectosBetweenFechaInicioAndFechaFin(fechaI, fechaF, clientesArray);
        List<ReporteFacturacionCliente> reportes = new ArrayList();
        Iterator var9 = proyectos.iterator();

        while (var9.hasNext()) {
            Proyecto p = (Proyecto) var9.next();
            List<Facturacion> cobros = this.facturacionService.getCobrosByProyecto(p);

            ReporteFacturacionCliente reporte;
            for (Iterator var12 = cobros.iterator(); var12.hasNext(); reportes.add(reporte)) {
                Facturacion c = (Facturacion) var12.next();
                reporte = new ReporteFacturacionCliente();
                reporte.setCliente(p.getCliente().getNombre());
                reporte.setProyecto(p.getNombre());
                reporte.setdescripcion(p.getDescripcion());
                reporte.setnPagos(this.facturacionService.getCobrosByProyecto(p).size());
                reporte.setPorcentaje(c.getPorcentaje());
                reporte.setPrecio(c.getValorPagar());
                reporte.setFechaPlaneada(c.getFechaPlaneada().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                reporte.setEstado(c.getEstado().getEstado());
                reporte.setValorAprobado(p.getCosto());
                reporte.setValorCobrado(this.getValorCobradoReporteFacturacionCliente(p));
                reporte.setFacturasPagas(this.getFacturasPagasReporteFacturacionCliente(cobros));
                reporte.setFacturasPendientes(this.getFacturasPendientesReporteFacturacionCliente(cobros));
                if (c.getFechaPago() != null) {
                    reporte.setFechaPago(c.getFechaPago().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                } else {
                    reporte.setFechaPago("");
                }
            }
        }

        return ResponseEntity.ok(reportes);
    }

    public List<Proyecto> getProyectosBetweenFechaInicioAndFechaFin(LocalDate fechaInicio, LocalDate fechaFin,
                                                                    String[] clientes) {
        List<Proyecto> proyectosList = new ArrayList();
        List<Proyecto> proyectos = new ArrayList();

        for (int i = 0; i < clientes.length; ++i) {
            List<Proyecto> proyectosByCliente = this.proyectoService
                    .findByCliente(this.clienteService.getClienteById(Integer.parseInt(clientes[i])));
            proyectosList.addAll(proyectosByCliente);
        }

        Iterator var8 = proyectosList.iterator();
        Proyecto p = new Proyecto();
        while (true) {
            try {
                do {
                    do {
                        if (!var8.hasNext()) {
                            return proyectos;
                        }

                        p = (Proyecto) var8.next();
                    } while (!p.getFechaCreacion().isAfter(fechaInicio) && !p.getFechaCreacion().isEqual(fechaInicio));
                } while (!p.getFechaCreacion().isBefore(fechaFin) && !p.getFechaCreacion().isEqual(fechaFin));

                proyectos.add(p);
            } catch (Exception e) {
                System.out.println("ERROR --> getProyectosBetweenFechaInicioAndFechaFin");
                System.out.println(p);
                System.out.println(e.getMessage());
            }
        }
    }

    public List<Proyecto> getProyectosByArray(String[] proyectosArray) {
        List<Proyecto> proyectos = new ArrayList();

        for (int i = 0; i < proyectosArray.length; ++i) {
            Proyecto proyecto = this.proyectoService.getProyectoById(Integer.parseInt(proyectosArray[i]));
            proyectos.add(proyecto);
        }

        return proyectos;
    }

    public Integer getValorCobradoReporteFacturacionCliente(Proyecto proyecto) {
        List<Facturacion> cobros = this.facturacionService.getCobrosByProyecto(proyecto);
        Integer totalValor = 0;
        Iterator var4 = cobros.iterator();

        while (var4.hasNext()) {
            Facturacion c = (Facturacion) var4.next();
            if (c.getEstado().getEstado().equalsIgnoreCase("FACTURADO")) {
                totalValor = totalValor + c.getValorPagado();
            }
        }

        return totalValor;
    }

    public Integer getFacturasPagasReporteFacturacionCliente(List<Facturacion> cobros) {
        Integer total = 0;
        Iterator var3 = cobros.iterator();

        while (var3.hasNext()) {
            Facturacion c = (Facturacion) var3.next();
            if (c.getEstado().getEstado().equalsIgnoreCase("FACTURADO")) {
                total = total + 1;
            }
        }

        return total;
    }

    public Integer getFacturasPendientesReporteFacturacionCliente(List<Facturacion> cobros) {
        Integer total = 0;
        Iterator var3 = cobros.iterator();

        while (var3.hasNext()) {
            Facturacion c = (Facturacion) var3.next();
            if (c.getEstado().getEstado().equalsIgnoreCase("PENDIENTE POR COBRAR")) {
                total = total + 1;
            }
        }

        return total;
    }

    @GetMapping({"/clientes/tiempos-reportados/{fechaInicio}/{fechaFin}/{clientesString}"})
    public ResponseEntity<?> findAllReportesTiemposClientes(@PathVariable String fechaInicio,
                                                            @PathVariable String fechaFin, @PathVariable String clientesString) {
        LocalDate fechaI = this.stringToLocalDate(fechaInicio);
        LocalDate fechaF = this.stringToLocalDate(fechaFin);
        String[] clientesArray = clientesString.split(",");
        List<Proyecto> proyectos = this.getProyectosBetweenFechaInicioAndFechaFin(fechaI, fechaF, clientesArray);
        List<ReporteTiempoCliente> reportes = new ArrayList();
        Iterator var9 = proyectos.iterator();
        Proyecto proyecto = new Proyecto();
        while (var9.hasNext()) {
            try {
                proyecto = (Proyecto) var9.next();
                ReporteTiempoCliente reporte = new ReporteTiempoCliente();
                reporte.setCliente(proyecto.getCliente().getNombre());
                reporte.setProyecto(proyecto.getNombre());
                if (proyecto.getFechaInicio() != null) {
                    reporte.setFechaInicio(proyecto.getFechaInicio().toString());
                } else {
                    reporte.setFechaInicio((String) null);
                }

                reporte.setProyectoB(proyecto);
                if (proyecto.getFechaFin() != null) {
                    reporte.setFechaFin(proyecto.getFechaFin().toString());
                } else {
                    reporte.setFechaFin((String) null);
                }

                reporte.setHorasCotizadas(proyecto.getHorasPlaneadas());
                reporte.setHorasConsumidas(this.getHorasConsumidasReporteTiempoCliente(proyecto));
                reporte.setPorcentajeAvance(this.getPorcentajeReporteTiempoCliente(proyecto));
                reportes.add(reporte);
            } catch (Exception e) {
                System.out.println("ERROR --> findAllReportesTiemposClientes");
                System.out.println(proyecto);
                System.out.println(e.getMessage());
            }
        }

        return ResponseEntity.ok(reportes);
    }

    public Integer getHorasConsumidasReporteTiempoCliente(Proyecto proyecto) {
        List<ReporteTiempo> reportes = this.reporteTiempoService.findByProyecto(proyecto);
        Integer total = 0;
        Iterator var4 = reportes.iterator();
        ReporteTiempo reporte = new ReporteTiempo();
        while (var4.hasNext()) {
            try {
                reporte = (ReporteTiempo) var4.next();
                if (reporte.getEstado() != null && reporte.getEstado().getEstado() != null &&
                        reporte.getEstado().getEstado().equalsIgnoreCase("APROBADO")) {
                    total += Optional.ofNullable(reporte.getHoras()).orElse(0);
                }
            } catch (Exception e) {
                System.out.println("ERROR --> getHorasConsumidasReporteTiempoCliente");
                System.out.println(reporte);
                System.out.println(e.getMessage());
            }
        }

        return total;
    }

    public Integer getPorcentajeReporteTiempoCliente(Proyecto proyecto) {
        List<ReporteTiempo> reportes = this.reporteTiempoService.findByProyecto(proyecto);
        Integer total = 0;
        Iterator var4 = reportes.iterator();
        ReporteTiempo reporte = new ReporteTiempo();
        while (var4.hasNext()) {
            try {
                reporte = (ReporteTiempo) var4.next();
                if (reporte.getEstado() != null && reporte.getEstado().getEstado() != null &&
                        reporte.getEstado().getEstado().equalsIgnoreCase("APROBADO")) {
                    total += Optional.ofNullable(reporte.getHoras()).orElse(0);
                }
            } catch (Exception e) {
                System.out.println("ERROR --> getPorcentajeReporteTiempoCliente");
                System.out.println(reporte);
                System.out.println(e.getMessage());
            }
        }

        if (proyecto.getHorasPlaneadas() == null ||
                proyecto.getHorasPlaneadas() < 1) {
            return 0;
        } else {
            return total * 100 / proyecto.getHorasPlaneadas();
        }
    }

    @GetMapping({"/proyectos/tiempos-reportados/{fechaInicio}/{fechaFin}/{proyectosString}"})
    public ResponseEntity<?> getReportesProyectoRecurso(@PathVariable String fechaInicio, @PathVariable String fechaFin,
                                                        @PathVariable String proyectosString) {
        LocalDate fechaI = this.stringToLocalDate(fechaInicio);
        LocalDate fechaF = this.stringToLocalDate(fechaFin);
        String[] proyectosArray = proyectosString.split(",");
        List<Proyecto> proyectos = this.getProyectosByArray(proyectosArray);
        List<ReporteProyectoRecurso> reportes = new ArrayList();
        List<Empleado> empleados = this.empleadoService.getEmpleado();
        Iterator var10 = empleados.iterator();

        while (var10.hasNext()) {
            Empleado e = (Empleado) var10.next();
            Iterator var12 = proyectos.iterator();

            while (var12.hasNext()) {
                Proyecto p = (Proyecto) var12.next();
                if (this.validExistsReporteTiempoEmpleadoAndProyectoAndFecha(e, p, fechaI, fechaF)) {
                    ReporteProyectoRecurso reporte = new ReporteProyectoRecurso();
                    reporte.setProyecto(p.getNombre());
                    reporte.setDescripcion(p.getDescripcion());
                    reporte.setRecurso(e.getNombre());
                    reporte.setHorasReportadas(this.getTotalHorasReportesProyectoRecurso(e, p, fechaI, fechaF));
                    reportes.add(reporte);
                }
            }
        }

        return ResponseEntity.ok(reportes);
    }

    @GetMapping({"/tiempos-reportados/empleados-pendientes/{fechaInicio}/{fechaFin}"})
    public ResponseEntity<?> getEmpleadosPendientesReporteTiempo(@PathVariable String fechaInicio,
                                                                 @PathVariable String fechaFin) {
        LocalDate fechaI = this.stringToLocalDate(fechaInicio);
        LocalDate fechaF = this.stringToLocalDate(fechaFin);
        LocalDate fechaA = LocalDate.now(ZoneId.of("America/Bogota"));
        if (this.empleadoService.getFechasBetween(fechaI, fechaF).size() > 31) {
            return ResponseEntity.badRequest().body("El rango de fechas no puede ser mayor a 31 dias.");
        } else if (!fechaI.isAfter(fechaA) && !fechaF.isAfter(fechaA)) {
            return fechaI.isAfter(fechaF)
                    ? ResponseEntity.badRequest().body("La fecha de inicio no puede ser mayor a la fecha fin.")
                    : ResponseEntity.ok(this.getReportesBetweenFechaInicioAndFechaFin(fechaI, fechaF));
        } else {
            return ResponseEntity.badRequest().body("La fecha de inicio o fin no puede ser mayor a la fecha actual.");
        }
    }

    public boolean validExistsReporteTiempoEmpleadoAndProyectoAndFecha(Empleado empleado, Proyecto proyecto,
                                                                       LocalDate fechaInicio, LocalDate fechaFin) {
        List<ReporteTiempo> reportes = this.reporteTiempoService.findByEmpleadoAndProyecto(empleado, proyecto);
        boolean flag = false;
        Iterator var7 = reportes.iterator();

        while (true) {
            ReporteTiempo r;
            do {
                do {
                    if (!var7.hasNext()) {
                        return flag;
                    }

                    r = (ReporteTiempo) var7.next();
                } while (!r.getFecha().isAfter(fechaInicio) && !r.getFecha().isEqual(fechaInicio));
            } while (!r.getFecha().isBefore(fechaFin) && !r.getFecha().isEqual(fechaFin));

            flag = true;
        }
    }

    public Integer getTotalHorasReportesProyectoRecurso(Empleado empleado, Proyecto proyecto, LocalDate fechaInicio,
                                                        LocalDate fechaFin) {
        List<ReporteTiempo> reportes = this.reporteTiempoService.findByEmpleadoAndProyecto(empleado, proyecto);
        Integer total = 0;
        Iterator var7 = reportes.iterator();

        while (true) {
            ReporteTiempo r;
            do {
                do {
                    do {
                        if (!var7.hasNext()) {
                            return total;
                        }

                        r = (ReporteTiempo) var7.next();
                    } while (!r.getEstado().getEstado().equalsIgnoreCase("APROBADO"));
                } while (!r.getFecha().isAfter(fechaInicio) && !r.getFecha().isEqual(fechaInicio));
            } while (!r.getFecha().isBefore(fechaFin) && !r.getFecha().isEqual(fechaFin));

            total = total + r.getHoras();
        }
    }
}

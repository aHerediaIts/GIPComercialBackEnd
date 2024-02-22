//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.controller;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.Empleado;
import com.backendgip.model.EmpleadoEspecialidad;
import com.backendgip.model.EstadoProyecto;
import com.backendgip.model.LogSistema;
import com.backendgip.model.PSRStatus;
import com.backendgip.model.ProjectStatusReport;
import com.backendgip.repository.EmpleadoRepository;
import com.backendgip.repository.ProjectStatusReportRepository;
import com.backendgip.service.LogSistemaService;
import com.backendgip.service.PSRStatusService;
import java.util.Date;
import java.util.HashMap;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

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
@RequestMapping({ "/api" })
public class PSRStatusController {
    @Autowired
    private PSRStatusService PSRStatusService;
    @Autowired
    private LogSistemaService logService;

    @Autowired

    public PSRStatusController() {
    }

    @GetMapping({ "/psrstatus" })
    public List<PSRStatus> getProjectStatusReportsStatus() {
        return this.PSRStatusService.getPSRStatus();
    }

    @GetMapping({ "/psrstatus/ProjectStatusReportById/{id}" })
    public List<PSRStatus> findByProjectStatusReportById(@PathVariable Integer id) {
        return this.PSRStatusService.findByProjectStatusReportId(id);
    }

    @GetMapping({ "/psrstatus/ProjectStatusReportByCodigoProyecto/{codigo}" })
    public List<PSRStatus> ProjectStatusReportByCodigoProyecto(@PathVariable String codigo ) {
        return this.PSRStatusService.findByCodigoProyecto(codigo);
    }


    @PostMapping({ "/psrstatus" })
    public ResponseEntity<?> savePsrStatus(@RequestBody PSRStatus psrStatus) {

        LocalDateTime fechaDate = LocalDateTime.now(ZoneId.of("America/Bogota"));        
        psrStatus.setFechaCreacionStatus(fechaDate);
        this.PSRStatusService.savePSRStatus(psrStatus);

        LogSistema log = new LogSistema();
        log.setAccion("CREATE");
        log.setFechaHora(new Date());
        log.setTabla(ProjectStatusReport.class.toString());
        // log.setIdAccion(psr.getId());
        log.setDescripcion(psrStatus.toString());
        this.logService.saveLog(log);

        return ResponseEntity.ok(psrStatus);
    }

    @DeleteMapping({ "/psrstatus/{id}" })
    public ResponseEntity<Map<String, Boolean>> deleteEstado(@PathVariable Integer id) {
        this.PSRStatusService.deletePSRStatusById(id);
        Map<String, Boolean> response = new HashMap();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}

package com.backendgip.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backendgip.model.LogSistema;
import com.backendgip.model.ParametriaGeneralMatrizTiempo;
import com.backendgip.model.ProjectStatusReport;
import com.backendgip.service.LogSistemaService;
import com.backendgip.service.ParametriaGeneralMatrizTiempoService;
import com.backendgip.model.Generarmatriztiemposasignacionrecursos;
import com.backendgip.model.Generarmatriztiempostiporecursos;


@RestController
@Transactional
@RequestMapping({ "/api" })
public class ParametriaGeneralMatrizTiempoController {

    @Autowired
    private ParametriaGeneralMatrizTiempoService parametrosGeneralesMatrizTiempoService;
    @Autowired
    private LogSistemaService logService;

    public ParametriaGeneralMatrizTiempoController() {
    }

    @GetMapping({ "/parametriaGeneralMatrizTiempo" })
    public ParametriaGeneralMatrizTiempo getParametriaGeneralMatrizTiempos() {
        return this.parametrosGeneralesMatrizTiempoService.getParametrosGenerales().get(0);
    }

    @PostMapping({ "/parametriaGeneralMatrizTiempo" })
    public ResponseEntity<?> saveParametriaGeneralMatrizTiempos(
            @RequestBody ParametriaGeneralMatrizTiempo parametriaNueva) {

        LocalDateTime fechaDate = LocalDateTime.now(ZoneId.of("America/Bogota"));
        parametriaNueva.setFechaUpdate(fechaDate);
        this.parametrosGeneralesMatrizTiempoService.saveParametrosGenerales(parametriaNueva);

        LogSistema log = new LogSistema();
        log.setAccion("UPDATE");
        log.setFechaHora(new Date());
        log.setTabla(ProjectStatusReport.class.toString());
        log.setIdAccion(parametriaNueva.getId());
        log.setDescripcion(parametriaNueva.toString());
        this.logService.saveLog(log);

        return ResponseEntity.ok(parametriaNueva);
    }

    @PostMapping({ "/parametriaGeneralMatrizTiempo/saveresursosasgi" })
	public ResponseEntity<?> saveRecursoAsignado(@RequestBody Generarmatriztiemposasignacionrecursos[] recursosAsignados) {
        for (int i = 0; i < recursosAsignados.length; i++) {
            this.parametrosGeneralesMatrizTiempoService.saveRecursosAsignados(recursosAsignados[i]);
        }
			LogSistema log = new LogSistema();
			log.setAccion("CREATE");
			log.setTabla(Generarmatriztiemposasignacionrecursos.class.toString());
			log.setDescripcion(recursosAsignados.toString());
			this.logService.saveLog(log);
			return ResponseEntity.ok(recursosAsignados);
		}
}

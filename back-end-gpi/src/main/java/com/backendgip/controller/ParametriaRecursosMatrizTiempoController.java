package com.backendgip.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.PropertyMapper.SourceOperator;
import org.springframework.http.HttpStatus;
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
import com.backendgip.model.LogSistema;
import com.backendgip.model.ParametriaRecursosMatrizTiempo;
import com.backendgip.service.LogSistemaService;
import com.backendgip.service.ParametriaRecursosMatrizTiempoService;

@RestController
@Transactional
@RequestMapping({ "/api" })
public class ParametriaRecursosMatrizTiempoController {

    @Autowired
    private ParametriaRecursosMatrizTiempoService parametriaRecursosMatrizTiempoService;

    @Autowired
    private LogSistemaService logService;

    public ParametriaRecursosMatrizTiempoController() {
    }

    @GetMapping({ "/parametriaRecursosMatrizTiempo" })
    public List<ParametriaRecursosMatrizTiempo> getParametriaRecursosMatrizTiempo() {
        return this.parametriaRecursosMatrizTiempoService.getParametriaRecursos();
    }

    @GetMapping({ "/parametriaRecursosMatrizTiempo/search/{id}" })
    public ParametriaRecursosMatrizTiempo findByParametriaRecursosById(@PathVariable Integer id) {
        ParametriaRecursosMatrizTiempo recurso = new ParametriaRecursosMatrizTiempo();
        recurso = this.parametriaRecursosMatrizTiempoService.findById(id);
        return recurso;
    }

    @PutMapping({ "/parametriaRecursosMatrizTiempo/update/{id}" })
    public ResponseEntity<?> updateParametriaRecurso(@PathVariable Integer id,
            @RequestBody ParametriaRecursosMatrizTiempo parametriaRecursosNueva) {
        try {
            System.out.println(id);
            System.out.println(parametriaRecursosNueva);
            ParametriaRecursosMatrizTiempo recurso = this.parametriaRecursosMatrizTiempoService.findById(id);
            System.out.println(recurso);
            LogSistema log = new LogSistema();
            log.setAccion("UPDATE");
            log.setDescripcion(recurso.toString());
            log.setFechaHora(new Date());
            log.setIdAccion(recurso.getId());
            log.setTabla(recurso.getClass().toString());
            this.logService.saveLog(log);
            recurso.setCliente(parametriaRecursosNueva.getCliente());
            recurso.setEmpleado(parametriaRecursosNueva.getEmpleado());
            recurso.setTarifaHora(parametriaRecursosNueva.getTarifaHora());
            recurso.setTarifaMensual(parametriaRecursosNueva.getTarifaMensual());
            ParametriaRecursosMatrizTiempo updatedRecurso = this.parametriaRecursosMatrizTiempoService
                    .saveParametriaRecursos(recurso);
            System.out.println(updatedRecurso);
            System.out.println(recurso);
            return ResponseEntity.ok(updatedRecurso);
        } catch (Exception e) {
            Error error = new Error("Ocurrió un error durante la actualización: " + e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping({ "/parametriaRecursosMatrizTiempo" })
    public ResponseEntity<?> saveParametriaRecursosMatrizTiempo(
            @RequestBody ParametriaRecursosMatrizTiempo parametriaRecursosNueva) {

        try {
            this.parametriaRecursosMatrizTiempoService.saveParametriaRecursos(parametriaRecursosNueva);

            LogSistema log = new LogSistema();
            log.setAccion("CREATE");
            log.setFechaHora(new Date());
            log.setIdAccion(parametriaRecursosNueva.getId());
            log.setDescripcion(parametriaRecursosNueva.toString());
            this.logService.saveLog(log);

            return ResponseEntity.ok(parametriaRecursosNueva);
        } catch (Exception e) {
            Error error = new Error("Ocurrió un error durante la creación: " + e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping({ "/parametriaRecursosMatrizTiempo/{id}" })
    public ResponseEntity<Map<String, Boolean>> deleteEstado(@PathVariable Integer id) {
        this.parametriaRecursosMatrizTiempoService.deleteParametriaRecursosById(id);
        Map<String, Boolean> response = new HashMap();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
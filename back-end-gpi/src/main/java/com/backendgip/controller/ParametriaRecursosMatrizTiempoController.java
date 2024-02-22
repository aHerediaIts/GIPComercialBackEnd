package com.backendgip.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.PropertyMapper.SourceOperator;
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
import com.backendgip.model.ProjectStatusReport;
import com.backendgip.service.LogSistemaService;
import com.backendgip.service.ParametriaRecursosMatrizTiempoService;
import com.backendgip.model.EspecialidadRecurso;
import com.backendgip.service.EspecialidadRecursoService;
import com.backendgip.model.PerfilesRecurso;
import com.backendgip.service.PerfilesRecursoService;


@RestController
@Transactional
@RequestMapping({ "/api" })
public class ParametriaRecursosMatrizTiempoController {

    @Autowired
    private ParametriaRecursosMatrizTiempoService parametriaRecursosMatrizTiempoService;

    @Autowired
    private LogSistemaService logService;

    @Autowired
    private EspecialidadRecursoService especialidadRecursoService;

    @Autowired
    private PerfilesRecursoService perfilesRecursoService;

    public ParametriaRecursosMatrizTiempoController() {
    }

    @GetMapping({ "/parametriaRecursosMatrizTiempo" })
    public List<ParametriaRecursosMatrizTiempo> getParametriaRecursosMatrizTiempo() {
        return this.parametriaRecursosMatrizTiempoService.getParametriaRecursos();
    }

    @GetMapping({ "/parametriaRecursosMatrizTiempo/search/{id}"})
    public ParametriaRecursosMatrizTiempo findByParametriaRecursosById(@PathVariable Integer id){
        ParametriaRecursosMatrizTiempo recurso = new ParametriaRecursosMatrizTiempo();
        recurso = this.parametriaRecursosMatrizTiempoService.findById(id);
        return recurso;
    }

    @GetMapping({ "/parametriaRecursosMatrizTiempo/EspecialidadRecurso" })
    public List<EspecialidadRecurso> getEspecialidadesRecursos(){
        return this.especialidadRecursoService.getEspecialidadRecursos();
    }
    
    @GetMapping({ "/parametriaRecursosMatrizTiempo/PerfilesRecurso/{id_especialidad}" })
    public List<PerfilesRecurso> getPerfilRecursos(@PathVariable Integer id_especialidad){
        List<PerfilesRecurso> getrecursos = new ArrayList<>();
        List<PerfilesRecurso> sendrecursos = new ArrayList<>();
        getrecursos = this.perfilesRecursoService.getPerfilesRecursos();
        PerfilesRecurso recurso = new PerfilesRecurso();
        System.out.println("estada"+getrecursos);
        for(int i=0; i< getrecursos.size() ; i++){
            recurso = getrecursos.get(i);
            if(recurso.getFk_especialidad() == id_especialidad){
            sendrecursos.add(recurso);
            }
        }
        return sendrecursos;
    }

    @GetMapping({ "/parametriaRecursosMatrizTiempo/PerfilesRecurso" })
    public List<PerfilesRecurso> getPerfilRecursos(){
        return this.perfilesRecursoService.getPerfilesRecursos();
    }

    @PutMapping({ "/parametriaRecursosMatrizTiempo/update/{id}" })
	public ResponseEntity<?> updateParametriaRecurso(@PathVariable Integer id, @RequestBody ParametriaRecursosMatrizTiempo parametriaRecursosNueva) {
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
            recurso.setEspecialidad(parametriaRecursosNueva.getEspecialidad());
            recurso.setPerfil(parametriaRecursosNueva.getPerfil());
            recurso.setDescripcion(parametriaRecursosNueva.getDescripcion());
            recurso.setTarifaHora(parametriaRecursosNueva.getTarifaHora());
            recurso.setTarifaMensual(parametriaRecursosNueva.getTarifaMensual());
			ParametriaRecursosMatrizTiempo updatedRecurso = this.parametriaRecursosMatrizTiempoService.saveParametriaRecursos(recurso);
			System.out.println(updatedRecurso);
            System.out.println(recurso);
            return ResponseEntity.ok(updatedRecurso);
	}

    @PostMapping({ "/parametriaRecursosMatrizTiempo" })
    public ResponseEntity<?> saveParametriaRecursosMatrizTiempo(
            @RequestBody ParametriaRecursosMatrizTiempo parametriaRecursosNueva) {

        this.parametriaRecursosMatrizTiempoService.saveParametriaRecursos(parametriaRecursosNueva);

        LogSistema log = new LogSistema();
        log.setAccion("UPDATE");
        log.setFechaHora(new Date());
        log.setTabla(ProjectStatusReport.class.toString());
        log.setIdAccion(parametriaRecursosNueva.getId());
        log.setDescripcion(parametriaRecursosNueva.toString());
        this.logService.saveLog(log);

        return ResponseEntity.ok(parametriaRecursosNueva);
    }

    @DeleteMapping({ "/parametriaRecursosMatrizTiempo/{id}" })
    public ResponseEntity<Map<String, Boolean>> deleteEstado(@PathVariable Integer id) {
        this.parametriaRecursosMatrizTiempoService.deleteParametriaRecursosById(id);
        Map<String, Boolean> response = new HashMap();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
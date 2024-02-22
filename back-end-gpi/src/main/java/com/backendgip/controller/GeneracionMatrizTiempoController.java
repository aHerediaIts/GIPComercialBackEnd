package com.backendgip.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.EstadoProyecto;
import com.backendgip.model.GeneracionMatrizTiempo;
import com.backendgip.model.Generarmatriztiemposasignacionrecursos;
import com.backendgip.model.LogSistema;
import com.backendgip.model.MatrizTiempo;
import com.backendgip.model.ParametriaGeneralMatrizTiempo;
import com.backendgip.repository.GeneracionmatriztiemposrecursosRepository;
import com.backendgip.repository.MatrizTiempoRepository;
import com.backendgip.model.VersionMatriz;
import com.backendgip.model.GeneracionMatrizTiempoRecursos;
import com.backendgip.repository.VersionMatrizRepository;
import com.backendgip.service.GeneracionMatrizTiempoService;
import com.backendgip.service.LogSistemaService;
import com.backendgip.service.MatrizTiempoService;
import com.backendgip.service.ParametriaGeneralMatrizTiempoService;

@RestController
@Transactional
@RequestMapping({ "/api" })
public class GeneracionMatrizTiempoController {
    @Autowired
    private VersionMatrizRepository versionMatrizRepository;
    @Autowired
    private MatrizTiempoService matrizTiempoService;
    @Autowired
    private GeneracionMatrizTiempoService generacionMatrizTiempoService;
    @Autowired
    private MatrizTiempoRepository matrizTiempoRepository;
    @Autowired
    private ParametriaGeneralMatrizTiempoService parametriaGeneralMatrizTiempoService;
    @Autowired
    private LogSistemaService logService;
    @Autowired
    private GeneracionmatriztiemposrecursosRepository generacionmatriztiemposTipoRecursosRepository;

    public GeneracionMatrizTiempoController() {
    }

    @PostMapping({ "/generacionMatrizTiempo/{nombreMatriz}" })
    public ResponseEntity<?> saveGeneracionTiempos(
            @RequestBody GeneracionMatrizTiempoRecursos generacionMatrizTiempoNueva,
            @PathVariable String nombreMatriz) {

        MatrizTiempo matrizTiempo = new MatrizTiempo();
        MatrizTiempo matrizTiempoNuevo = new MatrizTiempo();
        GeneracionMatrizTiempo matrizGeneralTiempos = new GeneracionMatrizTiempo();
        Generarmatriztiemposasignacionrecursos matrizGeneralTiemposRecursos = new Generarmatriztiemposasignacionrecursos();
        GeneracionMatrizTiempo matrizTiempoGeneralNuevo = new GeneracionMatrizTiempo();
        LocalDateTime fechaDate = LocalDateTime.now(ZoneId.of("America/Bogota"));
        matrizTiempo.setFechaCreacionMatriz(fechaDate);
        matrizTiempo.setNombreMatriz(nombreMatriz);
        matrizTiempoNuevo = this.matrizTiempoService.saveMatrizTiempo(matrizTiempo);

        for (int i = 0; i < generacionMatrizTiempoNueva.matrizTiempo.size(); i++) {
            matrizGeneralTiempos = generacionMatrizTiempoNueva.matrizTiempo.get(i);
            matrizGeneralTiempos.setMatrizTiempo(new MatrizTiempo(matrizTiempoNuevo.getId()));
            matrizTiempoGeneralNuevo = this.generacionMatrizTiempoService.saveGeneracionTiempos(matrizGeneralTiempos);

            for (int j = 0; j < generacionMatrizTiempoNueva.recursosAsignados.size(); j++) {
                matrizGeneralTiemposRecursos = generacionMatrizTiempoNueva.recursosAsignados.get(j);
                if (matrizGeneralTiempos.getVersion().getId() == matrizGeneralTiemposRecursos.getVersion().getId()
                        && matrizGeneralTiempos.getSprint() == matrizGeneralTiemposRecursos.getSprint()) {
                            this.parametriaGeneralMatrizTiempoService.saveRecursosAsignados(matrizGeneralTiemposRecursos);
                    matrizGeneralTiemposRecursos.setGeneracionMatrizTiempo(matrizTiempoGeneralNuevo);
                }
            }
        }

        return ResponseEntity.ok(generacionMatrizTiempoNueva);
    }

    @GetMapping({ "/generacionMatrizTiempo/getParametrosGeneralesByIdMatriz/{idMatriz}" })
    public ResponseEntity<?> getParametrosGeneralesByIdMatriz(@PathVariable Integer idMatriz) {

        MatrizTiempo matrizTiempo = (MatrizTiempo) this.matrizTiempoRepository.findById(idMatriz).orElseThrow(() -> {
            return new ResourceNotFoundException("Proyecto no encontrado con id: " + idMatriz);
        });
        return this.generacionMatrizTiempoService.findByMatrizTiempo(matrizTiempo).isEmpty()
                ? new ResponseEntity("No se encontraron resultado en la busqueda", HttpStatus.BAD_REQUEST)
                : new ResponseEntity(this.generacionMatrizTiempoService.findByMatrizTiempo(matrizTiempo),
                        HttpStatus.OK);
    }

    @GetMapping({ "/generacionMatrizTiempo/getParametrosRecursosByIdMatriz/{idMatriz}" })
    public ResponseEntity<?> getParametrosRecursosByIdMatriz(@PathVariable Integer idMatriz) {
        List<Generarmatriztiemposasignacionrecursos> recursosAsignados = new ArrayList();
        MatrizTiempo matrizTiempo = (MatrizTiempo) this.matrizTiempoRepository.findById(idMatriz).orElseThrow(() -> {
            return new ResourceNotFoundException("Proyecto no encontrado con id: " + idMatriz);
        });

        List<GeneracionMatrizTiempo> sprintsVersion = this.generacionMatrizTiempoService.findByMatrizTiempo(matrizTiempo);
        for (GeneracionMatrizTiempo sprint : sprintsVersion) {
            if (!this.generacionmatriztiemposTipoRecursosRepository.findByGeneracionMatrizTiempoId(sprint.getId()).isEmpty()) {
                for (Generarmatriztiemposasignacionrecursos item : this.generacionmatriztiemposTipoRecursosRepository.findByGeneracionMatrizTiempoId(sprint.getId())) {
                   recursosAsignados.add(item);
                } 
            }
        }
        return new ResponseEntity(recursosAsignados,HttpStatus.OK);
    }

    @GetMapping({ "/generacionMatrizTiempo/getVersiones" })
    public List<VersionMatriz> getVersiones() {
        return (List<VersionMatriz>) versionMatrizRepository.findAll();
    }

    @GetMapping({ "/generacionMatrizTiempo/getMatrices" })
    public List<MatrizTiempo> getMatrices() {
        return (List<MatrizTiempo>) matrizTiempoService.getMatrizTiempo();
    }

    @DeleteMapping({ "/generacionMatrizTiempo/{id}" })
    public ResponseEntity<Map<String, Boolean>> deleteEstado(@PathVariable Integer id) {
        // this.generacionMatrizTiempoService.deleteGeneracionTiemposById(id);
        Map<String, Boolean> response = new HashMap();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    public Generarmatriztiemposasignacionrecursos saveRecursoAsignado(
            Generarmatriztiemposasignacionrecursos recursosAsignados) {
        LogSistema log = new LogSistema();
        log.setAccion("CREATE");
        log.setTabla(Generarmatriztiemposasignacionrecursos.class.toString());
        log.setDescripcion(recursosAsignados.toString());
        this.logService.saveLog(log);
        return recursosAsignados;
    }

}
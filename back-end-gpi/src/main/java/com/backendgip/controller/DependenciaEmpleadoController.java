//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.controller;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.DependenciaEmpleado;
import com.backendgip.model.LogSistema;
import com.backendgip.repository.DependenciaEmpleadoRepository;
import com.backendgip.service.DependenciaEmpleadoService;
import com.backendgip.service.LogSistemaService;
import java.util.Date;
import java.util.HashMap;
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
@RequestMapping({ "/api/empleados" })
public class DependenciaEmpleadoController {
	@Autowired
	private DependenciaEmpleadoService dependenciaService;
	@Autowired
	private DependenciaEmpleadoRepository dependenciaReporsitory;
	@Autowired
	private LogSistemaService logService;

	public DependenciaEmpleadoController() {
	}

	@GetMapping({ "/dependencias" })
	public List<DependenciaEmpleado> getDependencia() {
		return this.dependenciaService.getDependencia();
	}

	@PostMapping({ "/dependencias" })
	public DependenciaEmpleado saveDependencia(@RequestBody DependenciaEmpleado dependencia) {
		DependenciaEmpleado createdDependencia = this.dependenciaService.saveDependencia(dependencia);
		LogSistema log = new LogSistema();
		log.setAccion("CREATE");
		log.setFechaHora(new Date());
		log.setTabla(DependenciaEmpleado.class.toString());
		log.setIdAccion(createdDependencia.getId());
		log.setDescripcion(createdDependencia.toString());
		this.logService.saveLog(log);
		return createdDependencia;
	}

	@PutMapping({ "/dependencias/{id}" })
	public ResponseEntity<DependenciaEmpleado> updateDependencia(@PathVariable Integer id,
			@RequestBody DependenciaEmpleado dependenciaDetails) {
		DependenciaEmpleado dependencia = (DependenciaEmpleado) this.dependenciaReporsitory.findById(id)
				.orElseThrow(() -> {
					return new ResourceNotFoundException("No se ha encontrado el recurso sollicitado" + id);
				});
		LogSistema log = new LogSistema();
		log.setAccion("UPDATE");
		log.setDescripcion(dependencia.toString());
		log.setFechaHora(new Date());
		log.setIdAccion(dependencia.getId());
		log.setTabla(dependencia.getClass().toString());
		this.logService.saveLog(log);
		dependencia.setDependencia(dependenciaDetails.getDependencia());
		DependenciaEmpleado updateDependencia = this.dependenciaService.saveDependencia(dependencia);
		return ResponseEntity.ok(updateDependencia);
	}

	@GetMapping({ "/dependencias/{id}" })
	public ResponseEntity<DependenciaEmpleado> getDependenciaById(@PathVariable Integer id) {
		DependenciaEmpleado dependencia = (DependenciaEmpleado) this.dependenciaReporsitory.findById(id)
				.orElseThrow(() -> {
					return new ResourceNotFoundException("No se ha encontrado el recurso solicitado" + id);
				});
		return ResponseEntity.ok(dependencia);
	}

	@DeleteMapping({ "/dependencias/{id}" })
	public ResponseEntity<Map<String, Boolean>> deleteDependencia(@PathVariable Integer id) {
		DependenciaEmpleado dependencia = (DependenciaEmpleado) this.dependenciaReporsitory.findById(id)
				.orElseThrow(() -> {
					return new ResourceNotFoundException("No se ha encontrado el recuso solicitado" + id);
				});
		LogSistema log = new LogSistema();
		log.setAccion("DELETE");
		log.setFechaHora(new Date());
		log.setIdAccion(dependencia.getId());
		log.setDescripcion(dependencia.toString());
		log.setTabla(DependenciaEmpleado.class.toString());
		this.logService.saveLog(log);
		this.dependenciaReporsitory.deleteById(dependencia.getId());
		Map<String, Boolean> response = new HashMap();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}

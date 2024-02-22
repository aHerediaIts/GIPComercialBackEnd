//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.controller;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.Especialidad;
import com.backendgip.model.LogSistema;
import com.backendgip.repository.EspecialidadRepository;
import com.backendgip.service.EspecialidadService;
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
public class EspecialidadController {
	@Autowired
	private EspecialidadService especialidadService;
	@Autowired
	private EspecialidadRepository especialidadRepository;
	@Autowired
	private LogSistemaService logService;

	public EspecialidadController() {
	}

	@GetMapping({ "/especialidades" })
	public List<Especialidad> getEspecialidad() {
		return this.especialidadService.getEspecialidad();
	}

	@PostMapping({ "/especialidades" })
	public Especialidad saveEspecialidad(@RequestBody Especialidad especialidad) {
		Especialidad createdEspecialidad = this.especialidadService.saveEspecialidad(especialidad);
		LogSistema log = new LogSistema();
		log.setAccion("CREATE");
		log.setFechaHora(new Date());
		log.setTabla(Especialidad.class.toString());
		log.setIdAccion(createdEspecialidad.getId());
		log.setDescripcion(createdEspecialidad.toString());
		this.logService.saveLog(log);
		return createdEspecialidad;
	}

	@PutMapping({ "/especialidades/{id}" })
	public ResponseEntity<Especialidad> updateEspecialidad(@PathVariable Integer id,
			@RequestBody Especialidad especialidadDetails) {
		Especialidad especialidad = (Especialidad) this.especialidadRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado el recurso solicitado" + id);
		});
		LogSistema log = new LogSistema();
		log.setAccion("UPDATE");
		log.setDescripcion(especialidad.toString());
		log.setFechaHora(new Date());
		log.setIdAccion(especialidad.getId());
		log.setTabla(especialidad.getClass().toString());
		this.logService.saveLog(log);
		especialidad.setEspecialidad(especialidadDetails.getEspecialidad());
		Especialidad updateEspecialidad = this.especialidadService.saveEspecialidad(especialidad);
		return ResponseEntity.ok(updateEspecialidad);
	}

	@GetMapping({ "/especialidades/{id}" })
	public ResponseEntity<Especialidad> getEspecialidadById(@PathVariable Integer id) {
		Especialidad especialidad = (Especialidad) this.especialidadRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado el recurso solicitado" + id);
		});
		return ResponseEntity.ok(especialidad);
	}

	@DeleteMapping({ "/especialidades/{id}" })
	public ResponseEntity<Map<String, Boolean>> deleteEspecialidad(@PathVariable Integer id) {
		Especialidad especialidad = (Especialidad) this.especialidadRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado el recurso solicitado" + id);
		});
		LogSistema log = new LogSistema();
		log.setAccion("DELETE");
		log.setFechaHora(new Date());
		log.setIdAccion(especialidad.getId());
		log.setDescripcion(especialidad.toString());
		log.setTabla(Especialidad.class.toString());
		this.logService.saveLog(log);
		this.especialidadRepository.deleteById(especialidad.getId());
		Map<String, Boolean> response = new HashMap();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}

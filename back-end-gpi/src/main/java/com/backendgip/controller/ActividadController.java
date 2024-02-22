//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.controller;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.Actividad;
import com.backendgip.model.FaseProyecto;
import com.backendgip.model.LogSistema;
import com.backendgip.model.Proyecto;
import com.backendgip.repository.ActividadAsignadaRepository;
import com.backendgip.repository.ActividadRepository;
import com.backendgip.repository.FaseProyectoRepository;
import com.backendgip.repository.ProyectoRepository;
import com.backendgip.service.ActividadService;
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
@RequestMapping({ "/api/proyectos" })
public class ActividadController {
	@Autowired
	private ActividadRepository actividadRepository;
	@Autowired
	private ActividadService actividadService;
	@Autowired
	private ProyectoRepository proyectoRepository;
	@Autowired
	private FaseProyectoRepository faseRepository;
	@Autowired
	private LogSistemaService logService;
	@Autowired
	private ActividadAsignadaRepository actividadAsigRepository;

	public ActividadController() {
	}

	@GetMapping({ "/actividades" })
	public List<Actividad> getActividades() {
		return this.actividadService.getActividades();
	}

	@PostMapping({ "/actividades" })
	public ResponseEntity<?> saveActividad(@RequestBody Actividad actividad) {
		if (this.actividadRepository.existsByActividadAndFase(actividad.getActividad(), actividad.getFase())) {
			return ResponseEntity.badRequest().body("Actividad ya existe dentro de esta fase");
		} else {
			Actividad createdActividad = this.actividadService.saveActividad(actividad);
			LogSistema log = new LogSistema();
			log.setAccion("CREATE");
			log.setFechaHora(new Date());
			log.setTabla(Actividad.class.toString());
			log.setIdAccion(createdActividad.getId());
			log.setDescripcion(createdActividad.toString());
			this.logService.saveLog(log);
			return ResponseEntity.ok(createdActividad);
		}
	}

	@GetMapping({ "/actividades/{id}" })
	public ResponseEntity<Actividad> getActividadById(@PathVariable Integer id) {
		Actividad actividad = (Actividad) this.actividadRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("Actividad no encontrada con id:" + id);
		});
		return ResponseEntity.ok(actividad);
	}

	@PutMapping({ "/actividades/{id}" })
	public ResponseEntity<?> updateActividad(@PathVariable Integer id, @RequestBody Actividad actividadDetails) {
		Actividad actividad = (Actividad) this.actividadRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("Actividad no encontrada con id:" + id);
		});
		if (this.actividadRepository.existsByActividad(actividadDetails.getActividad())
				&& actividad.getId() != actividadDetails.getId()) {
			return ResponseEntity.badRequest().body("Actividad ya existe.");
		} else {
			LogSistema log = new LogSistema();
			log.setAccion("UPDATE");
			log.setDescripcion(actividad.toString());
			log.setFechaHora(new Date());
			log.setIdAccion(actividad.getId());
			log.setTabla(actividad.getClass().toString());
			this.logService.saveLog(log);
			actividad.setActividad(actividadDetails.getActividad());
			actividad.setBase(actividadDetails.getBase());
			actividad.setFase(actividadDetails.getFase());
			actividad.setProyecto(actividadDetails.getProyecto());
			Actividad updatedActividad = this.actividadService.saveActividad(actividad);
			return ResponseEntity.ok(updatedActividad);
		}
	}

	@DeleteMapping({ "/actividades/{id}" })
	public ResponseEntity<?> deleteActividad(@PathVariable Integer id) {
		Actividad actividad = (Actividad) this.actividadRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("Actividad no encontrada con id:" + id);
		});
		if (this.actividadAsigRepository.existsByActividad(actividad)) {
			return ResponseEntity.badRequest().body("Actividad utilizada en planeación.");
		} else {
			LogSistema log = new LogSistema();
			log.setAccion("DELETE");
			log.setFechaHora(new Date());
			log.setIdAccion(actividad.getId());
			log.setDescripcion(actividad.toString());
			log.setTabla(Actividad.class.toString());
			this.logService.saveLog(log);
			this.actividadService.deleteActividad(actividad);
			Map<String, Boolean> response = new HashMap();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
		}
	}

	@GetMapping({ "/actividades/proyecto/{idProyecto}/{idFase}" })
	public List<Actividad> findByProyectoAndFaseAndBase(@PathVariable Integer idProyecto,
			@PathVariable Integer idFase) {
		Proyecto proyecto = (Proyecto) this.proyectoRepository.findById(idProyecto).orElseThrow(() -> {
			return new ResourceNotFoundException("Proyecto no encontrado con id:" + idProyecto);
		});
		FaseProyecto fase = (FaseProyecto) this.faseRepository.findById(idFase).orElseThrow(() -> {
			return new ResourceNotFoundException("Fase no encontrada con id:" + idFase);
		});
		return this.actividadService.findForPlaneacion(proyecto, fase);
	}

	@GetMapping({ "/actividades/find-by-fase/{idFase}" })
	public ResponseEntity<?> findByFase(@PathVariable Integer idFase) {
		FaseProyecto fase = (FaseProyecto) this.faseRepository.findById(idFase).orElseThrow(() -> {
			return new ResourceNotFoundException("Fase no encontrada con id:" + idFase);
		});
		return ResponseEntity.ok(this.actividadService.findByFase(fase));
	}
}

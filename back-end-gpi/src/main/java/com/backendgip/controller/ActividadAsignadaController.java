//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.controller;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.Actividad;
import com.backendgip.model.ActividadAsignada;
import com.backendgip.model.EstadoActividadAsig;
import com.backendgip.model.FaseProyecto;
import com.backendgip.model.LogSistema;
import com.backendgip.model.Proyecto;
import com.backendgip.repository.ActividadAsignadaRepository;
import com.backendgip.repository.ActividadRepository;
import com.backendgip.repository.EstadoActividadAsigRepository;
import com.backendgip.repository.FaseProyectoRepository;
import com.backendgip.repository.ProyectoRepository;
import com.backendgip.repository.RecursoActividadRepository;
import com.backendgip.service.ActividadAsignadaService;
import com.backendgip.service.EmpleadoService;
import com.backendgip.service.LogSistemaService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
@RequestMapping({ "/api/proyectos/planeacion" })
public class ActividadAsignadaController {
	@Autowired
	private ActividadAsignadaRepository actividadRepository;
	@Autowired
	private ActividadAsignadaService actividadService;
	@Autowired
	private EstadoActividadAsigRepository estadoRepository;
	@Autowired
	private ProyectoRepository proyectoRepository;
	@Autowired
	private FaseProyectoRepository faseRepository;
	@Autowired
	private LogSistemaService logService;
	@Autowired
	private RecursoActividadRepository recursoActRepository;
	@Autowired
	private ActividadRepository actRepository;
	@Autowired
	private EmpleadoService empleadoService;

	public ActividadAsignadaController() {
	}

	@GetMapping({ "/actividades-asig" })
	public List<ActividadAsignada> getActividades() {
		return this.actividadService.getActividades();
	}

	@PostMapping({ "/actividades-asig" })
	public ResponseEntity<?> saveActividad(@RequestBody ActividadAsignada actividad) {
		EstadoActividadAsig estado = (EstadoActividadAsig) this.estadoRepository.findById(1).orElseThrow(() -> {
			return new ResourceNotFoundException("Estado no encontrado con id 1");
		});
		actividad.setEstado(estado);
		if (actividad.getProyecto().getEtapa().getEtapa().equalsIgnoreCase("CRN")) {
			if (this.validCreateActividadAsig(actividad) != null) {
				return this.validCreateActividadAsig(actividad);
			}
		} else if (actividad.getProyecto().getEtapa().getEtapa().equalsIgnoreCase("PRP")
				&& this.validCreateActividadAsigPRP(actividad) != null) {
			return this.validCreateActividadAsigPRP(actividad);
		}

		Proyecto proyecto = actividad.getProyecto();
		proyecto.setFechaFin(this.actividadService.getFechaFinMax(proyecto, actividad));
		this.proyectoRepository.save(proyecto);
		ActividadAsignada createdActividad = this.actividadService.saveActividad(actividad);
		LogSistema log = new LogSistema();
		log.setAccion("CREATE");
		log.setFechaHora(new Date(Calendar.getInstance().getTime().getTime()));
		log.setTabla(ActividadAsignada.class.toString());
		log.setIdAccion(createdActividad.getId());
		log.setDescripcion(createdActividad.toString());
		this.logService.saveLog(log);
		return ResponseEntity.ok(createdActividad);
	}

	@GetMapping({ "/actividades-asig/{id}" })
	public ResponseEntity<ActividadAsignada> getActividadById(@PathVariable Integer id) {
		ActividadAsignada actividad = (ActividadAsignada) this.actividadRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("Actividad asignada no encontrada con id:" + id);
		});
		return ResponseEntity.ok(actividad);
	}

	@PutMapping({ "/actividades-asig/{id}" })
	public ResponseEntity<?> updateActividad(@PathVariable Integer id,
			@RequestBody ActividadAsignada actividadDetails) {
		ActividadAsignada actividad = (ActividadAsignada) this.actividadRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("Actividad asignada no encontrada con id:" + id);
		});
		if (this.validCreateActividadAsig(actividadDetails) != null) {
			return this.validCreateActividadAsig(actividadDetails);
		} else {
			this.deletePlaneacionForUpdateActividadAsig(actividad, actividadDetails);
			LogSistema log = new LogSistema();
			log.setAccion("UPDATE");
			log.setDescripcion(actividad.toString());
			log.setFechaHora(new Date());
			log.setIdAccion(actividad.getId());
			log.setTabla(actividad.getClass().toString());
			this.logService.saveLog(log);
			actividad.setActividad(actividadDetails.getActividad());
			actividad.setEstado(actividadDetails.getEstado());
			actividad.setFechaInicio(actividadDetails.getFechaInicio());
			actividad.setFechaFin(actividadDetails.getFechaFin());
			ActividadAsignada updatedActividad = this.actividadService.saveActividad(actividad);
			return ResponseEntity.ok(updatedActividad);
		}
	}

	@DeleteMapping({ "/actividades-asig/{id}" })
	public ResponseEntity<?> deleteActividad(@PathVariable Integer id) {
		ActividadAsignada actividad = (ActividadAsignada) this.actividadRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("Actividad asignada no encontrada con id:" + id);
		});
		if (this.recursoActRepository.existsByActividad(actividad)) {
			return ResponseEntity.badRequest().body("Actividad no se puede eliminar, Tiene recursos asignados.");
		} else {
			LogSistema log = new LogSistema();
			log.setAccion("DELETE");
			log.setFechaHora(new Date(Calendar.getInstance().getTime().getTime()));
			log.setIdAccion(actividad.getId());
			log.setDescripcion(actividad.toString());
			log.setTabla(ActividadAsignada.class.toString());
			this.logService.saveLog(log);
			this.actividadService.deleteActividad(actividad);
			Map<String, Boolean> response = new HashMap();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
		}
	}

	@GetMapping({ "/actividades-asig/proyecto/{id}" })
	public List<ActividadAsignada> findByProyecto(@PathVariable Integer id) {
		Proyecto proyecto = (Proyecto) this.proyectoRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("Proyecto no encontrado con id:" + id);
		});
		return this.actividadService.findByProyecto(proyecto);
	}

	@GetMapping({ "/actividades-asig/proyecto-int/{id}" })
	public List<ActividadAsignada> findByProyectoInt(@PathVariable Integer id) {
		Proyecto proyecto = (Proyecto) this.proyectoRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("Proyecto no encontrado con id:" + id);
		});
		List<Actividad> actividades = this.actRepository.findByProyecto(proyecto);
		List<ActividadAsignada> actividadesAsig = new ArrayList();
		Iterator var5 = actividades.iterator();

		while (var5.hasNext()) {
			Actividad a = (Actividad) var5.next();
			ActividadAsignada act = new ActividadAsignada();
			act.setActividad(a);
			act.setEstado((EstadoActividadAsig) this.estadoRepository.findById(1).get());
			act.setFechaInicio((LocalDate) null);
			act.setFechaFin((LocalDate) null);
			act.setProyecto(proyecto);
			actividadesAsig.add(act);
		}

		return actividadesAsig;
	}

	@GetMapping({ "/actividades-asig/fase/{idProyecto}/{idFase}" })
	public List<ActividadAsignada> findByFase(@PathVariable Integer idProyecto, @PathVariable Integer idFase) {
		Proyecto proyecto = (Proyecto) this.proyectoRepository.findById(idProyecto).orElseThrow(() -> {
			return new ResourceNotFoundException("Proyecto no encontrado con id:" + idProyecto);
		});
		FaseProyecto fase = (FaseProyecto) this.faseRepository.findById(idFase).orElseThrow(() -> {
			return new ResourceNotFoundException("Fase no encontrada con id:" + idFase);
		});
		return this.actividadService.findByFase(fase, proyecto);
	}

	@GetMapping({ "/actividades-asig/find-by-actividad/{idActividad}" })
	public ResponseEntity<?> findByActividad(@PathVariable Integer idActividad) {
		Actividad actividad = (Actividad) this.actRepository.findById(idActividad).orElseThrow(() -> {
			return new ResourceNotFoundException("No se encontro actividad con id: " + idActividad);
		});
		return ResponseEntity.ok(this.actividadService.findByActividad(actividad));
	}

	public void deletePlaneacionForUpdateActividadAsig(ActividadAsignada oldA, ActividadAsignada newA) {
		List<LocalDate> fechas = this.empleadoService.getFechasBetween(oldA.getFechaInicio(), oldA.getFechaFin());
		List<LocalDate> fechas2 = this.empleadoService.getFechasBetween(newA.getFechaInicio(), newA.getFechaFin());
		Iterator var5 = fechas.iterator();

		while (var5.hasNext()) {
			LocalDate f = (LocalDate) var5.next();
			if (!fechas2.contains(f)) {
				this.recursoActRepository.deleteByActividadAndFecha(oldA, f).size();
			}
		}

	}

	public ResponseEntity<?> validCreateActividadAsig(ActividadAsignada actividad) {
		if (actividad.getFechaInicio().isBefore(actividad.getProyecto().getFechaInicio())) {
			return ResponseEntity.badRequest()
					.body("La Fecha Inicio de la actividad debe ser mayor o igual a la Fecha Inicio de proyecto.");
		} else if (actividad.getFechaFin().isBefore(actividad.getProyecto().getFechaInicio())) {
			return ResponseEntity.badRequest()
					.body("La Fecha Fin de la actividad debe ser mayor a la Fecha Inicio del proyecto.");
		} else {
			return actividad.getFechaFin().isBefore(actividad.getFechaInicio()) ? ResponseEntity.badRequest()
					.body("La Fecha Fin de de la actividad debe ser mayor a la Fecha Inicio de la actividad.") : null;
		}
	}

	public ResponseEntity<?> validCreateActividadAsigPRP(ActividadAsignada actividad) {
		return actividad.getFechaFin().isBefore(actividad.getFechaInicio()) ? ResponseEntity.badRequest()
				.body("La Fecha Fin de de la actividad debe ser mayor a la Fecha Inicio de la actividad.") : null;
	}
}

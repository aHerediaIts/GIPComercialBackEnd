//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.controller;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.*;
import com.backendgip.repository.ActividadAsignadaRepository;
import com.backendgip.repository.EmpleadoRepository;
import com.backendgip.repository.RecursoActividadRepository;
import com.backendgip.service.LogSistemaService;
import com.backendgip.service.MailService;
import com.backendgip.service.ProyectoService;
import com.backendgip.service.RecursoActividadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@Transactional
@RequestMapping({ "/api/proyectos/recursos" })
public class RecursoActividadController {
	@Autowired
	private RecursoActividadService recursoActService;
	@Autowired
	private RecursoActividadRepository recursoActRepository;
	@Autowired
	private EmpleadoRepository empleadoRepository;
	@Autowired
	private ActividadAsignadaRepository actividadAsigRepository;
	@Autowired
	private LogSistemaService logService;
	@Autowired
	private ProyectoService proyectoService;
	@Autowired
	private MailService mailService;

	public RecursoActividadController() {
	}

	@GetMapping({ "/actividades" })
	public List<RecursoActividad> getRecursoActividad() {
		return this.recursoActService.getRecursoActividad();
	}

	@PostMapping({ "/actividades/save-list" })
	public ResponseEntity<?> createRecursoActividadList(@RequestBody List<RecursoActividad> recursos) {
		List<RecursoActividad> createdRecursos = new ArrayList<>();
		String fechas = "";
		Iterator var4 = recursos.iterator();

		while (var4.hasNext()) {
			RecursoActividad r = (RecursoActividad) var4.next();
			boolean ifExistsByDate = this.recursoActService
						.existsByActividadAndFecha(r.getActividad(), r.getFecha());

			if(!ifExistsByDate) {
				RecursoActividad createdRecursoActividad = this.recursoActService.saveRecursoActividad(r);
				createdRecursos.add(createdRecursoActividad);
				LogSistema log = new LogSistema();
				log.setAccion("CREATE");
				log.setFechaHora(new Date());
				log.setTabla(RecursoActividad.class.toString());
				log.setIdAccion(createdRecursoActividad.getId());
				log.setDescripcion(createdRecursoActividad.toString());
				fechas = fechas + r.getFecha() + ", ";
				this.logService.saveLog(log);
			}
		}

		Proyecto proyecto = null;
		if (!recursos.isEmpty()) {
			proyecto = ((RecursoActividad) recursos.get(0)).getActividad().getProyecto();

			String descriptionProyecto = proyecto.getDescripcion();
			String nombreProyecto = proyecto.getNombre();
			String fechasProyecto = fechas;
			String emailEmpleado = ((RecursoActividad) recursos.get(0)).getEmpleado().getEmail();
			String nombreActividad = ((RecursoActividad) recursos.get(0)).getActividad().getActividad().getActividad();
			this.mailService.sendSimpleMail(emailEmpleado,
					"ASIGNACIÓN ACTIVIDAD",
					"Buen Dia.<br><br>Usted ha sido asignado a la actividad "
							+ nombreActividad
							+ "  en la/s fecha/s " + fechasProyecto + " en el proyecto " + nombreProyecto + "-"
							+ descriptionProyecto
							+ ".<br><br><br>Gerencia De Proyectos Y Servicios.<br>Its Solution SAS.");
		}
		return ResponseEntity.ok(createdRecursos);
	}

	@PostMapping({ "/actividades" })
	public RecursoActividad saveRecursoActividad(@RequestBody RecursoActividad recursoActividad) {
		RecursoActividad createdRecursoActividad = this.recursoActService.saveRecursoActividad(recursoActividad);
		Proyecto proyecto = recursoActividad.getActividad().getProyecto();
		proyecto.setHorasPlaneadas(this.getHorasPlaneadas(proyecto));
		this.proyectoService.saveProyecto(proyecto);
		LogSistema log = new LogSistema();
		log.setAccion("CREATE");
		log.setFechaHora(new Date());
		log.setTabla(RecursoActividad.class.toString());
		log.setIdAccion(createdRecursoActividad.getId());
		log.setDescripcion(createdRecursoActividad.toString());
		this.logService.saveLog(log);
		return createdRecursoActividad;
	}

	@PutMapping({ "/actividades/{id}" })
	public ResponseEntity<RecursoActividad> updateRecursoActividad(@PathVariable Integer id,
			@RequestBody RecursoActividad recursoActividadDetails) {
		RecursoActividad recurso = (RecursoActividad) this.recursoActRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException(" No existe el Recurso Actividad con el id: " + id);
		});
		LogSistema log = new LogSistema();
		log.setAccion("UPDATE");
		log.setDescripcion(recurso.toString());
		log.setFechaHora(new Date());
		log.setIdAccion(recurso.getId());
		log.setTabla(recurso.getClass().toString());
		this.logService.saveLog(log);
		recurso.setFecha(recursoActividadDetails.getFecha());
		recurso.setEmpleado(recursoActividadDetails.getEmpleado());
		recurso.setActividad(recursoActividadDetails.getActividad());
		RecursoActividad updatedRecursoActividad = this.recursoActService.saveRecursoActividad(recurso);
		return ResponseEntity.ok(updatedRecursoActividad);
	}

	@GetMapping({ "/actividades/{id}" })
	public ResponseEntity<RecursoActividad> getRecursoActividadById(@PathVariable Integer id) {
		RecursoActividad recurso = (RecursoActividad) this.recursoActRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException(" No existe el Recurso Actividad con el id: " + id);
		});
		return ResponseEntity.ok(recurso);
	}

	@DeleteMapping({ "/actividades/{id}" })
	public ResponseEntity<Map<String, Boolean>> deleteRecursoActividad(@PathVariable Integer id) {
		RecursoActividad recurso = (RecursoActividad) this.recursoActRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException(" No existe el Recurso Actividad con el id: " + id);
		});
		LogSistema log = new LogSistema();
		log.setAccion("DELETE");
		log.setFechaHora(new Date());
		log.setIdAccion(recurso.getId());
		log.setDescripcion(recurso.toString());
		log.setTabla(RecursoActividad.class.toString());
		this.logService.saveLog(log);
		this.recursoActService.deleteRecursoActividad(recurso);
		Map<String, Boolean> response = new HashMap();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

	@GetMapping({ "/actividades/planeacion/{idEmpleado}/{idActividad}" })
	public List<RecursoActividad> getNewListByFecha(@PathVariable Integer idEmpleado,
			@PathVariable Integer idActividad) {
		new ArrayList();
		Empleado empleado = (Empleado) this.empleadoRepository.findById(idEmpleado).orElseThrow(() -> {
			return new ResourceNotFoundException("Empleado no encontrado con id:" + idEmpleado);
		});
		ActividadAsignada actividad = (ActividadAsignada) this.actividadAsigRepository.findById(idActividad)
				.orElseThrow(() -> {
					return new ResourceNotFoundException("Actividad no encontrada con el id:" + idActividad);
				});
		List<RecursoActividad> fpush = this.recursoActService.findByEmpleadoAndActividadNewDates(empleado, actividad);
		Iterator var6 = fpush.iterator();

		while (var6.hasNext()) {
			RecursoActividad ra = (RecursoActividad) var6.next();
			// System.out.println(ra.getFecha());
		}

		return fpush;
	}

	@GetMapping({ "/actividades/planeacion/recursos/{idActividad}" })
	public List<RecursoActividad> findByActividad(@PathVariable Integer idActividad) {
		ActividadAsignada actividad = (ActividadAsignada) this.actividadAsigRepository.findById(idActividad)
				.orElseThrow(() -> {
					return new ResourceNotFoundException("Actividad no encontrada con id:" + idActividad);
				});
		return this.recursoActService.findByActividad(actividad);
	}

	@GetMapping({ "/actividades/planeacion/recursos-asignados/{idEmpleado}" })
	public List<RecursoActividad> findByEmpleado(@PathVariable Integer idEmpleado) {
		Empleado empleado = (Empleado) this.empleadoRepository.findById(idEmpleado).orElseThrow(() -> {
			return new ResourceNotFoundException("Empleado no encontrado con id:" + idEmpleado);
		});
		return this.recursoActService.findByEmpleado(empleado);
	}

	@GetMapping({ "/actividades/planeacion/recursos-planeados/{idActividad}" })
	public List<Empleado> findEmpleadosAsignados(@PathVariable Integer idActividad) {
		List<RecursoActividad> allRecursos = this.findByActividad(idActividad);
		List<Empleado> recursos = new ArrayList();
		Iterator var4 = allRecursos.iterator();

		while (var4.hasNext()) {
			RecursoActividad e = (RecursoActividad) var4.next();
			if (!recursos.contains(e.getEmpleado())) {
				recursos.add(e.getEmpleado());
			}
		}

		return recursos;
	}

	@GetMapping({ "/actividades/planeacion/recursos-asignados/{idEmpleado}/{idActividad}" })
	public List<RecursoActividad> findByEmpleadoAndActividad(@PathVariable Integer idEmpleado,
			@PathVariable Integer idActividad) {
		Empleado empleado = (Empleado) this.empleadoRepository.findById(idEmpleado).orElseThrow(() -> {
			return new ResourceNotFoundException("Empleado no encontrado con id:" + idEmpleado);
		});
		ActividadAsignada actividad = (ActividadAsignada) this.actividadAsigRepository.findById(idActividad)
				.orElseThrow(() -> {
					return new ResourceNotFoundException("Actividad no encontrada con el id:" + idActividad);
				});
		return this.recursoActService.findByEmpleadoAndActividad(empleado, actividad);
	}

	@DeleteMapping({ "/actividades/planeacion/recursos-asignados/{idEmpleado}/{idActividad}" })
	public ResponseEntity<Map<String, Boolean>> deleteByEmpleadoAndActividad(@PathVariable Integer idEmpleado,
			@PathVariable Integer idActividad) {
		Empleado empleado = (Empleado) this.empleadoRepository.findById(idEmpleado).orElseThrow(() -> {
			return new ResourceNotFoundException("Empleado no encontrado con id:" + idEmpleado);
		});
		ActividadAsignada actividad = (ActividadAsignada) this.actividadAsigRepository.findById(idActividad)
				.orElseThrow(() -> {
					return new ResourceNotFoundException("Actividad no encontrada con el id:" + idActividad);
				});
		List<RecursoActividad> listToDelete = this.recursoActService.findByEmpleadoAndActividad(empleado, actividad);
		Iterator var6 = listToDelete.iterator();

		while (var6.hasNext()) {
			RecursoActividad e = (RecursoActividad) var6.next();
			this.recursoActService.deleteRecursoActividad(e);
		}

		Map<String, Boolean> response = new HashMap();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

	@GetMapping({ "/actividades/planeacion/existe-actividad-asignada/{idActividad}" })
	public Boolean existsByActividad(@PathVariable Integer idActividad) {
		ActividadAsignada actividad = (ActividadAsignada) this.actividadAsigRepository.findById(idActividad)
				.orElseThrow(() -> {
					return new ResourceNotFoundException("Actividad no encontrada con el id:" + idActividad);
				});
		return this.recursoActService.existsByActividad(actividad);
	}

	public Integer getHorasPlaneadas(Proyecto proyecto) {
		List<ActividadAsignada> actividades = this.actividadAsigRepository.findByProyecto(proyecto);
		List<RecursoActividad> recursos = new ArrayList();
		Integer totalHoras = 0;
		Iterator var5 = actividades.iterator();

		while (var5.hasNext()) {
			ActividadAsignada a = (ActividadAsignada) var5.next();
			recursos.addAll(this.recursoActService.findByActividad(a));
		}

		totalHoras = recursos.size() * 8;
		return totalHoras;
	}
}

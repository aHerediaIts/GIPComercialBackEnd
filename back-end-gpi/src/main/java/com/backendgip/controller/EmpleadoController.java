//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.controller;

import com.backendgip.exception.ResourceAlreadyExistsException;
import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.*;
import com.backendgip.repository.ActividadAsignadaRepository;
import com.backendgip.repository.CargoRepository;
import com.backendgip.repository.EmpleadoEspecialidadRepository;
import com.backendgip.repository.EmpleadoRepository;
import com.backendgip.repository.EspecialidadRepository;
import com.backendgip.repository.NovedadRepository;
import com.backendgip.repository.RecursoActividadRepository;
import com.backendgip.repository.ReporteTiempoRepository;
import com.backendgip.service.CargoService;
import com.backendgip.service.DependenciaEmpleadoService;
import com.backendgip.service.EmpleadoService;
import com.backendgip.service.EstadoEmpleadoService;
import com.backendgip.service.LogSistemaService;
import com.backendgip.service.RecursoActividadService;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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
public class EmpleadoController {
	@Autowired
	private EmpleadoService empleadoService;
	@Autowired
	private EmpleadoRepository empleadoRepository;
	@Autowired
	private DependenciaEmpleadoService dependenciaService;
	@Autowired
	private CargoService cargoService;
	@Autowired
	private CargoRepository cargoRepository;
	@Autowired
	private EstadoEmpleadoService estadoService;
	@Autowired
	private ActividadAsignadaRepository actividadRepository;
	@Autowired
	private RecursoActividadService recursoActService;
	@Autowired
	private RecursoActividadRepository recursoActRepository;
	@Autowired
	private LogSistemaService logService;
	@Autowired
	private NovedadRepository novedadRepository;
	@Autowired
	private ReporteTiempoRepository reporteRepository;
	@Autowired
	private EspecialidadRepository especialidadRepository;
	@Autowired
	private EmpleadoEspecialidadRepository empEspRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public EmpleadoController() {
	}

	@GetMapping({ "/empleados" })
	public List<Empleado> getEmpleado() {
		return this.empleadoService.getEmpleado();
	}

	@PostMapping({ "/empleados" })
	public ResponseEntity<?> saveEmpleado(@RequestBody Empleado empleado) {
		if (this.empleadoRepository.existsByEmail(empleado.getEmail())) {
			return ResponseEntity.badRequest().body(new ResourceAlreadyExistsException("Correo existente"));
		} else {
			EstadoEmpleado estado = this.estadoService.getEstadoById(1);
			empleado.setEstado(estado);
			empleado.setNombreUsuario(empleado.getEmail());
			empleado.setPassword(this.passwordEncoder.encode(empleado.getEmail()));
			Empleado createdEmpleado = this.empleadoService.saveEmpleado(empleado);
			LogSistema log = new LogSistema();
			log.setAccion("CREATE");
			log.setFechaHora(new Date(Calendar.getInstance().getTime().getTime()));
			log.setTabla(Empleado.class.toString());
			log.setIdAccion(createdEmpleado.getId());
			log.setDescripcion(createdEmpleado.toString());
			this.logService.saveLog(log);
			return ResponseEntity.ok(createdEmpleado);
		}
	}

	@PutMapping({ "/empleados/{id}" })
	public ResponseEntity<?> updateEmpleado(@PathVariable Integer id, @RequestBody Empleado empleadoDetails) {
		Empleado empleado = (Empleado) this.empleadoRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado el recurso solicitado" + id);
		});
		if (this.empleadoRepository.existsByEmail(empleadoDetails.getEmail())
				&& empleado.getId() != empleadoDetails.getId()) {
			return ResponseEntity.badRequest().body(new ResourceAlreadyExistsException("Correo existente"));
		} else {
			LogSistema log = new LogSistema();
			log.setAccion("UPDATE");
			log.setDescripcion(empleado.toString());
			log.setFechaHora(new Date(Calendar.getInstance().getTime().getTime()));
			log.setIdAccion(empleado.getId());
			log.setTabla(empleado.getClass().toString());
			this.logService.saveLog(log);
			empleado.setNumeroDoc(empleadoDetails.getNumeroDoc());
			empleado.setNombre(empleadoDetails.getNombre());
			empleado.setEmail(empleadoDetails.getEmail());
			DependenciaEmpleado dependencia = this.dependenciaService
					.getDependenciaById(empleadoDetails.getDependencia().getId());
			empleado.setDependencia(dependencia);
			Cargo cargo = this.cargoService.getCargoById(empleadoDetails.getCargo().getId());
			empleado.setCargo(cargo);
			EstadoEmpleado estado = this.estadoService.getEstadoById(empleadoDetails.getEstado().getId());
			empleado.setEstado(estado);
			empleado.setNombreUsuario(empleado.getEmail());
			empleado.setPassword(this.passwordEncoder.encode(empleado.getEmail()));
			Empleado updateEmpleado = this.empleadoService.saveEmpleado(empleado);
			return ResponseEntity.ok(updateEmpleado);
		}
	}

	@GetMapping({ "/empleados/{id}" })
	public ResponseEntity<Empleado> getEmpleadoById(@PathVariable Integer id) {
		Empleado empleado = (Empleado) this.empleadoRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("ID " + id + " NO ENCONTRADO");
		});
		return ResponseEntity.ok(empleado);
	}

	@DeleteMapping({ "/empleados/{id}" })
	public ResponseEntity<?> deleteEmpleado(@PathVariable Integer id) {
		Empleado empleado = (Empleado) this.empleadoRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado el recurso solicitado" + id);
		});
		if (this.novedadRepository.existsByEmpleado(empleado)) {
			return ResponseEntity.badRequest().body("No se puede eliminar el empleado, Tiene relacion con Novedades");
		} else if (this.recursoActRepository.existsByEmpleado(empleado)) {
			return ResponseEntity.badRequest()
					.body("No se puede eliminar el empleado, Tiene relacion con Actividades planeadas.");
		} else if (this.reporteRepository.existsByEmpleado(empleado)) {
			return ResponseEntity.badRequest()
					.body("No se puede eliminar el empleado, Tiene relacion con Reporte Tiempo.");
		} else {
			LogSistema log = new LogSistema();
			log.setAccion("DELETE");
			log.setFechaHora(new Date(Calendar.getInstance().getTime().getTime()));
			log.setIdAccion(empleado.getId());
			log.setDescripcion(empleado.toString());
			log.setTabla(Empleado.class.toString());
			this.logService.saveLog(log);
			this.empleadoRepository.deleteById(empleado.getId());
			Map<String, Boolean> response = new HashMap();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
		}
	}

	@GetMapping({ "/empleados/directores" })
	public List<Empleado> getGerentes() {
		Cargo cargo = (Cargo) this.cargoRepository.findById(12).orElseThrow(() -> {
			return new ResourceNotFoundException("Gerente no encontrado con id: 11");
		});
		List<Empleado> directores = (List) this.empleadoRepository.findByCargo(cargo).orElseThrow(() -> {
			return new ResourceNotFoundException("Empleados no encontrados con el cargo de Gerente de cuenta");
		});
		return directores;
	}

	@GetMapping({ "/empleados/directores-cli" })
	public List<Empleado> getDirectoresCli() {
		Cargo cargo = (Cargo) this.cargoRepository.findById(10).orElseThrow(() -> {
			return new ResourceNotFoundException("Cargo no encontrado con id: 10");
		});
		List<Empleado> directorCli = (List) this.empleadoRepository.findByCargo(cargo).orElseThrow(() -> {
			return new ResourceNotFoundException("Empleados no encontrados con el cargo de Director Asignado Cliente");
		});
		return directorCli;
	}

	@GetMapping({ "/empleados/directores-its" })
	public List<Empleado> getDirectoresIts() {
		Cargo cargo = (Cargo) this.cargoRepository.findById(3).orElseThrow(() -> {
			return new ResourceNotFoundException("Cargo no encontrado con id: 3");
		});
		List<Empleado> directorIts = (List) this.empleadoRepository.findByCargo(cargo).orElseThrow(() -> {
			return new ResourceNotFoundException("Empleados no encontrados con el cargo de Director Asignado ITS");
		});
		return directorIts;
	}

	@GetMapping({ "/empleados/lider" })
	public List<Empleado> getLiderProyecto() {
		Cargo cargo = (Cargo) this.cargoRepository.findById(2).orElseThrow(() -> {
			return new ResourceNotFoundException("Cargo no encontrado con id: 2");
		});
		List<Empleado> liderProyecto = (List) this.empleadoRepository.findByCargo(cargo).orElseThrow(() -> {
			return new ResourceNotFoundException("Empleados no encontrados con el cargo de Líder de Proyecto");
		});
		return liderProyecto;
	}

	@GetMapping({ "/empleados/planeacion/disponibles/{id}" })
	public List<Empleado> getAvailableEmpleados(@PathVariable Integer id) {
		ActividadAsignada actividad = (ActividadAsignada) this.actividadRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("Actividad no encontrada con id:" + id);
		});
		List<Empleado> allEmployees = (List) this.empleadoRepository.findAll();
		List<Empleado> availableEmployees = new ArrayList();
		List<LocalDate> fechasActividad = this.empleadoService.getFechasBetween(actividad.getFechaInicio(),
				actividad.getFechaFin());
		Iterator var6 = allEmployees.iterator();

		while (var6.hasNext()) {
			Empleado e = (Empleado) var6.next();
			boolean existsBetweenFechas = this.existsFechasBetween(e, fechasActividad);
			List<RecursoActividad> recursoAct = this.recursoActService.findByEmpleadoAndActividadNewDates(e, actividad);
			if (!existsBetweenFechas && e.getEstado().getId() == 1
					&& !recursoAct.isEmpty()) {
				availableEmployees.add(e);
			}
		}

		return availableEmployees;
	}

	public Boolean existsFechasBetween(Empleado empleado, List<LocalDate> fechas) {
		Boolean flag = true;
		Iterator var4 = fechas.iterator();

		while (var4.hasNext()) {
			LocalDate f = (LocalDate) var4.next();
			if (!this.recursoActService.existsByEmpleadoAndFecha(empleado, f)) {
				flag = false;
			}
		}

		return flag;
	}

	@GetMapping({ "/empleados/search/especialidad/{idActividad}/{idEspecialidad}" })
	public List<Empleado> searchRecursosByEspecialidad(@PathVariable Integer idActividad,
			@PathVariable Integer idEspecialidad) {
		Especialidad especialidad = (Especialidad) this.especialidadRepository.findById(idEspecialidad)
				.orElseThrow(() -> {
					return new ResourceNotFoundException("Especialidad no existe con id: " + idEspecialidad);
				});
		List<EmpleadoEspecialidad> especialidades = this.empEspRepository.findByEspecialidad(especialidad);
		List<Empleado> empleados = new ArrayList();
		this.getAvailableEmpleados(idActividad);
		Iterator var6 = especialidades.iterator();

		while (var6.hasNext()) {
			EmpleadoEspecialidad e = (EmpleadoEspecialidad) var6.next();
			if (e.getEmpleado().getEstado().getId() == 1) {
				empleados.add(e.getEmpleado());
			}
		}

		return this.getAvailableEmpleadosForSearch(empleados, idActividad);
	}

	@GetMapping({ "/empleados/search/nombre-containing/{idActividad}/{nombre}" })
	public ResponseEntity<?> searchRecursosByNombre(@PathVariable Integer idActividad, @PathVariable String nombre) {
		List<Empleado> empleados = this.empleadoRepository.findByNombreContaining(nombre);
		return ResponseEntity.ok(this.getAvailableEmpleadosForSearch(empleados, idActividad));
	}

	public List<Empleado> getAvailableEmpleadosForSearch(List<Empleado> empleados, Integer idActividad) {
		ActividadAsignada actividad = (ActividadAsignada) this.actividadRepository.findById(idActividad)
				.orElseThrow(() -> {
					return new ResourceNotFoundException("Actividad no encontrada con id:" + idActividad);
				});
		List<Empleado> availableEmployees = new ArrayList();
		List<LocalDate> fechasActividad = this.empleadoService.getFechasBetween(actividad.getFechaInicio(),
				actividad.getFechaFin());
		Iterator var6 = empleados.iterator();

		while (var6.hasNext()) {
			Empleado e = (Empleado) var6.next();
			if (!this.existsFechasBetween(e, fechasActividad) && e.getEstado().getId() == 1
					&& !this.recursoActService.findByEmpleadoAndActividadNewDates(e, actividad).isEmpty()) {
				availableEmployees.add(e);
			}
		}

		return availableEmployees;
	}

	@GetMapping({ "/empleados/search/nombre-recurso/{nombre}" })
	public ResponseEntity<?> searchRecursoByName(@PathVariable String nombre) {
		List<Empleado> empleados = this.empleadoRepository.findByNombreContaining(nombre);
		return ResponseEntity.ok(empleados);
	}
}

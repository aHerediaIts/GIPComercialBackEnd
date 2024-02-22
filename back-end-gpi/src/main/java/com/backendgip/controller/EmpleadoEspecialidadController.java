//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.controller;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.Empleado;
import com.backendgip.model.EmpleadoEspecialidad;
import com.backendgip.model.Especialidad;
import com.backendgip.model.LogSistema;
import com.backendgip.repository.EmpleadoEspecialidadRepository;
import com.backendgip.repository.EmpleadoRepository;
import com.backendgip.service.EmpleadoEspecialidadService;
import com.backendgip.service.EmpleadoService;
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
@RequestMapping({ "/api" })
public class EmpleadoEspecialidadController {
	@Autowired
	private EmpleadoEspecialidadService empleadoService;
	@Autowired
	private EmpleadoEspecialidadRepository emplEspecRepository;
	@Autowired
	private EmpleadoService empSer;
	@Autowired
	private EmpleadoRepository empleadoRepository;
	@Autowired
	private EspecialidadService espServ;
	@Autowired
	private LogSistemaService logService;

	public EmpleadoEspecialidadController() {
	}

	@GetMapping({ "/empleado-especialidad" })
	public List<EmpleadoEspecialidad> getEmpleado() {
		return this.empleadoService.getEmpleado();
	}

	@PostMapping({ "/empleado-especialidad" })
	public EmpleadoEspecialidad saveEmpleado(@RequestBody EmpleadoEspecialidad empleado) {
		EmpleadoEspecialidad createdEmpleadoEspecialidad = this.empleadoService.saveEmpleado(empleado);
		LogSistema log = new LogSistema();
		log.setAccion("CREATE");
		log.setFechaHora(new Date());
		log.setTabla(EmpleadoEspecialidad.class.toString());
		log.setIdAccion(createdEmpleadoEspecialidad.getId());
		log.setDescripcion(createdEmpleadoEspecialidad.toString());
		this.logService.saveLog(log);
		return createdEmpleadoEspecialidad;
	}

	@PutMapping({ "/empleado-especialidad/{id}" })
	public ResponseEntity<EmpleadoEspecialidad> updateEmpleado(@PathVariable Integer id,
			@RequestBody EmpleadoEspecialidad empleadoDetails) {
		EmpleadoEspecialidad empleado = (EmpleadoEspecialidad) this.emplEspecRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado el recurso solicitado" + id);
		});
		LogSistema log = new LogSistema();
		log.setAccion("UPDATE");
		log.setDescripcion(empleado.toString());
		log.setFechaHora(new Date());
		log.setIdAccion(empleado.getId());
		log.setTabla(empleado.getClass().toString());
		this.logService.saveLog(log);
		Empleado empleados = this.empSer.getEmpleadoById(empleadoDetails.getEmpleado().getId());
		empleado.setEmpleado(empleados);
		Especialidad esp = this.espServ.getEspecialidadById(empleadoDetails.getEspecialidad().getId());
		empleado.setEspecialidad(esp);
		EmpleadoEspecialidad updateEmpleado = this.empleadoService.saveEmpleado(empleado);
		return ResponseEntity.ok(updateEmpleado);
	}

	@GetMapping({ "/empleado-especialidad/{id}" })
	public ResponseEntity<EmpleadoEspecialidad> getEmpleadoById(@PathVariable Integer id) {
		EmpleadoEspecialidad empleado = (EmpleadoEspecialidad) this.emplEspecRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado el recurso solicitado" + id);
		});
		return ResponseEntity.ok(empleado);
	}

	@DeleteMapping({ "/empleado-especialidad/{id}" })
	public ResponseEntity<Map<String, Boolean>> deleteEmpleado(@PathVariable Integer id) {
		EmpleadoEspecialidad empleado = (EmpleadoEspecialidad) this.emplEspecRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado el recurso solicitado" + id);
		});
		LogSistema log = new LogSistema();
		log.setAccion("DELETE");
		log.setFechaHora(new Date());
		log.setIdAccion(empleado.getId());
		log.setDescripcion(empleado.toString());
		log.setTabla(EmpleadoEspecialidad.class.toString());
		this.logService.saveLog(log);
		this.emplEspecRepository.deleteById(empleado.getId());
		Map<String, Boolean> response = new HashMap();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

	@GetMapping({ "/empleado-especialidad/empleado/{id}" })
	public List<EmpleadoEspecialidad> findByEmpleado(@PathVariable Integer id) {
		Empleado empleado = (Empleado) this.empleadoRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("Empleado no encontrado con el id" + id);
		});
		return this.emplEspecRepository.findByEmpleado(empleado);
	}

	@DeleteMapping({ "/empleado-especialidad/delete-list/{id}" })
	public ResponseEntity<Map<String, Boolean>> deleteListByEmpleado(@PathVariable Integer id) {
		this.empleadoService.deleteListByEmpleado(this.empSer.getEmpleadoById(id));
		Map<String, Boolean> response = new HashMap();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}

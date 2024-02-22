//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.controller;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.Causas;
import com.backendgip.model.Empleado;
import com.backendgip.model.EstadoEmpleado;
import com.backendgip.model.LogSistema;
import com.backendgip.model.Novedad;
import com.backendgip.repository.EmpleadoRepository;
import com.backendgip.repository.EstadoEmpleadoRepository;
import com.backendgip.repository.NovedadRepository;
import com.backendgip.service.CausasService;
import com.backendgip.service.LogSistemaService;
import com.backendgip.service.NovedadService;
import java.time.LocalDate;
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
@RequestMapping({ "/api/empleados" })
public class NovedadController {
	@Autowired
	private NovedadService novedadService;
	@Autowired
	private NovedadRepository novedadRepository;
	@Autowired
	private EmpleadoRepository empleadoRepository;
	@Autowired
	private LogSistemaService logService;
	@Autowired
	private CausasService causaServ;
	@Autowired
	private EstadoEmpleadoRepository estadoEmpRepository;

	public NovedadController() {
	}

	@GetMapping({ "/novedades" })
	public List<Novedad> getNovedad() {
		return this.novedadService.findAll();
	}

	@PostMapping({ "/novedades" })
	public ResponseEntity<?> saveNovedad(@RequestBody Novedad novedad) {
		System.out.println(novedad);
		if (!this.availableDateNovedad(novedad)) {
			return ResponseEntity.badRequest().body("No se puede agregar otra novedad");
		} else {
			Novedad createdNovedad = this.novedadService.save(novedad);
			LogSistema log = new LogSistema();
			log.setAccion("CREATE");
			log.setFechaHora(new Date());
			log.setTabla(Novedad.class.toString());
			log.setIdAccion(createdNovedad.getId());
			log.setDescripcion(createdNovedad.toString());
			this.logService.saveLog(log);
			return ResponseEntity.ok(createdNovedad);
		}
	}

	@PutMapping({ "/novedades/{id}" })
	public ResponseEntity<Novedad> updateNovedad(@PathVariable Integer id, @RequestBody Novedad novedadDetails) {
		Novedad novedad = (Novedad) this.novedadRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado el recurso solicitado" + id);
		});
		LogSistema log = new LogSistema();
		log.setAccion("UPDATE");
		log.setDescripcion(novedad.toString());
		log.setFechaHora(new Date());
		log.setIdAccion(novedad.getId());
		log.setTabla(novedad.getClass().toString());
		this.logService.saveLog(log);
		novedad.setFechaInicio(novedadDetails.getFechaInicio());
		novedad.setFechaFin(novedadDetails.getFechaFin());
		novedad.setEmpleado(novedadDetails.getEmpleado());
		Causas causa = this.causaServ.getCausaById(novedadDetails.getCausa().getId());
		novedad.setCausa(causa);
		Novedad updateNovedad = this.novedadService.save(novedad);
		return ResponseEntity.ok(updateNovedad);
	}

	@GetMapping({ "/novedades/{id}" })
	public ResponseEntity<Novedad> getNovedadById(@PathVariable Integer id) {
		Novedad novedad = (Novedad) this.novedadRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado el recurso solicitado" + id);
		});
		return ResponseEntity.ok(novedad);
	}

	@DeleteMapping({ "/novedades/{id}" })
	public ResponseEntity<Map<String, Boolean>> deleteNovedad(@PathVariable Integer id) {
		Novedad novedad = (Novedad) this.novedadRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado el recurso solicitado" + id);
		});
		Empleado empleado = novedad.getEmpleado();
		EstadoEmpleado estado = (EstadoEmpleado) this.estadoEmpRepository.findById(1).orElseThrow(() -> {
			return new ResourceNotFoundException("Estado no encontrado con id: 1");
		});
		empleado.setEstado(estado);
		LogSistema log = new LogSistema();
		log.setAccion("DELETE");
		log.setFechaHora(new Date());
		log.setIdAccion(novedad.getId());
		log.setDescripcion(novedad.toString());
		log.setTabla(Novedad.class.toString());
		this.logService.saveLog(log);
		this.empleadoRepository.save(empleado);
		this.novedadRepository.deleteById(novedad.getId());
		Map<String, Boolean> response = new HashMap();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

	@GetMapping({ "/novedades/empleado/{id}" })
	public List<Novedad> findByEmpleado(@PathVariable Integer id) {
		return this.novedadService.findByEmpleado((Empleado) this.empleadoRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("Empleado no encontrado con id:" + id);
		}));
	}

	public boolean isBetweenDates(LocalDate fechaInicio, LocalDate fechaFin, LocalDate fechaInicioToValid,
			LocalDate fechaFinToValid) {
		return fechaInicioToValid.isAfter(fechaInicio) && fechaInicioToValid.isBefore(fechaFin)
				|| fechaFinToValid.isAfter(fechaInicio) && fechaFinToValid.isBefore(fechaFin)
				|| fechaInicio.isEqual(fechaFin) && fechaInicio.isAfter(fechaInicioToValid)
						&& fechaInicio.isBefore(fechaFinToValid)
				|| fechaInicio.isEqual(fechaInicioToValid) || fechaInicio.isEqual(fechaFinToValid);
	}

	public boolean availableDateNovedad(Novedad novedad) {
		List<Novedad> novedades = this.novedadService.findByEmpleado(novedad.getEmpleado());
		Iterator var3 = novedades.iterator();

		Novedad e;
		do {
			if (!var3.hasNext()) {
				return true;
			}

			e = (Novedad) var3.next();
		} while (!this.isBetweenDates(novedad.getFechaInicio(), novedad.getFechaFin(), e.getFechaInicio(),
				e.getFechaFin()));

		return false;
	}
}

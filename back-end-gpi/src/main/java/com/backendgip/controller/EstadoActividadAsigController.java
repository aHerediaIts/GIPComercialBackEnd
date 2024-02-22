//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.controller;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.EstadoActividadAsig;
import com.backendgip.model.LogSistema;
import com.backendgip.repository.EstadoActividadAsigRepository;
import com.backendgip.service.EstadoActividadAsigService;
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
@RequestMapping({ "/api/proyectos/planeacion/actividades-asig" })
public class EstadoActividadAsigController {
	@Autowired
	private EstadoActividadAsigService estadoService;
	@Autowired
	private EstadoActividadAsigRepository estadoRepository;
	@Autowired
	private LogSistemaService logService;

	public EstadoActividadAsigController() {
	}

	@GetMapping({ "/estados" })
	public List<EstadoActividadAsig> getEstados() {
		return this.estadoService.getEstados();
	}

	@PostMapping({ "/estados" })
	public EstadoActividadAsig saveEstado(@RequestBody EstadoActividadAsig estado) {
		EstadoActividadAsig createdEstadoActividadAsig = this.estadoService.saveEstado(estado);
		LogSistema log = new LogSistema();
		log.setAccion("CREATE");
		log.setFechaHora(new Date());
		log.setTabla(EstadoActividadAsig.class.toString());
		log.setIdAccion(createdEstadoActividadAsig.getId());
		log.setDescripcion(createdEstadoActividadAsig.toString());
		this.logService.saveLog(log);
		return createdEstadoActividadAsig;
	}

	@GetMapping({ "/estados/{id}" })
	public ResponseEntity<EstadoActividadAsig> getEstadoById(@PathVariable Integer id) {
		EstadoActividadAsig estado = (EstadoActividadAsig) this.estadoRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("Estado no encontrado con id: " + id);
		});
		return ResponseEntity.ok(estado);
	}

	@PutMapping({ "/estados/{id}" })
	public ResponseEntity<EstadoActividadAsig> updateEstado(@PathVariable Integer id,
			@RequestBody EstadoActividadAsig estadoDetails) {
		EstadoActividadAsig estado = (EstadoActividadAsig) this.estadoRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("Estado no encontrado con id: " + id);
		});
		LogSistema log = new LogSistema();
		log.setAccion("UPDATE");
		log.setDescripcion(estado.toString());
		log.setFechaHora(new Date());
		log.setIdAccion(estado.getId());
		log.setTabla(estado.getClass().toString());
		this.logService.saveLog(log);
		estado.setEstado(estadoDetails.getEstado());
		EstadoActividadAsig updatedEstado = this.estadoService.saveEstado(estado);
		return ResponseEntity.ok(updatedEstado);
	}

	@DeleteMapping({ "/estados/{id}" })
	public ResponseEntity<Map<String, Boolean>> deleteEstado(@PathVariable Integer id) {
		EstadoActividadAsig estado = (EstadoActividadAsig) this.estadoRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("Estado no encontrado con id: " + id);
		});
		LogSistema log = new LogSistema();
		log.setAccion("DELETE");
		log.setFechaHora(new Date());
		log.setIdAccion(estado.getId());
		log.setDescripcion(estado.toString());
		log.setTabla(EstadoActividadAsig.class.toString());
		this.logService.saveLog(log);
		this.estadoService.deleteEstado(estado);
		Map<String, Boolean> response = new HashMap();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}

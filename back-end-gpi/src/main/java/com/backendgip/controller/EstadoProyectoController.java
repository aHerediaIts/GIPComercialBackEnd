//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.controller;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.EstadoProyecto;
import com.backendgip.model.LogSistema;
import com.backendgip.repository.EstadoProyectoRepository;
import com.backendgip.service.EstadoProyectoService;
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
public class EstadoProyectoController {
	@Autowired
	private EstadoProyectoService estadoService;
	@Autowired
	private EstadoProyectoRepository estadoRepository;
	@Autowired
	private LogSistemaService logService;

	public EstadoProyectoController() {
	}

	@GetMapping({ "/estados-proyecto" })
	public List<EstadoProyecto> getAllEstados() {
		return this.estadoService.getEstados();
	}

	@PostMapping({ "/estados-proyecto" })
	public EstadoProyecto saveEstado(@RequestBody EstadoProyecto estado) {
		EstadoProyecto createdEstadoProyecto = this.estadoService.saveEstado(estado);
		LogSistema log = new LogSistema();
		log.setAccion("CREATE");
		log.setFechaHora(new Date());
		log.setTabla(EstadoProyecto.class.toString());
		log.setIdAccion(createdEstadoProyecto.getId());
		log.setDescripcion(createdEstadoProyecto.toString());
		this.logService.saveLog(log);
		return createdEstadoProyecto;
	}

	@PutMapping({ "/estados-proyecto/{id}" })
	public ResponseEntity<EstadoProyecto> updateEstado(@PathVariable Integer id,
			@RequestBody EstadoProyecto estadoDetails) {
		EstadoProyecto estado = (EstadoProyecto) this.estadoRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("Estado proyecto no encontrado con id: " + id);
		});
		LogSistema log = new LogSistema();
		log.setAccion("UPDATE");
		log.setDescripcion(estado.toString());
		log.setFechaHora(new Date());
		log.setIdAccion(estado.getId());
		log.setTabla(estado.getClass().toString());
		this.logService.saveLog(log);
		estado.setEstado(estadoDetails.getEstado());
		EstadoProyecto updateEstado = this.estadoService.saveEstado(estado);
		return ResponseEntity.ok(updateEstado);
	}

	@GetMapping({ "/estados-proyecto/{id}" })
	public ResponseEntity<EstadoProyecto> getEstadoById(@PathVariable Integer id) {
		EstadoProyecto estado = (EstadoProyecto) this.estadoRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("Estado proyecto no encontrado con id: " + id);
		});
		return ResponseEntity.ok(estado);
	}

	@DeleteMapping({ "/estados-proyecto/{id}" })
	public ResponseEntity<Map<String, Boolean>> deleteEstado(@PathVariable Integer id) {
		EstadoProyecto estado = (EstadoProyecto) this.estadoRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("Estado proyecto no encontrado con id: " + id);
		});
		LogSistema log = new LogSistema();
		log.setAccion("DELETE");
		log.setFechaHora(new Date());
		log.setIdAccion(estado.getId());
		log.setDescripcion(estado.toString());
		log.setTabla(EstadoProyecto.class.toString());
		this.logService.saveLog(log);
		this.estadoRepository.deleteById(estado.getId());
		Map<String, Boolean> response = new HashMap();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}

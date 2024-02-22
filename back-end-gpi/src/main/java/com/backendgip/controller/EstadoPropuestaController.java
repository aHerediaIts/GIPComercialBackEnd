//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.controller;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.EstadoPropuesta;
import com.backendgip.model.LogSistema;
import com.backendgip.repository.EstadoPropuestaRepository;
import com.backendgip.service.EstadoPropuestaService;
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
public class EstadoPropuestaController {
	@Autowired
	private EstadoPropuestaService estadoService;
	@Autowired
	private EstadoPropuestaRepository estadoRespository;
	@Autowired
	private LogSistemaService logService;

	public EstadoPropuestaController() {
	}

	@GetMapping({ "/estados-propuesta" })
	public List<EstadoPropuesta> getAllEstados() {
		return this.estadoService.getEstados();
	}

	@PostMapping({ "/estados-propuesta" })
	public EstadoPropuesta saveEstado(@RequestBody EstadoPropuesta estado) {
		EstadoPropuesta createdEstadoPropuesta = this.estadoService.saveEstado(estado);
		LogSistema log = new LogSistema();
		log.setAccion("CREATE");
		log.setFechaHora(new Date());
		log.setTabla(EstadoPropuesta.class.toString());
		log.setIdAccion(createdEstadoPropuesta.getId());
		log.setDescripcion(createdEstadoPropuesta.toString());
		this.logService.saveLog(log);
		return createdEstadoPropuesta;
	}

	@PutMapping({ "/estados-propuesta/{id}" })
	public ResponseEntity<EstadoPropuesta> updateEstado(@PathVariable Integer id,
			@RequestBody EstadoPropuesta estadoDetails) {
		EstadoPropuesta estado = (EstadoPropuesta) this.estadoRespository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("Estado propuesta no encontrado con id: " + id);
		});
		LogSistema log = new LogSistema();
		log.setAccion("UPDATE");
		log.setDescripcion(estado.toString());
		log.setFechaHora(new Date());
		log.setIdAccion(estado.getId());
		log.setTabla(estado.getClass().toString());
		this.logService.saveLog(log);
		estado.setEstado(estadoDetails.getEstado());
		EstadoPropuesta updateEstado = this.estadoService.saveEstado(estado);
		return ResponseEntity.ok(updateEstado);
	}

	@GetMapping({ "/estados-propuesta/{id}" })
	public ResponseEntity<EstadoPropuesta> getEstadoById(@PathVariable Integer id) {
		EstadoPropuesta estado = (EstadoPropuesta) this.estadoRespository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("Estado propuesta no encontrado con id: " + id);
		});
		return ResponseEntity.ok(estado);
	}

	@DeleteMapping({ "/estados-propuesta/{id}" })
	public ResponseEntity<Map<String, Boolean>> deleteEstado(@PathVariable Integer id) {
		EstadoPropuesta estado = (EstadoPropuesta) this.estadoRespository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("Estado propuesta no encontrado con id: " + id);
		});
		LogSistema log = new LogSistema();
		log.setAccion("DELETE");
		log.setFechaHora(new Date());
		log.setIdAccion(estado.getId());
		log.setDescripcion(estado.toString());
		log.setTabla(EstadoPropuesta.class.toString());
		this.logService.saveLog(log);
		this.estadoRespository.deleteById(estado.getId());
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}

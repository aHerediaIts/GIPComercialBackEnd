//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.controller;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.EstadoCliente;
import com.backendgip.model.LogSistema;
import com.backendgip.repository.EstadoClienteRepository;
import com.backendgip.service.EstadoClienteService;
import com.backendgip.service.LogSistemaService;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
@RequestMapping({ "/api/clientes" })
public class EstadoClienteController {
	@Autowired
	private EstadoClienteService estadoService;
	@Autowired
	private EstadoClienteRepository estadoRepository;
	@Autowired
	private LogSistemaService logService;

	public EstadoClienteController() {
	}

	@GetMapping({ "/estados" })
	public List<EstadoCliente> getEstados() {
		return this.estadoService.getEstados();
	}

	@PostMapping({ "/estados" })
	public EstadoCliente saveEstado(@RequestBody EstadoCliente estado) {
		EstadoCliente createdEstadoCliente = this.estadoService.saveEstado(estado);
		LogSistema log = new LogSistema();
		log.setAccion("CREATE");
		log.setFechaHora(new Date());
		log.setTabla(EstadoCliente.class.toString());
		log.setIdAccion(createdEstadoCliente.getId());
		log.setDescripcion(createdEstadoCliente.toString());
		this.logService.saveLog(log);
		return createdEstadoCliente;
	}

	@PutMapping({ "/estados/{id}" })
	public ResponseEntity<EstadoCliente> updateEstado(@PathVariable Integer id,
			@RequestBody EstadoCliente estadoDetails) {
		EstadoCliente estado = (EstadoCliente) this.estadoRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha escontrado estado con id:" + id);
		});
		LogSistema log = new LogSistema();
		log.setAccion("UPDATE");
		log.setDescripcion(estado.toString());
		log.setFechaHora(new Date());
		log.setIdAccion(estado.getId());
		log.setTabla(estado.getClass().toString());
		this.logService.saveLog(log);
		estado.setEstado(estadoDetails.getEstado());
		EstadoCliente updatedEstado = this.estadoService.saveEstado(estado);
		return ResponseEntity.ok(updatedEstado);
	}

	@GetMapping({ "/estados/{id}" })
	public ResponseEntity<EstadoCliente> getEstadoById(@PathVariable Integer id) {
		EstadoCliente estado = (EstadoCliente) this.estadoRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado estado con id:" + id);
		});
		return ResponseEntity.ok(estado);
	}

	public ResponseEntity<Map<String, Boolean>> deleteEstado(@PathVariable Integer id) {
		EstadoCliente estado = (EstadoCliente) this.estadoRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado estado con id:" + id);
		});
		LogSistema log = new LogSistema();
		log.setAccion("DELETE");
		log.setFechaHora(new Date());
		log.setIdAccion(estado.getId());
		log.setDescripcion(estado.toString());
		log.setTabla(EstadoCliente.class.toString());
		this.logService.saveLog(log);
		this.estadoService.deleteEstado(estado);
		Map<String, Boolean> response = new HashMap();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.controller;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.EstadoEmpleado;
import com.backendgip.model.LogSistema;
import com.backendgip.repository.EstadoEmpleadoRepository;
import com.backendgip.service.EstadoEmpleadoService;
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
@RequestMapping({ "/api/empleados" })
public class EstadoEmpleadoController {
	@Autowired
	private EstadoEmpleadoService estadoService;
	@Autowired
	private EstadoEmpleadoRepository estadoRespository;
	@Autowired
	private LogSistemaService logService;

	public EstadoEmpleadoController() {
	}

	@GetMapping({ "/estados" })
	public List<EstadoEmpleado> getEstado() {
		return this.estadoService.getEstado();
	}

	@PostMapping({ "/estados" })
	public EstadoEmpleado saveEstado(@RequestBody EstadoEmpleado estado) {
		EstadoEmpleado createdEstadoEmpleado = this.estadoService.saveEstado(estado);
		LogSistema log = new LogSistema();
		log.setAccion("CREATE");
		log.setFechaHora(new Date());
		log.setTabla(EstadoEmpleado.class.toString());
		log.setIdAccion(createdEstadoEmpleado.getId());
		log.setDescripcion(createdEstadoEmpleado.toString());
		this.logService.saveLog(log);
		return createdEstadoEmpleado;
	}

	@PutMapping({ "/estados/{id}" })
	public ResponseEntity<EstadoEmpleado> updateEstado(@PathVariable Integer id,
			@RequestBody EstadoEmpleado estadoDetails) {
		EstadoEmpleado estado = (EstadoEmpleado) this.estadoRespository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado el recurso solicitado" + id);
		});
		LogSistema log = new LogSistema();
		log.setAccion("UPDATE");
		log.setDescripcion(estado.toString());
		log.setFechaHora(new Date());
		log.setIdAccion(estado.getId());
		log.setTabla(estado.getClass().toString());
		this.logService.saveLog(log);
		estado.setEstado(estadoDetails.getEstado());
		EstadoEmpleado updateEstado = this.estadoService.saveEstado(estado);
		return ResponseEntity.ok(updateEstado);
	}

	@GetMapping({ "/estados/{id}" })
	public ResponseEntity<EstadoEmpleado> getEstadoById(@PathVariable Integer id) {
		EstadoEmpleado estado = (EstadoEmpleado) this.estadoRespository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado el recurso solicitado" + id);
		});
		return ResponseEntity.ok(estado);
	}

	@DeleteMapping({ "/estados/{id}" })
	public ResponseEntity<Map<String, Boolean>> deleteEstado(@PathVariable Integer id) {
		EstadoEmpleado estado = (EstadoEmpleado) this.estadoRespository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado el recurso solicitado" + id);
		});
		LogSistema log = new LogSistema();
		log.setAccion("DELETE");
		log.setFechaHora(new Date());
		log.setIdAccion(estado.getId());
		log.setDescripcion(estado.toString());
		log.setTabla(EstadoEmpleado.class.toString());
		this.logService.saveLog(log);
		this.estadoRespository.deleteById(estado.getId());
		Map<String, Boolean> response = new HashMap();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}

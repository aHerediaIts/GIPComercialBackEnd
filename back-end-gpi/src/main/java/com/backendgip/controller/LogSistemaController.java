//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.controller;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.LogSistema;
import com.backendgip.repository.LogSistemaRepository;
import com.backendgip.service.LogSistemaService;
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
public class LogSistemaController {
	@Autowired
	private LogSistemaRepository logRepository;
	@Autowired
	private LogSistemaService logService;

	public LogSistemaController() {
	}

	@GetMapping({ "/logs-sistema" })
	public List<LogSistema> getLogs() {
		return this.logService.getLogs();
	}

	@PostMapping({ "/logs-sistema" })
	public LogSistema saveLog(@RequestBody LogSistema log) {
		return this.logService.saveLog(log);
	}

	@PutMapping({ "/logs-sistema/{id}" })
	public ResponseEntity<LogSistema> updateLog(@PathVariable Integer id, @RequestBody LogSistema logDetails) {
		LogSistema log = (LogSistema) this.logRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("Log no encontrado con id: " + id);
		});
		log.setAccion(logDetails.getAccion());
		log.setDescripcion(logDetails.getDescripcion());
		log.setFechaHora(logDetails.getFechaHora());
		log.setIdAccion(logDetails.getIdAccion());
		log.setServidor(logDetails.getServidor());
		log.setTabla(logDetails.getTabla());
		log.setUsuario(logDetails.getUsuario());
		LogSistema updatedLog = this.logService.saveLog(log);
		return ResponseEntity.ok(updatedLog);
	}

	@GetMapping({ "/logs-sistema/{id}" })
	public ResponseEntity<LogSistema> getLogById(@PathVariable Integer id) {
		LogSistema log = (LogSistema) this.logRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("Log no encontrado con id: " + id);
		});
		return ResponseEntity.ok(log);
	}

	@DeleteMapping({ "/logs-sistema/{id}" })
	public ResponseEntity<Map<String, Boolean>> deleteLog(@PathVariable Integer id) {
		LogSistema log = (LogSistema) this.logRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("Log no encontrado con id: " + id);
		});
		this.logService.deleteLog(log);
		Map<String, Boolean> response = new HashMap();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}

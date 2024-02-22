//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.controller;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.EtapaProyecto;
import com.backendgip.model.LogSistema;
import com.backendgip.repository.EtapaProyectoRepository;
import com.backendgip.service.EtapaProyectoService;
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
public class EtapaProyectoController {
	@Autowired
	private EtapaProyectoService etapaService;
	@Autowired
	private EtapaProyectoRepository etapaRepository;
	@Autowired
	private LogSistemaService logService;

	public EtapaProyectoController() {
	}

	@GetMapping({ "/etapas" })
	public List<EtapaProyecto> getAllEtapas() {
		return this.etapaService.getEtapas();
	}

	@PostMapping({ "/etapas" })
	public EtapaProyecto saveEtapa(@RequestBody EtapaProyecto etapa) {
		EtapaProyecto createdEtapaProyecto = this.etapaService.saveEtapa(etapa);
		LogSistema log = new LogSistema();
		log.setAccion("CREATE");
		log.setFechaHora(new Date());
		log.setTabla(EtapaProyecto.class.toString());
		log.setIdAccion(createdEtapaProyecto.getId());
		log.setDescripcion(createdEtapaProyecto.toString());
		this.logService.saveLog(log);
		return createdEtapaProyecto;
	}

	@PutMapping({ "/etapas/{id}" })
	public ResponseEntity<EtapaProyecto> updateEtapa(@PathVariable Integer id,
			@RequestBody EtapaProyecto etapaDetails) {
		EtapaProyecto etapa = (EtapaProyecto) this.etapaRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("Etapa proyecto no encontrada con id: " + id);
		});
		LogSistema log = new LogSistema();
		log.setAccion("UPDATE");
		log.setDescripcion(etapa.toString());
		log.setFechaHora(new Date());
		log.setIdAccion(etapa.getId());
		log.setTabla(etapa.getClass().toString());
		this.logService.saveLog(log);
		etapa.setEtapa(etapaDetails.getEtapa());
		EtapaProyecto etapaUpdate = this.etapaService.saveEtapa(etapa);
		return ResponseEntity.ok(etapaUpdate);
	}

	@GetMapping({ "/etapas/{id}" })
	public ResponseEntity<EtapaProyecto> getEtapaById(@PathVariable Integer id) {
		EtapaProyecto etapa = (EtapaProyecto) this.etapaRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("Etapa proyecto no encontrada con id: " + id);
		});
		return ResponseEntity.ok(etapa);
	}

	@DeleteMapping({ "/etapas/{id}" })
	public ResponseEntity<Map<String, Boolean>> deleteEtapa(@PathVariable Integer id) {
		EtapaProyecto etapa = (EtapaProyecto) this.etapaRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("Etapa proyecto no encontrada con id: " + id);
		});
		LogSistema log = new LogSistema();
		log.setAccion("DELETE");
		log.setFechaHora(new Date());
		log.setIdAccion(etapa.getId());
		log.setDescripcion(etapa.toString());
		log.setTabla(EtapaProyecto.class.toString());
		this.logService.saveLog(log);
		this.etapaRepository.deleteById(etapa.getId());
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}

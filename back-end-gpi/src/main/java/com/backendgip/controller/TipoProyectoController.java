//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.controller;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.LogSistema;
import com.backendgip.model.TipoProyecto;
import com.backendgip.repository.ProyectoRepository;
import com.backendgip.repository.TipoProyectoRepository;
import com.backendgip.service.LogSistemaService;
import com.backendgip.service.TipoProyectoService;
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
public class TipoProyectoController {
	@Autowired
	private TipoProyectoService tipoService;
	@Autowired
	private TipoProyectoRepository tipoRepository;
	@Autowired
	private LogSistemaService logService;
	@Autowired
	private ProyectoRepository proyectoRepository;

	public TipoProyectoController() {
	}

	@GetMapping({ "/tipos" })
	public List<TipoProyecto> getAllTipos() {
		return this.tipoService.getTipos();
	}

	@PostMapping({ "/tipos" })
	public ResponseEntity<?> savetipo(@RequestBody TipoProyecto tipo) {
		if (this.tipoRepository.existsByTipo(tipo.getTipo())) {
			return ResponseEntity.badRequest().body("Tipo ya existe");
		} else {
			TipoProyecto createdTipoProyecto = this.tipoService.saveTipo(tipo);
			LogSistema log = new LogSistema();
			log.setAccion("CREATE");
			log.setFechaHora(new Date());
			log.setTabla(TipoProyecto.class.toString());
			log.setIdAccion(createdTipoProyecto.getId());
			log.setDescripcion(createdTipoProyecto.toString());
			this.logService.saveLog(log);
			return ResponseEntity.ok(createdTipoProyecto);
		}
	}

	@PutMapping({ "/tipos/{id}" })
	public ResponseEntity<TipoProyecto> updateTipo(@PathVariable Integer id, @RequestBody TipoProyecto tipoDetails) {
		TipoProyecto tipo = (TipoProyecto) this.tipoRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("Tipo de proyecto no encontrado con id: " + id);
		});
		LogSistema log = new LogSistema();
		log.setAccion("UPDATE");
		log.setDescripcion(tipo.toString());
		log.setFechaHora(new Date());
		log.setIdAccion(tipo.getId());
		log.setTabla(tipo.getClass().toString());
		this.logService.saveLog(log);
		tipo.setTipo(tipoDetails.getTipo());
		TipoProyecto updateTipo = this.tipoService.saveTipo(tipo);
		return ResponseEntity.ok(updateTipo);
	}

	@GetMapping({ "/tipos/{id}" })
	public ResponseEntity<TipoProyecto> getTipoById(@PathVariable Integer id) {
		TipoProyecto tipo = (TipoProyecto) this.tipoRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("Tipo de proyecto no encontrado con id: " + id);
		});
		return ResponseEntity.ok(tipo);
	}

	@DeleteMapping({ "/tipos/{id}" })
	public ResponseEntity<?> deleteTipo(@PathVariable Integer id) {
		TipoProyecto tipo = (TipoProyecto) this.tipoRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("Tipo de proyecto no encontrado con id: " + id);
		});
		if (this.proyectoRepository.existsByTipo(tipo)) {
			return ResponseEntity.badRequest().body("No se puede eliminar el tipo, Esta relacionado con proyecto.");
		} else {
			LogSistema log = new LogSistema();
			log.setAccion("DELETE");
			log.setFechaHora(new Date());
			log.setIdAccion(tipo.getId());
			log.setDescripcion(tipo.toString());
			log.setTabla(TipoProyecto.class.toString());
			this.logService.saveLog(log);
			this.tipoRepository.deleteById(tipo.getId());
			Map<String, Boolean> response = new HashMap();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
		}
	}
}

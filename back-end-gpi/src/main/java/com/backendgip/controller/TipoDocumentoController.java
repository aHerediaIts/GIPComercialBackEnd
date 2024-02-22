//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.controller;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.LogSistema;
import com.backendgip.model.TipoDocumento;
import com.backendgip.repository.TipoDocumentoRepository;
import com.backendgip.service.LogSistemaService;
import com.backendgip.service.TipoDocumentoService;
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
public class TipoDocumentoController {
	@Autowired
	private TipoDocumentoService tipoService;
	@Autowired
	private TipoDocumentoRepository tipoRepository;
	@Autowired
	private LogSistemaService logService;

	public TipoDocumentoController() {
	}

	@GetMapping({ "/tipo-documento" })
	public List<TipoDocumento> getAllTipos() {
		return this.tipoService.getTipos();
	}

	@PostMapping({ "/tipo-documento" })
	public TipoDocumento saveTipo(@RequestBody TipoDocumento tipo) {
		TipoDocumento createdTipoDocumento = this.tipoService.saveTipo(tipo);
		LogSistema log = new LogSistema();
		log.setAccion("CREATE");
		log.setFechaHora(new Date());
		log.setTabla(TipoDocumento.class.toString());
		log.setIdAccion(createdTipoDocumento.getId());
		log.setDescripcion(createdTipoDocumento.toString());
		this.logService.saveLog(log);
		return createdTipoDocumento;
	}

	@PutMapping({ "/tipo-documento/{id}" })
	public ResponseEntity<TipoDocumento> updateTipo(@PathVariable Integer id, @RequestBody TipoDocumento tipoDetails) {
		TipoDocumento tipo = (TipoDocumento) this.tipoRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado el recurso solicitado" + id);
		});
		LogSistema log = new LogSistema();
		log.setAccion("UPDATE");
		log.setDescripcion(tipo.toString());
		log.setFechaHora(new Date());
		log.setIdAccion(tipo.getId());
		log.setTabla(tipo.getClass().toString());
		this.logService.saveLog(log);
		tipo.setTipo(tipoDetails.getTipo());
		TipoDocumento updateTipo = this.tipoService.saveTipo(tipo);
		return ResponseEntity.ok(updateTipo);
	}

	@GetMapping({ "/tipo-documento/{id}" })
	public ResponseEntity<TipoDocumento> getTipoById(@PathVariable Integer id) {
		TipoDocumento tipo = (TipoDocumento) this.tipoRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado el recurso solicitado" + id);
		});
		return ResponseEntity.ok(tipo);
	}

	@DeleteMapping({ "/tipo-documento/{id}" })
	public ResponseEntity<Map<String, Boolean>> deleteTipo(@PathVariable Integer id) {
		TipoDocumento tipo = (TipoDocumento) this.tipoRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado el recurso solicitado" + id);
		});
		LogSistema log = new LogSistema();
		log.setAccion("DELETE");
		log.setFechaHora(new Date());
		log.setIdAccion(tipo.getId());
		log.setDescripcion(tipo.toString());
		log.setTabla(TipoDocumento.class.toString());
		this.logService.saveLog(log);
		this.tipoRepository.deleteById(tipo.getId());
		Map<String, Boolean> response = new HashMap();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}

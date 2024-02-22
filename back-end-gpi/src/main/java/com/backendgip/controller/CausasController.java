//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.controller;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.Causas;
import com.backendgip.model.LogSistema;
import com.backendgip.repository.CausasRepository;
import com.backendgip.service.CausasService;
import com.backendgip.service.LogSistemaService;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "/api/empleados" })
public class CausasController {
	@Autowired
	private CausasService causaServ;
	@Autowired
	private CausasRepository causaRepo;
	@Autowired
	private LogSistemaService logService;

	public CausasController() {
	}

	@GetMapping({ "/causas" })
	public List<Causas> getCausas() {
		return this.causaServ.getCausas();
	}

	@PostMapping({ "/causas" })
	public Causas saveCausa(@RequestBody Causas causa) {
		Causas createdCausas = this.causaServ.saveCausa(causa);
		LogSistema log = new LogSistema();
		log.setAccion("CREATE");
		log.setFechaHora(new Date());
		log.setTabla(Causas.class.toString());
		log.setIdAccion(createdCausas.getId());
		log.setDescripcion(createdCausas.toString());
		this.logService.saveLog(log);
		return createdCausas;
	}

	@PutMapping({ "/causas/{id}" })
	public ResponseEntity<Causas> updateCausa(@PathVariable Integer id, @RequestBody Causas causaDetails) {
		Causas causa = (Causas) this.causaRepo.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado la causa con el id: " + id);
		});
		LogSistema log = new LogSistema();
		log.setAccion("UPDATE");
		log.setDescripcion(causa.toString());
		log.setFechaHora(new Date());
		log.setIdAccion(causa.getId());
		log.setTabla(causa.getClass().toString());
		this.logService.saveLog(log);
		causa.setCausas(causaDetails.getCausas());
		Causas updateCausa = this.causaServ.saveCausa(causa);
		return ResponseEntity.ok(updateCausa);
	}

	@GetMapping({ "/causas/{id}" })
	public ResponseEntity<Causas> getCausaById(@PathVariable Integer id) {
		Causas causa = (Causas) this.causaRepo.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado la causa con el id: " + id);
		});
		return ResponseEntity.ok(causa);
	}

	@DeleteMapping({ "/causas/{id}" })
	public ResponseEntity<Map<String, Boolean>> deleteCausa(@PathVariable Integer id) {
		Causas causa = (Causas) this.causaRepo.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado la causa con el id: " + id);
		});
		LogSistema log = new LogSistema();
		log.setAccion("DELETE");
		log.setFechaHora(new Date());
		log.setIdAccion(causa.getId());
		log.setDescripcion(causa.toString());
		log.setTabla(Causas.class.toString());
		this.logService.saveLog(log);
		this.causaRepo.deleteById(causa.getId());
		Map<String, Boolean> response = new HashMap();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}

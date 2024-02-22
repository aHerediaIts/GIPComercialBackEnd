//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.controller;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.ComponenteDesarrollo;
import com.backendgip.model.LogSistema;
import com.backendgip.repository.ComponenteDesarrolloRepository;
import com.backendgip.repository.ProyectoRepository;
import com.backendgip.service.ComponenteDesarrolloService;
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
public class ComponenteDesarrolloController {
	@Autowired
	private ComponenteDesarrolloService componenteService;
	@Autowired
	private ComponenteDesarrolloRepository componenteRespository;
	@Autowired
	private LogSistemaService logService;
	@Autowired
	private ProyectoRepository proyectoRepository;

	public ComponenteDesarrolloController() {
	}

	@GetMapping({ "/componentes" })
	public List<ComponenteDesarrollo> getAllComponentes() {
		return this.componenteService.getComponentes();
	}

	@PostMapping({ "/componentes" })
	public ResponseEntity<?> saveComponente(@RequestBody ComponenteDesarrollo componente) {
		if (this.componenteRespository.existsByComponente(componente.getComponente())) {
			return ResponseEntity.badRequest().body("Componente ya existe");
		} else {
			ComponenteDesarrollo createdComponente = this.componenteService.saveComponente(componente);
			LogSistema log = new LogSistema();
			log.setAccion("CREATE");
			log.setFechaHora(new Date());
			log.setTabla(ComponenteDesarrollo.class.toString());
			log.setIdAccion(createdComponente.getId());
			log.setDescripcion(createdComponente.toString());
			this.logService.saveLog(log);
			return ResponseEntity.ok(createdComponente);
		}
	}

	@PutMapping({ "/componentes/{id}" })
	public ResponseEntity<ComponenteDesarrollo> updateComponente(@PathVariable Integer id,
			@RequestBody ComponenteDesarrollo componenteDetails) {
		ComponenteDesarrollo componente = (ComponenteDesarrollo) this.componenteRespository.findById(id)
				.orElseThrow(() -> {
					return new ResourceNotFoundException("Componente desarrollo no encontrado con id: " + id);
				});
		LogSistema log = new LogSistema();
		log.setAccion("UPDATE");
		log.setDescripcion(componente.toString());
		log.setFechaHora(new Date());
		log.setIdAccion(componente.getId());
		log.setTabla(componente.getClass().toString());
		this.logService.saveLog(log);
		componente.setComponente(componenteDetails.getComponente());
		ComponenteDesarrollo updateComponente = this.componenteService.saveComponente(componente);
		return ResponseEntity.ok(updateComponente);
	}

	@GetMapping({ "/componentes/{id}" })
	public ResponseEntity<ComponenteDesarrollo> GetComponenteById(@PathVariable Integer id) {
		ComponenteDesarrollo componente = (ComponenteDesarrollo) this.componenteRespository.findById(id)
				.orElseThrow(() -> {
					return new ResourceNotFoundException("Componente desarrollo no encontrado con id: " + id);
				});
		return ResponseEntity.ok(componente);
	}

	@DeleteMapping({ "/componentes/{id}" })
	public ResponseEntity<?> deleteComponente(@PathVariable Integer id) {
		ComponenteDesarrollo componente = (ComponenteDesarrollo) this.componenteRespository.findById(id)
				.orElseThrow(() -> {
					return new ResourceNotFoundException("Componente desarrollo no encontrado con id: " + id);
				});
		if (this.proyectoRepository.existsByComponente(componente)) {
			return ResponseEntity.badRequest().body("No se puede eliminar el componente, Tiene relacion con Proyecto.");
		} else {
			LogSistema log = new LogSistema();
			log.setAccion("DELETE");
			log.setFechaHora(new Date());
			log.setIdAccion(componente.getId());
			log.setDescripcion(componente.toString());
			log.setTabla(ComponenteDesarrollo.class.toString());
			this.logService.saveLog(log);
			this.componenteRespository.deleteById(componente.getId());
			Map<String, Boolean> response = new HashMap();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
		}
	}
}

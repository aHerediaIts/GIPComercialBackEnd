//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.controller;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.Actividad;
import com.backendgip.model.FaseProyecto;
import com.backendgip.model.LogSistema;
import com.backendgip.model.Proyecto;
import com.backendgip.repository.ActividadAsignadaRepository;
import com.backendgip.repository.ActividadRepository;
import com.backendgip.repository.FaseProyectoRepository;
import com.backendgip.repository.ProyectoRepository;
import com.backendgip.service.FaseProyectoService;
import com.backendgip.service.LogSistemaService;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
public class FaseProyectoController {
	@Autowired
	private FaseProyectoRepository faseRepository;
	@Autowired
	private FaseProyectoService faseService;
	@Autowired
	private ProyectoRepository proyectoReposity;
	@Autowired
	private LogSistemaService logService;
	@Autowired
	private ActividadRepository actividadRepository;
	@Autowired
	private ActividadAsignadaRepository actividadAsigRepository;

	public FaseProyectoController() {
	}

	@GetMapping({ "/fases" })
	public List<FaseProyecto> getFases() {
		return this.faseService.getFases();
	}

	@PostMapping({ "/fases" })
	public ResponseEntity<?> saveFase(@RequestBody FaseProyecto fase) {
		if (this.faseRepository.existsByFase(fase.getFase())) {
			return ResponseEntity.badRequest().body("Fase ya existe");
		} else {
			FaseProyecto createdFaseProyecto = this.faseService.saveFase(fase);
			LogSistema log = new LogSistema();
			log.setAccion("CREATE");
			log.setFechaHora(new Date());
			log.setTabla(FaseProyecto.class.toString());
			log.setIdAccion(createdFaseProyecto.getId());
			log.setDescripcion(createdFaseProyecto.toString());
			this.logService.saveLog(log);
			return ResponseEntity.ok(createdFaseProyecto);
		}
	}

	@GetMapping({ "/fases/{id}" })
	public ResponseEntity<FaseProyecto> getFaseById(@PathVariable Integer id) {
		FaseProyecto fase = (FaseProyecto) this.faseRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("Fase no encontrada con id: " + id);
		});
		return ResponseEntity.ok(fase);
	}

	@PutMapping({ "/fases/{id}" })
	public ResponseEntity<?> udpateFase(@PathVariable Integer id, @RequestBody FaseProyecto faseDetails) {
		FaseProyecto fase = (FaseProyecto) this.faseRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("Fase no encontrada con id: " + id);
		});
		System.out.println(this.faseRepository.existsByFase(faseDetails.getFase()));
		System.out.println(fase);
		System.out.println(faseDetails);
		if (this.faseRepository.existsByFase(faseDetails.getFase())) {
			return ResponseEntity.badRequest().body("Fase ya existe");
		} else {
			LogSistema log = new LogSistema();
			log.setAccion("UPDATE");
			log.setDescripcion(fase.toString());
			log.setFechaHora(new Date());
			log.setIdAccion(fase.getId());
			log.setTabla(fase.getClass().toString());
			this.logService.saveLog(log);
			fase.setFase(faseDetails.getFase());
			FaseProyecto updatedFase = this.faseService.saveFase(fase);
			return ResponseEntity.ok(updatedFase);
		}
	}

	@DeleteMapping({ "/fases/{id}" })
	public ResponseEntity<?> deleteFase(@PathVariable Integer id) {
		FaseProyecto fase = (FaseProyecto) this.faseRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("Fase no encontrada con id: " + id);
		});
		LogSistema log = new LogSistema();
		log.setAccion("DELETE");
		log.setFechaHora(new Date());
		log.setIdAccion(fase.getId());
		log.setDescripcion(fase.toString());
		log.setTabla(FaseProyecto.class.toString());
		this.logService.saveLog(log);
		if (!this.validDeleteFase(fase)) {
			return ResponseEntity.badRequest().body("Fase asignada a actividades.");
		} else {
			this.faseService.deleteFase(fase);
			Map<String, Boolean> response = new HashMap();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
		}
	}

	@GetMapping({ "/fases/proyecto/{id}" })
	public List<FaseProyecto> findByProyectoAndBase(@PathVariable Integer id) {
		Proyecto proyecto = (Proyecto) this.proyectoReposity.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("Proyecto no encontrado con id:" + id);
		});
		return this.faseService.findByProyectoAndBase(proyecto);
	}

	@GetMapping({ "/fases/actividad/proyecto/{idProyecto}" })
	public ResponseEntity<?> findByProyectoActividadAsig(@PathVariable Integer idProyecto) {
		Proyecto proyecto = (Proyecto) this.proyectoReposity.findById(idProyecto).orElseThrow(() -> {
			return new ResourceNotFoundException("Proyecto no encontrado con id:" + idProyecto);
		});
		return ResponseEntity.ok(this.faseService.findByProyectoActividadAsig(proyecto));
	}

	public boolean validDeleteFase(FaseProyecto fase) {
		List<Actividad> actividades = this.actividadRepository.findByFase(fase);
		boolean flag = true;
		Iterator var4 = actividades.iterator();

		while (var4.hasNext()) {
			Actividad a = (Actividad) var4.next();
			if (this.actividadAsigRepository.existsByActividad(a)) {
				flag = false;
			}
		}

		return flag;
	}
}

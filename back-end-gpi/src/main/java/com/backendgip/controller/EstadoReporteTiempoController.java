//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.controller;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.EstadoReporteTiempo;
import com.backendgip.model.LogSistema;
import com.backendgip.repository.EstadoReporteTiempoRepository;
import com.backendgip.service.EstadoReporteTiempoService;
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
@RequestMapping({ "/api/proyectos/reporte-tiempo" })
public class EstadoReporteTiempoController {
	@Autowired
	private EstadoReporteTiempoService estadoReporteTiempoService;
	@Autowired
	private EstadoReporteTiempoRepository estadoReporteTiempoRepository;
	@Autowired
	private LogSistemaService logService;

	public EstadoReporteTiempoController() {
	}

	@GetMapping({ "/estados" })
	public List<EstadoReporteTiempo> getAllEstadoReporteTiempo() {
		return this.estadoReporteTiempoService.getEstadoReporteTiempo();
	}

	@PostMapping({ "/estados" })
	public EstadoReporteTiempo saveEstadoReporteTiempo(@RequestBody EstadoReporteTiempo estadoReporteTiempo) {
		EstadoReporteTiempo createdEstadoReporteTiempo = this.estadoReporteTiempoService
				.saveEstadoReporteTiempo(estadoReporteTiempo);
		LogSistema log = new LogSistema();
		log.setAccion("CREATE");
		log.setFechaHora(new Date());
		log.setTabla(EstadoReporteTiempo.class.toString());
		log.setIdAccion(createdEstadoReporteTiempo.getId());
		log.setDescripcion(createdEstadoReporteTiempo.toString());
		this.logService.saveLog(log);
		return createdEstadoReporteTiempo;
	}

	@PutMapping({ "/estados/{id}" })
	public ResponseEntity<EstadoReporteTiempo> updateEstadoReporteTiempo(@PathVariable Integer id,
			@RequestBody EstadoReporteTiempo estadoReporteTiempoDetails) {
		EstadoReporteTiempo estado = (EstadoReporteTiempo) this.estadoReporteTiempoRepository.findById(id)
				.orElseThrow(() -> {
					return new ResourceNotFoundException(" No existe el Estado de Factura con el id: " + id);
				});
		LogSistema log = new LogSistema();
		log.setAccion("UPDATE");
		log.setDescripcion(estado.toString());
		log.setFechaHora(new Date());
		log.setIdAccion(estado.getId());
		log.setTabla(estado.getClass().toString());
		this.logService.saveLog(log);
		estado.setEstado(estadoReporteTiempoDetails.getEstado());
		EstadoReporteTiempo updatedEstadoReporteTiempo = this.estadoReporteTiempoService
				.saveEstadoReporteTiempo(estado);
		return ResponseEntity.ok(updatedEstadoReporteTiempo);
	}

	@GetMapping({ "/estados/{id}" })
	public ResponseEntity<EstadoReporteTiempo> getEstadoReporteTiempoById(@PathVariable Integer id) {
		EstadoReporteTiempo estadoReporteTiempo = (EstadoReporteTiempo) this.estadoReporteTiempoRepository.findById(id)
				.orElseThrow(() -> {
					return new ResourceNotFoundException(" No existe EstadoReporteTiempo con el id: " + id);
				});
		return ResponseEntity.ok(estadoReporteTiempo);
	}

	@DeleteMapping({ "/estados/{id}" })
	public ResponseEntity<Map<String, Boolean>> deleteEstadoReporteTiempo(@PathVariable Integer id) {
		EstadoReporteTiempo estado = (EstadoReporteTiempo) this.estadoReporteTiempoRepository.findById(id)
				.orElseThrow(() -> {
					return new ResourceNotFoundException("No existe el estadoReporteTiempo con el id: " + id);
				});
		LogSistema log = new LogSistema();
		log.setAccion("DELETE");
		log.setFechaHora(new Date());
		log.setIdAccion(estado.getId());
		log.setDescripcion(estado.toString());
		log.setTabla(EstadoReporteTiempo.class.toString());
		this.logService.saveLog(log);
		this.estadoReporteTiempoRepository.deleteById(estado.getId());
		Map<String, Boolean> response = new HashMap();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}

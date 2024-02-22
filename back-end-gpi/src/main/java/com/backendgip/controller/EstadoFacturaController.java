//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.controller;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.EstadoFactura;
import com.backendgip.model.LogSistema;
import com.backendgip.repository.EstadoFacturaRepository;
import com.backendgip.service.EstadoFacturaService;
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
@RequestMapping({ "/api/proyectos/facturacion" })
public class EstadoFacturaController {
	@Autowired
	private EstadoFacturaService estadoService;
	@Autowired
	private EstadoFacturaRepository estadoRepository;
	@Autowired
	private LogSistemaService logService;

	public EstadoFacturaController() {
	}

	@GetMapping({ "/estados" })
	public List<EstadoFactura> getEstadoFactura() {
		return this.estadoService.getEstadoFactura();
	}

	@PostMapping({ "/estados" })
	public EstadoFactura saveEstadoFactura(@RequestBody EstadoFactura estadoFactura) {
		EstadoFactura createdEstadoFactura = this.estadoService.saveEstadoFactura(estadoFactura);
		LogSistema log = new LogSistema();
		log.setAccion("CREATE");
		log.setFechaHora(new Date());
		log.setTabla(EstadoFactura.class.toString());
		log.setIdAccion(createdEstadoFactura.getId());
		log.setDescripcion(createdEstadoFactura.toString());
		this.logService.saveLog(log);
		return createdEstadoFactura;
	}

	@PutMapping({ "/estados/{id}" })
	public ResponseEntity<EstadoFactura> updateEstadoFactura(@PathVariable Integer id,
			@RequestBody EstadoFactura estadoFacturaDetails) {
		EstadoFactura estado = (EstadoFactura) this.estadoRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException(" No existe el Estado de Factura con el id: " + id);
		});
		LogSistema log = new LogSistema();
		log.setAccion("UPDATE");
		log.setDescripcion(estado.toString());
		log.setFechaHora(new Date());
		log.setIdAccion(estado.getId());
		log.setTabla(estado.getClass().toString());
		this.logService.saveLog(log);
		estado.setEstado(estadoFacturaDetails.getEstado());
		EstadoFactura updatedEstadoFactura = this.estadoService.saveEstadoFactura(estado);
		return ResponseEntity.ok(updatedEstadoFactura);
	}

	@GetMapping({ "/estados/{id}" })
	public ResponseEntity<EstadoFactura> getEstadoFacturaById(@PathVariable Integer id) {
		EstadoFactura estado = (EstadoFactura) this.estadoRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException(" No existe el Estado de Factura con el id: " + id);
		});
		return ResponseEntity.ok(estado);
	}

	@DeleteMapping({ "/estados/{id}" })
	public ResponseEntity<Map<String, Boolean>> deleteEstado(@PathVariable Integer id) {
		EstadoFactura estado = (EstadoFactura) this.estadoRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException(" No existe el Estado Factura con el id: " + id);
		});
		LogSistema log = new LogSistema();
		log.setAccion("DELETE");
		log.setFechaHora(new Date());
		log.setIdAccion(estado.getId());
		log.setDescripcion(estado.toString());
		log.setTabla(EstadoFactura.class.toString());
		this.logService.saveLog(log);
		this.estadoService.deleteEstadoFactura(estado);
		Map<String, Boolean> response = new HashMap();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}

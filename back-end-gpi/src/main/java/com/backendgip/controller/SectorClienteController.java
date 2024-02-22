//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.controller;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.LogSistema;
import com.backendgip.model.SectorCliente;
import com.backendgip.repository.SectorClienteRepository;
import com.backendgip.service.LogSistemaService;
import com.backendgip.service.SectorClienteService;
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
@RequestMapping({ "/api/clientes" })
public class SectorClienteController {
	@Autowired
	private SectorClienteService sectorService;
	@Autowired
	private SectorClienteRepository sectorRepository;
	@Autowired
	private LogSistemaService logService;

	public SectorClienteController() {
	}

	@GetMapping({ "/sectores" })
	public List<SectorCliente> getSectores() {
		return this.sectorService.getSectores();
	}

	@PostMapping({ "/sectores" })
	public SectorCliente saveSector(@RequestBody SectorCliente sector) {
		SectorCliente createdSectorCliente = this.sectorService.saveSector(sector);
		LogSistema log = new LogSistema();
		log.setAccion("CREATE");
		log.setFechaHora(new Date());
		log.setTabla(SectorCliente.class.toString());
		log.setIdAccion(createdSectorCliente.getId());
		log.setDescripcion(createdSectorCliente.toString());
		this.logService.saveLog(log);
		return createdSectorCliente;
	}

	@GetMapping({ "/sectores/{id}" })
	public ResponseEntity<SectorCliente> getSectorById(@PathVariable Integer id) {
		SectorCliente sector = (SectorCliente) this.sectorRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("Sector no encontrado con id:" + id);
		});
		return ResponseEntity.ok(sector);
	}

	@PutMapping({ "/sectores/{id}" })
	public ResponseEntity<SectorCliente> updateSector(@PathVariable Integer id,
			@RequestBody SectorCliente sectorDetails) {
		SectorCliente sector = (SectorCliente) this.sectorRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("Sector no encontrado con id" + id);
		});
		LogSistema log = new LogSistema();
		log.setAccion("UPDATE");
		log.setDescripcion(sector.toString());
		log.setFechaHora(new Date());
		log.setIdAccion(sector.getId());
		log.setTabla(sector.getClass().toString());
		this.logService.saveLog(log);
		sector.setSector(sectorDetails.getSector());
		SectorCliente updatedSector = this.sectorService.saveSector(sector);
		return ResponseEntity.ok(updatedSector);
	}

	@DeleteMapping({ "/sectores/{id}" })
	public ResponseEntity<Map<String, Boolean>> deleteSector(@PathVariable Integer id) {
		SectorCliente sector = (SectorCliente) this.sectorRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("Sector no encontrado con id" + id);
		});
		LogSistema log = new LogSistema();
		log.setAccion("DELETE");
		log.setFechaHora(new Date());
		log.setIdAccion(sector.getId());
		log.setDescripcion(sector.toString());
		log.setTabla(SectorCliente.class.toString());
		this.logService.saveLog(log);
		this.sectorService.deleteSector(sector);
		Map<String, Boolean> response = new HashMap();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}

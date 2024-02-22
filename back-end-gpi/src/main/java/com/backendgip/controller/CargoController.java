//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.controller;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.Cargo;
import com.backendgip.model.LogSistema;
import com.backendgip.repository.CargoRepository;
import com.backendgip.repository.EmpleadoRepository;
import com.backendgip.service.CargoService;
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
@RequestMapping({ "/api/empleados" })
public class CargoController {
	@Autowired
	private CargoService cargoService;
	@Autowired
	private CargoRepository cargoRepository;
	@Autowired
	private LogSistemaService logService;
	@Autowired
	private EmpleadoRepository empleadoRepository;

	public CargoController() {
	}

	@GetMapping({ "/cargos" })
	public List<Cargo> getCargos() {
		return this.cargoService.getCargo();
	}

	@PostMapping({ "/cargos" })
	public ResponseEntity<?> saveCargo(@RequestBody Cargo cargo) {
		if (this.cargoRepository.existsByCargo(cargo.getCargo())) {
			return ResponseEntity.badRequest().body("Cargo ya existe");
		} else {
			Cargo createdCargo = this.cargoService.saveCargo(cargo);
			LogSistema log = new LogSistema();
			log.setAccion("CREATE");
			log.setFechaHora(new Date());
			log.setTabla(Cargo.class.toString());
			log.setIdAccion(createdCargo.getId());
			log.setDescripcion(createdCargo.toString());
			this.logService.saveLog(log);
			return ResponseEntity.ok(createdCargo);
		}
	}

	@PutMapping({ "/cargos/{id}" })
	public ResponseEntity<Cargo> updateCargo(@PathVariable Integer id, @RequestBody Cargo cargoDetails) {
		Cargo cargo = (Cargo) this.cargoRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado el recuso solicitado" + id);
		});
		LogSistema log = new LogSistema();
		log.setAccion("UPDATE");
		log.setDescripcion(cargo.toString());
		log.setFechaHora(new Date());
		log.setIdAccion(cargo.getId());
		log.setTabla(cargo.getClass().toString());
		this.logService.saveLog(log);
		cargo.setCargo(cargoDetails.getCargo());
		Cargo updateCargo = (Cargo) this.cargoRepository.save(cargo);
		return ResponseEntity.ok(updateCargo);
	}

	@GetMapping({ "/cargos/{id}" })
	public ResponseEntity<Cargo> getCargoById(@PathVariable Integer id) {
		Cargo cargo = (Cargo) this.cargoRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado el recurso solicitado" + id);
		});
		return ResponseEntity.ok(cargo);
	}

	@DeleteMapping({ "/cargos/{id}" })
	public ResponseEntity<?> deleteCargo(@PathVariable Integer id) {
		Cargo cargo = (Cargo) this.cargoRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado el recurso solicitado" + id);
		});
		if (this.empleadoRepository.existsByCargo(cargo)) {
			return ResponseEntity.badRequest().body("No se puede eliminar cargo, Esta relacionado con Empleado");
		} else {
			LogSistema log = new LogSistema();
			log.setAccion("DELETE");
			log.setFechaHora(new Date());
			log.setIdAccion(cargo.getId());
			log.setDescripcion(cargo.toString());
			log.setTabla(Cargo.class.toString());
			this.logService.saveLog(log);
			this.cargoRepository.deleteById(cargo.getId());
			Map<String, Boolean> response = new HashMap();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
		}
	}
}

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.controller;

import com.backendgip.exception.ResourceAlreadyExistsException;
import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.Cliente;
import com.backendgip.model.Empleado;
import com.backendgip.model.EstadoCliente;
import com.backendgip.model.LogSistema;
import com.backendgip.model.SectorCliente;
import com.backendgip.repository.ClienteRepository;
import com.backendgip.repository.ProyectoRepository;
import com.backendgip.service.ClienteService;
import com.backendgip.service.EmpleadoService;
import com.backendgip.service.EstadoClienteService;
import com.backendgip.service.LogSistemaService;
import com.backendgip.service.SectorClienteService;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
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
@RequestMapping({ "/api" })
public class ClienteController {
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EstadoClienteService estadoService;
	@Autowired
	private SectorClienteService sectorService;
	@Autowired
	private EmpleadoService empleadoService;
	@Autowired
	private LogSistemaService logService;
	@Autowired
	private ProyectoRepository proyectoRepository;

	public ClienteController() {
	}

	@GetMapping({ "/clientes" })
	public List<Cliente> getClientes() {
		return this.clienteService.getClientes();
	}

	@PostMapping({ "/clientes" })
	public ResponseEntity<?> saveCliente(@RequestBody Cliente cliente) {
		if (this.clienteRepository.existsByNomenclatura(cliente.getNomenclatura())) {
			return ResponseEntity.badRequest().body("Nomenclatura existente");
		} else {
			LocalDate fechaCreacion = LocalDate.now(ZoneId.of("America/Bogota"));
			EstadoCliente estado = this.estadoService.getEstadoById(1);
			cliente.setFechaCreacion(fechaCreacion);
			cliente.setEstado(estado);
			Cliente createdCliente = this.clienteService.saveCliente(cliente);
			LogSistema log = new LogSistema();
			log.setAccion("CREATE");
			log.setFechaHora(new Date(Calendar.getInstance().getTime().getTime()));
			log.setTabla(Cliente.class.toString());
			log.setIdAccion(createdCliente.getId());
			log.setDescripcion(createdCliente.toString());
			this.logService.saveLog(log);
			return ResponseEntity.ok(createdCliente);
		}
	}

	@PutMapping({ "/clientes/{id}" })
	public ResponseEntity<?> updateCliente(@PathVariable Integer id, @RequestBody Cliente clienteDetails) {
		Cliente cliente = (Cliente) this.clienteRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("Cliente no existe con id: " + id);
		});
		if (this.clienteRepository.existsByNomenclatura(clienteDetails.getNomenclatura())
				&& cliente.getId() != clienteDetails.getId()) {
			return ResponseEntity.badRequest().body(new ResourceAlreadyExistsException("Nomenclatura existente"));
		} else {
			LogSistema log = new LogSistema();
			log.setAccion("UPDATE");
			log.setDescripcion(cliente.toString());
			log.setFechaHora(new Date(Calendar.getInstance().getTime().getTime()));
			log.setIdAccion(cliente.getId());
			log.setTabla(cliente.getClass().toString());
			this.logService.saveLog(log);
			cliente.setNit(clienteDetails.getNit());
			cliente.setNombre(clienteDetails.getNombre());
			cliente.setNomenclatura(clienteDetails.getNomenclatura());
			cliente.setFechaCreacion(clienteDetails.getFechaCreacion());
			EstadoCliente estado = this.estadoService.getEstadoById(clienteDetails.getEstado().getId());
			cliente.setEstado(estado);
			SectorCliente sector = this.sectorService.getSectorById(clienteDetails.getSector().getId());
			cliente.setSector(sector);
			Empleado director = this.empleadoService.getEmpleadoById(clienteDetails.getGerenteCuenta().getId());
			cliente.setGerenteCuenta(director);
			Cliente updatedCliente = this.clienteService.saveCliente(cliente);
			return ResponseEntity.ok(updatedCliente);
		}
	}

	@GetMapping({ "/clientes/{id}" })
	public ResponseEntity<Cliente> getClienteById(@PathVariable Integer id) {
		Cliente cliente = (Cliente) this.clienteRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("ID " + id + " NO ENCONTRADO");
		});
		return ResponseEntity.ok(cliente);
	}

	@DeleteMapping({ "/clientes/{id}" })
	public ResponseEntity<?> deleteCliente(@PathVariable Integer id) {
		Cliente cliente = (Cliente) this.clienteRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("Cliente no exite con el id:" + id);
		});
		if (this.proyectoRepository.existsByCliente(cliente)) {
			return ResponseEntity.badRequest()
					.body("Cliente no se puede eliminar, Ya se encuentra asignado a uno o mas proyectos.");
		} else {
			LogSistema log = new LogSistema();
			log.setAccion("DELETE");
			log.setFechaHora(new Date(Calendar.getInstance().getTime().getTime()));
			log.setIdAccion(cliente.getId());
			log.setDescripcion(cliente.toString());
			log.setTabla(Cliente.class.toString());
			this.logService.saveLog(log);
			this.clienteService.deleteCliente(cliente);
			Map<String, Boolean> response = new HashMap();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
		}
	}

	@GetMapping({ "/clientes/reportes/find-by-sector/{idSector}" })
	public ResponseEntity<?> findBySector(@PathVariable Integer idSector) {
		SectorCliente sector = this.sectorService.getSectorById(idSector);
		return ResponseEntity.ok(this.clienteService.findBySector(sector));
	}

	@GetMapping({ "/clientes/reportes/find-by-gerente/{idGerente}" })
	public ResponseEntity<?> findByGerenteCuenta(@PathVariable Integer idGerente) {
		Empleado gerente = this.empleadoService.getEmpleadoById(idGerente);
		return ResponseEntity.ok(this.clienteService.findByGerenteCuenta(gerente));
	}

	@GetMapping({ "/clientes/reportes/find-by-sector-gerente/{idSector}/{idGerente}" })
	public ResponseEntity<?> findBySectorAndGerenteCuenta(@PathVariable Integer idSector,
			@PathVariable Integer idGerente) {
		SectorCliente sector = this.sectorService.getSectorById(idSector);
		Empleado gerente = this.empleadoService.getEmpleadoById(idGerente);
		return ResponseEntity.ok(this.clienteService.findBySectorAndGerenteCuenta(sector, gerente));
	}

	@GetMapping({ "/clientes/reportes/find-by-fechas/{fechaInicio}/{fechaFin}" })
	public ResponseEntity<?> findByBeetweenFechas(@PathVariable LocalDate fechaInicio,
			@PathVariable LocalDate fechaFin) {
		return ResponseEntity.ok(this.clienteService.findByBeetweenFechas(fechaInicio, fechaFin));
	}
}

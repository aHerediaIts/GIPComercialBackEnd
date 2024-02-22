//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.controller;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.EmpleadoRol;
import com.backendgip.model.EstadoFactura;
import com.backendgip.model.Facturacion;
import com.backendgip.model.LogSistema;
import com.backendgip.model.Proyecto;
import com.backendgip.repository.FacturacionRepository;
import com.backendgip.service.EmpleadoRolService;
import com.backendgip.service.EstadoFacturaService;
import com.backendgip.service.FacturacionService;
import com.backendgip.service.LogSistemaService;
import com.backendgip.service.MailService;
import com.backendgip.service.ProyectoService;
import com.backendgip.service.RolService;
import java.time.LocalDate;
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
public class FacturacionController {
	@Autowired
	private FacturacionService facturacionService;
	@Autowired
	private FacturacionRepository facturacionRepository;
	@Autowired
	private ProyectoService proyectoService;
	@Autowired
	private EstadoFacturaService estadoService;
	@Autowired
	private LogSistemaService logService;
	@Autowired
	private MailService mailService;
	@Autowired
	private RolService rolService;
	@Autowired
	private EmpleadoRolService empleadoRolService;

	public FacturacionController() {
	}

	@GetMapping({ "/facturacion" })
	public List<Facturacion> getAllFacturacion() {
		return this.facturacionService.getFacturacion();
	}

	@PostMapping({ "/facturacion/cobrar" })
	public ResponseEntity<?> cobrar(@RequestBody Facturacion facturacion) {
		if (this.validDatePlaneada(facturacion) != null) {
			return this.validDatePlaneada(facturacion);
		} else if (this.validCienPorciento(facturacion) != null) {
			return this.validCienPorciento(facturacion);
		} else {
			facturacion.setEstado(this.estadoService.getEstadoFacturaById(2));
			facturacion.setFechaPago((LocalDate) null);
			facturacion.setValorPagado((Integer) null);
			Facturacion createdFacturacion = this.facturacionService.saveFacturacion(facturacion);
			LogSistema log = new LogSistema();
			log.setAccion("CREATE");
			log.setFechaHora(new Date());
			log.setTabla(Facturacion.class.toString());
			log.setIdAccion(createdFacturacion.getId());
			log.setDescripcion(createdFacturacion.toString());
			this.logService.saveLog(log);
			return ResponseEntity.ok(createdFacturacion);
		}
	}

	@PutMapping({ "/facturacion/pagar" })
	public ResponseEntity<?> pagar(@RequestBody Facturacion facturacion) {
		if (this.validDatePago(facturacion) != null) {
			return this.validDatePago(facturacion);
		} else {
			facturacion.setEstado(this.estadoService.getEstadoFacturaById(1));
			Facturacion updatedFacturacion = this.facturacionService.saveFacturacion(facturacion);
			LogSistema log = new LogSistema();
			log.setAccion("CREATE");
			log.setFechaHora(new Date());
			log.setTabla(Facturacion.class.toString());
			log.setIdAccion(updatedFacturacion.getId());
			log.setDescripcion(updatedFacturacion.toString());
			this.logService.saveLog(log);
			if (facturacion.getProyecto().getLider() != null) {
				this.mailService.sendSimpleMail(facturacion.getProyecto().getLider().getEmail(),
						"CAMBIO ESTADO A FACTURADO ",
						"Buen dia.<br><br>En el proyecto " + facturacion.getProyecto().getDescripcion()
								+ " se ha cambiado el estado de un cobro a " + facturacion.getEstado().getEstado()
								+ " Por favor revisar.<br><br><br>Gerencia De Proyectos Y Servicios.<br>Its Solution SAS.");
			}

			List<EmpleadoRol> LGerentes = this.empleadoRolService.findByRol(this.rolService.findById(4));

			for (int i = 0; i < LGerentes.size(); ++i) {
				this.mailService.sendSimpleMail(((EmpleadoRol) LGerentes.get(i)).getEmpleado().getEmail(),
						"CAMBIO ESTADO A FACTURADO ",
						"Buen dia.<br><br>En el proyecto " + facturacion.getProyecto().getDescripcion()
								+ " se ha cambiado el estado de un cobro a " + facturacion.getEstado().getEstado()
								+ " Por favor revisar.<br><br><br>Gerencia De Proyectos Y Servicios.<br>Its Solution SAS.");
			}

			return ResponseEntity.ok(updatedFacturacion);
		}
	}

	@PostMapping({ "/facturacion" })
	public ResponseEntity<?> saveFacturacion(@RequestBody Facturacion facturacion) {
		if (this.validDatePlaneada(facturacion) != null) {
			return this.validDatePlaneada(facturacion);
		} else {
			Facturacion createdFacturacion = this.facturacionService.saveFacturacion(facturacion);
			LogSistema log = new LogSistema();
			log.setAccion("CREATE");
			log.setFechaHora(new Date());
			log.setTabla(Facturacion.class.toString());
			log.setIdAccion(createdFacturacion.getId());
			log.setDescripcion(createdFacturacion.toString());
			this.logService.saveLog(log);
			return ResponseEntity.ok(createdFacturacion);
		}
	}

	@PutMapping({ "/facturacion/{id}" })
	public ResponseEntity<?> updateFacturacion(@PathVariable Integer id, @RequestBody Facturacion facturacionDetails) {
		Facturacion facturacion = (Facturacion) this.facturacionRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException(" No existe Facturacion con el id: " + id);
		});
		if (this.validDatePago(facturacionDetails) != null) {
			return this.validDatePago(facturacionDetails);
		} else {
			LogSistema log = new LogSistema();
			log.setAccion("UPDATE");
			log.setDescripcion(facturacion.toString());
			log.setFechaHora(new Date());
			log.setIdAccion(facturacion.getId());
			log.setTabla(facturacion.getClass().toString());
			this.logService.saveLog(log);
			facturacion.setPorcentaje(facturacionDetails.getPorcentaje());
			facturacion.setFechaPlaneada(facturacionDetails.getFechaPlaneada());
			facturacion.setFechaPago(facturacionDetails.getFechaPago());
			Proyecto proyecto = this.proyectoService.getProyectoById(facturacionDetails.getProyecto().getId());
			facturacion.setProyecto(proyecto);
			EstadoFactura estado = this.estadoService.getEstadoFacturaById(facturacionDetails.getEstado().getId());
			facturacion.setEstado(estado);
			Facturacion updatedFacturacion = this.facturacionService.saveFacturacion(facturacionDetails);
			if (facturacion.getProyecto().getLider() != null) {
				this.mailService.sendSimpleMail(facturacion.getProyecto().getLider().getEmail(),
						"CAMBIO ESTADO A FACTURADO ",
						"Buen dia.<br><br>En el proyecto " + facturacion.getProyecto().getDescripcion()
								+ " se ha cambiado el estado de un cobro a " + facturacion.getEstado().getEstado()
								+ " Por favor revisar.<br><br><br>Gerencia De Proyectos Y Servicios.<br>Its Solution SAS.");
			}

			List<EmpleadoRol> LGerentes = this.empleadoRolService.findByRol(this.rolService.findById(4));

			for (int i = 0; i < LGerentes.size(); ++i) {
				this.mailService.sendSimpleMail(((EmpleadoRol) LGerentes.get(i)).getEmpleado().getEmail(),
						"CAMBIO ESTADO A FACTURADO ",
						"Buen dia.<br><br>En el proyecto " + facturacion.getProyecto().getDescripcion()
								+ " se ha cambiado el estado de un cobro a " + facturacion.getEstado().getEstado()
								+ " Por favor revisar.<br><br><br>Gerencia De Proyectos Y Servicios.<br>Its Solution SAS.");
			}

			return ResponseEntity.ok(updatedFacturacion);
		}
	}

	@GetMapping({ "/facturacion/{id}" })
	public ResponseEntity<Facturacion> getFacturacionById(@PathVariable Integer id) {
		Facturacion facturacion = (Facturacion) this.facturacionRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException(" No existe Facturacion con el id: " + id);
		});
		return ResponseEntity.ok(facturacion);
	}

	@DeleteMapping({ "/facturacion/{id}" })
	public ResponseEntity<?> deleteFacturacion(@PathVariable Integer id) {
		Facturacion facturacion = (Facturacion) this.facturacionRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("No existe la Facturacion con el id: " + id);
		});
		if (facturacion.getEstado().getId() == 1) {
			return ResponseEntity.badRequest().body("No se puede eliminar esta facturacion, Estado facturado.");
		} else {
			LogSistema log = new LogSistema();
			log.setAccion("DELETE");
			log.setFechaHora(new Date());
			log.setIdAccion(facturacion.getId());
			log.setDescripcion(facturacion.toString());
			log.setTabla(Facturacion.class.toString());
			this.logService.saveLog(log);
			this.facturacionRepository.deleteById(facturacion.getId());
			Map<String, Boolean> response = new HashMap();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
		}
	}

	@GetMapping({ "/facturacion/proyectos/{id}" })
	public List<Facturacion> getCobrosByProyecto(@PathVariable Integer id) {
		return this.facturacionService.getCobrosByProyecto(this.proyectoService.getProyectoById(id));
	}

	public ResponseEntity<?> validDatePlaneada(Facturacion facturacion) {
		return facturacion.getFechaPlaneada().isBefore(facturacion.getProyecto().getFechaAprobacion())
				? ResponseEntity.badRequest().body("La Fecha Planeada debe ser mayor o igual a la Fecha Aprobación.")
				: null;
	}

	public ResponseEntity<?> validDatePago(Facturacion facturacion) {
		return facturacion.getFechaPago().isBefore(facturacion.getFechaPlaneada())
				? ResponseEntity.badRequest().body("La Fecha de Pago debe ser mayor o igual a la Fecha Planeada.")
				: null;
	}

	public ResponseEntity<?> validCienPorciento(Facturacion facturacion) {
		List<Facturacion> facturaciones = this.facturacionService.getCobrosByProyecto(facturacion.getProyecto());
		int total = 0;

		Facturacion f;
		for (Iterator var4 = facturaciones.iterator(); var4.hasNext(); total += f.getPorcentaje()) {
			f = (Facturacion) var4.next();
		}

		total += facturacion.getPorcentaje();
		return total > 100
				? ResponseEntity.badRequest()
						.body("'Cobro no registrado, el porcentaje de los pagos no debe ser mayor a 100%")
				: null;
	}
}

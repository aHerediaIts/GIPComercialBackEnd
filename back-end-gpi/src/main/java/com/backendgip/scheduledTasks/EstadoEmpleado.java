//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.scheduledTasks;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.Empleado;
import com.backendgip.model.LogSistema;
import com.backendgip.model.Novedad;
import com.backendgip.repository.EmpleadoRepository;
import com.backendgip.service.EmpleadoService;
import com.backendgip.service.EstadoEmpleadoService;
import com.backendgip.service.LogSistemaService;
import com.backendgip.service.NovedadService;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EstadoEmpleado {
	@Autowired
	private NovedadService novedadService;
	@Autowired
	private EstadoEmpleadoService estadoService;
	@Autowired
	private EmpleadoRepository empleadoRepository;
	@Autowired
	private EmpleadoService empleadoService;
	@Autowired
	private LogSistemaService logService;

	public EstadoEmpleado() {
	}

	@Scheduled(cron = "0 0 12 1/1 * ?")
	public void updateEstado() {
		List<Novedad> novedades = this.getNovedad();

		for (int i = 0; i < novedades.size(); ++i) {
			if (((Novedad) novedades.get(i)).getFechaFin().isBefore(LocalDate.now())) {
				this.update(((Novedad) novedades.get(i)).getEmpleado().getId());
			}
		}

	}

	public List<Novedad> getNovedad() {
		return this.novedadService.findAll();
	}

	public Empleado update(Integer id) {
		Empleado empleado = (Empleado) this.empleadoRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado el recurso solicitado" + id);
		});
		LogSistema log = new LogSistema();
		log.setAccion("UPDATE");
		log.setDescripcion(empleado.toString());
		log.setFechaHora(new Date());
		log.setIdAccion(empleado.getId());
		log.setTabla(empleado.getClass().toString());
		this.logService.saveLog(log);
		com.backendgip.model.EstadoEmpleado estado = this.estadoService.getEstadoById(1);
		empleado.setEstado(estado);
		Empleado updateEmpleado = this.empleadoService.saveEmpleado(empleado);
		return updateEmpleado;
	}

	public Date convertToDateViaInstant(LocalDate dateToConvert) {
		return Date.from(dateToConvert.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}
}

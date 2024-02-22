//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.imp;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.ActividadAsignada;
import com.backendgip.model.Empleado;
import com.backendgip.model.Novedad;
import com.backendgip.model.RecursoActividad;
import com.backendgip.repository.NovedadRepository;
import com.backendgip.repository.RecursoActividadRepository;
import com.backendgip.service.EmpleadoService;
import com.backendgip.service.RecursoActividadService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecursoActividadServImp implements RecursoActividadService {
	@Autowired
	private RecursoActividadRepository recursoRepository;
	@Autowired
	private EmpleadoService empleadoService;
	@Autowired
	private RecursoActividadService recursoActService;
	@Autowired
	private NovedadRepository novedadRepository;

	public RecursoActividadServImp() {
	}

	public List<RecursoActividad> getRecursoActividad() {
		return (List) this.recursoRepository.findAll();
	}

	public RecursoActividad saveRecursoActividad(RecursoActividad recursoActividad) {
		return (RecursoActividad) this.recursoRepository.save(recursoActividad);
	}

	public void deleteRecursoActividad(RecursoActividad recursoActividad) {
		this.recursoRepository.delete(recursoActividad);
	}

	public RecursoActividad getRecursoActividadById(Integer id) {
		return (RecursoActividad) this.recursoRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado el recurso actividad solicitado con id:" + id);
		});
	}

	public Boolean existsByEmpleado(Empleado empleado) {
		return this.recursoRepository.existsByEmpleado(empleado);
	}

	public Boolean existsByEmpleadoAndFecha(Empleado empleado, LocalDate fecha) {
		return this.recursoRepository.existsByEmpleadoAndFecha(empleado, fecha);
	}

	public List<RecursoActividad> findByActividad(ActividadAsignada actividad) {
		return this.recursoRepository.findByActividad(actividad);
	}

	public List<RecursoActividad> findByEmpleado(Empleado empleado) {
		return this.recursoRepository.findByEmpleado(empleado);
	}

	public List<RecursoActividad> findByEmpleadoAndActividad(Empleado empleado, ActividadAsignada actividad) {
		return this.recursoRepository.findByEmpleadoAndActividad(empleado, actividad);
	}

	public Boolean existsByActividad(ActividadAsignada actividad) {
		return this.recursoRepository.existsByActividad(actividad);
	}

	public Boolean existsByActividadAndFecha(ActividadAsignada actividad, LocalDate fecha) {
		return this.recursoRepository.existsByActividadAndFecha(actividad, fecha);
	}

	public List<RecursoActividad> findByEmpleadoAndActividadNewDates(Empleado empleado, ActividadAsignada actividad) {
		List<LocalDate> fechas = this.empleadoService.getFechasBetween(actividad.getFechaInicio(),
				actividad.getFechaFin());
		List<RecursoActividad> recursos = new ArrayList();
		Iterator var5 = fechas.iterator();

		while (var5.hasNext()) {
			LocalDate f = (LocalDate) var5.next();
			RecursoActividad recurso = new RecursoActividad();
			recurso.setEmpleado(empleado);
			recurso.setFecha(f);
			recurso.setActividad(actividad);
			recursos.add(recurso);
		}

		return recursos;
	}

	public boolean existsFechaVsNovedadesEmpleado(Empleado empleado, LocalDate fecha) {
		List<Novedad> novedades = this.novedadRepository.findByEmpleado(empleado);
		Iterator var4 = novedades.iterator();

		Novedad n;
		do {
			if (!var4.hasNext()) {
				return false;
			}

			n = (Novedad) var4.next();
		} while ((!fecha.isAfter(n.getFechaInicio()) || !fecha.isBefore(n.getFechaFin()))
				&& (!fecha.isAfter(n.getFechaInicio()) || !fecha.isEqual(n.getFechaFin())));

		return true;
	}
}

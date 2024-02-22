//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.imp;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.Actividad;
import com.backendgip.model.ActividadAsignada;
import com.backendgip.model.FaseProyecto;
import com.backendgip.model.Proyecto;
import com.backendgip.repository.ActividadAsignadaRepository;
import com.backendgip.service.ActividadAsignadaService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActividadAsignadaServImp implements ActividadAsignadaService {
	@Autowired
	private ActividadAsignadaRepository actividadRepository;

	public ActividadAsignadaServImp() {
	}

	public List<ActividadAsignada> getActividades() {
		return (List) this.actividadRepository.findAll();
	}

	public ActividadAsignada saveActividad(ActividadAsignada actividad) {
		return (ActividadAsignada) this.actividadRepository.save(actividad);
	}

	public void deleteActividad(ActividadAsignada actividad) {
		this.actividadRepository.delete(actividad);
	}

	public ActividadAsignada getActividadById(Integer idActividad) {
		return (ActividadAsignada) this.actividadRepository.findById(idActividad).orElseThrow(() -> {
			return new ResourceNotFoundException("NO se ha encontrado la actividad con id:" + idActividad);
		});
	}

	public List<ActividadAsignada> findByProyecto(Proyecto proyecto) {
		return this.actividadRepository.findByProyecto(proyecto);
	}

	public List<ActividadAsignada> findByFase(FaseProyecto fase, Proyecto proyecto) {
		List<ActividadAsignada> byProyecto = this.actividadRepository.findByProyecto(proyecto);
		List<ActividadAsignada> actividades = new ArrayList();
		Iterator var5 = byProyecto.iterator();

		while (var5.hasNext()) {
			ActividadAsignada a = (ActividadAsignada) var5.next();
			if (a.getActividad().getFase().getId() == fase.getId()) {
				actividades.add(a);
			}
		}

		return actividades;
	}

	public Boolean existsByActividad(Actividad actividad) {
		return this.actividadRepository.existsByActividad(actividad);
	}

	public List<ActividadAsignada> findByActividad(Actividad actividad) {
		return this.actividadRepository.findByActividad(actividad);
	}

	public LocalDate getFechaFinMax(Proyecto proyecto, ActividadAsignada actividad) {
		List<ActividadAsignada> actividadesByProyect = this.actividadRepository.findByProyecto(proyecto);
		LocalDate maxDate = null;
		if (actividadesByProyect.size() > 0) {
			maxDate = ((ActividadAsignada) actividadesByProyect.get(0)).getFechaFin();
		} else if (actividadesByProyect.size() == 0) {
			maxDate = actividad.getFechaFin();
		}

		System.out.println("Actividad " + actividad.getFechaFin());
		System.out.println("MaxDate " + maxDate);
		System.out.println("Act proy" + actividadesByProyect);
		Iterator var5 = actividadesByProyect.iterator();

		while (var5.hasNext()) {
			ActividadAsignada a = (ActividadAsignada) var5.next();
			if (a.getFechaFin().isAfter(maxDate)) {
				maxDate = a.getFechaFin();
			}
		}

		if (actividad.getFechaFin().isAfter(maxDate)) {
			maxDate = actividad.getFechaFin();
		}

		return maxDate;
	}
}

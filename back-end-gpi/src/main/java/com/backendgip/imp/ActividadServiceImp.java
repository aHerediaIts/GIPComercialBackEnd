//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.imp;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.Actividad;
import com.backendgip.model.FaseProyecto;
import com.backendgip.model.Proyecto;
import com.backendgip.repository.ActividadRepository;
import com.backendgip.repository.ProyectoRepository;
import com.backendgip.service.ActividadService;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActividadServiceImp implements ActividadService {
	@Autowired
	private ActividadRepository actividadRepository;
	@Autowired
	private ProyectoRepository proyectoRepository;

	public ActividadServiceImp() {
	}

	public List<Actividad> getActividades() {
		return (List) this.actividadRepository.findAll();
	}

	public Actividad saveActividad(Actividad actividad) {
		return (Actividad) this.actividadRepository.save(actividad);
	}

	public void deleteActividad(Actividad actividad) {
		this.actividadRepository.delete(actividad);
	}

	public Actividad getActividadById(Integer id) {
		return (Actividad) this.actividadRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("Actividad no encontrada con id:" + id);
		});
	}

	public List<Actividad> findByProyecto(Integer id) {
		Proyecto proyecto = (Proyecto) this.proyectoRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("No se encontro proyecto con id:" + id);
		});
		if (this.actividadRepository.findByProyecto(proyecto).isEmpty()) {
			System.out.println("NO HAY ACTIVIDADES DE ESTE PROYECTO, SOLO BASES");
			return this.actividadRepository.findByBase(true);
		} else {
			List<Actividad> bases = this.actividadRepository.findByBase(true);
			List<Actividad> actividades = this.actividadRepository.findByProyecto(proyecto);
			Iterator var5 = actividades.iterator();

			while (var5.hasNext()) {
				Actividad a = (Actividad) var5.next();
				bases.add(a);
			}

			System.out.println("SI HAY ACTIVIDADES DE ESTE PROYECTO");
			return bases;
		}
	}

	public List<Actividad> findByProyectoAndFase(Proyecto proyecto, FaseProyecto fase) {
		return this.actividadRepository.findByProyectoAndFase(proyecto, fase);
	}

	public List<Actividad> findForPlaneacion(Proyecto proyecto, FaseProyecto fase) {
		List<Actividad> bases = this.actividadRepository.findByFaseAndBase(fase, true);
		List<Actividad> byProyecto = this.actividadRepository.findByProyectoAndFase(proyecto, fase);
		if (!byProyecto.isEmpty()) {
			Iterator var5 = byProyecto.iterator();

			while (var5.hasNext()) {
				Actividad a = (Actividad) var5.next();
				bases.add(a);
			}
		}

		return bases;
	}

	public List<Actividad> findByFase(FaseProyecto fase) {
		return this.actividadRepository.findByFase(fase);
	}
}

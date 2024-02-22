//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.imp;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.ActividadAsignada;
import com.backendgip.model.FaseProyecto;
import com.backendgip.model.Proyecto;
import com.backendgip.repository.FaseProyectoRepository;
import com.backendgip.service.ActividadAsignadaService;
import com.backendgip.service.FaseProyectoService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FaseProyectoServImp implements FaseProyectoService {
	@Autowired
	private FaseProyectoRepository faseRepository;
	@Autowired
	private ActividadAsignadaService actividadAsigService;

	public FaseProyectoServImp() {
	}

	public List<FaseProyecto> getFases() {
		return (List) this.faseRepository.findAll();
	}

	public FaseProyecto saveFase(FaseProyecto fase) {
		return (FaseProyecto) this.faseRepository.save(fase);
	}

	public void deleteFase(FaseProyecto fase) {
		this.faseRepository.delete(fase);
	}

	public FaseProyecto getFaseById(Integer idFase) {
		return (FaseProyecto) this.faseRepository.findById(idFase).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado la fase con id:" + idFase);
		});
	}

	public List<FaseProyecto> findByProyecto(Proyecto proyecto) {
		return this.faseRepository.findByProyecto(proyecto);
	}

	public List<FaseProyecto> findByProyectoAndBase(Proyecto proyecto) {
		List<FaseProyecto> bases = this.faseRepository.findByBase(true);
		List<FaseProyecto> fases = this.faseRepository.findByProyecto(proyecto);
		if (!fases.isEmpty()) {
			Iterator var4 = fases.iterator();

			while (var4.hasNext()) {
				FaseProyecto f = (FaseProyecto) var4.next();
				bases.add(f);
			}
		}

		return bases;
	}

	public List<FaseProyecto> findByProyectoActividadAsig(Proyecto proyecto) {
		List<ActividadAsignada> actividades = this.actividadAsigService.findByProyecto(proyecto);
		List<FaseProyecto> fases = new ArrayList();
		Iterator var4 = actividades.iterator();

		while (var4.hasNext()) {
			ActividadAsignada a = (ActividadAsignada) var4.next();
			if (!fases.contains(a.getActividad().getFase())) {
				fases.add(a.getActividad().getFase());
			}
		}

		return fases;
	}
}

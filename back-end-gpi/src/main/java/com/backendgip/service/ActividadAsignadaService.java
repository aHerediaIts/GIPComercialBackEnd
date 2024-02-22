//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.service;

import com.backendgip.model.Actividad;
import com.backendgip.model.ActividadAsignada;
import com.backendgip.model.FaseProyecto;
import com.backendgip.model.Proyecto;
import java.time.LocalDate;
import java.util.List;

public interface ActividadAsignadaService {
	List<ActividadAsignada> getActividades();

	ActividadAsignada saveActividad(ActividadAsignada actividad);

	void deleteActividad(ActividadAsignada actividad);

	ActividadAsignada getActividadById(Integer idActividad);

	List<ActividadAsignada> findByProyecto(Proyecto proyecto);

	List<ActividadAsignada> findByFase(FaseProyecto fase, Proyecto proyecto);

	Boolean existsByActividad(Actividad actividad);

	List<ActividadAsignada> findByActividad(Actividad actividad);

	LocalDate getFechaFinMax(Proyecto proyecto, ActividadAsignada actividad);
}

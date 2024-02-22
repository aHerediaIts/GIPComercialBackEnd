//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.service;

import com.backendgip.model.Actividad;
import com.backendgip.model.FaseProyecto;
import com.backendgip.model.Proyecto;
import java.util.List;

public interface ActividadService {
	List<Actividad> getActividades();

	Actividad saveActividad(Actividad actividad);

	void deleteActividad(Actividad actividad);

	Actividad getActividadById(Integer id);

	List<Actividad> findByProyecto(Integer id);

	List<Actividad> findByProyectoAndFase(Proyecto proyecto, FaseProyecto fase);

	List<Actividad> findForPlaneacion(Proyecto proyecto, FaseProyecto fase);

	List<Actividad> findByFase(FaseProyecto fase);
}

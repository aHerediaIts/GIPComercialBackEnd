//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.repository;

import com.backendgip.model.Actividad;
import com.backendgip.model.ActividadAsignada;
import com.backendgip.model.Proyecto;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActividadAsignadaRepository extends CrudRepository<ActividadAsignada, Integer> {
	boolean existsByProyecto(Proyecto proyecto);

	List<ActividadAsignada> findByProyecto(Proyecto proyecto);

	Boolean existsByActividad(Actividad actividad);

	List<ActividadAsignada> findByActividad(Actividad actividad);
}

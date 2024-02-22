//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.repository;

import com.backendgip.model.Actividad;
import com.backendgip.model.FaseProyecto;
import com.backendgip.model.Proyecto;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActividadRepository extends CrudRepository<Actividad, Integer> {
	List<Actividad> findByBase(Boolean base);

	List<Actividad> findByProyecto(Proyecto proyecto);

	List<Actividad> findByProyectoAndFase(Proyecto proyecto, FaseProyecto fase);

	List<Actividad> findByFaseAndBase(FaseProyecto fase, Boolean base);

	boolean existsByProyecto(Proyecto proyecto);

	List<Actividad> findByFase(FaseProyecto fase);

	boolean existsByActividad(String actividad);

	boolean existsByActividadAndFase(String actividad, FaseProyecto fase);
}

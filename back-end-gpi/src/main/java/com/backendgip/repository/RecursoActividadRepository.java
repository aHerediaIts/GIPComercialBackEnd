//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.repository;

import com.backendgip.model.ActividadAsignada;
import com.backendgip.model.Empleado;
import com.backendgip.model.RecursoActividad;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecursoActividadRepository extends CrudRepository<RecursoActividad, Integer> {
	Boolean existsByEmpleado(Empleado empleado);

	Boolean existsByEmpleadoAndFecha(Empleado empleado, LocalDate fecha);

	Boolean existsByActividad(ActividadAsignada actividad);

	Boolean existsByActividadAndFecha(ActividadAsignada actividad, LocalDate fecha);

	List<RecursoActividad> findByActividad(ActividadAsignada actividad);

	List<RecursoActividad> findByEmpleado(Empleado empleado);

	List<RecursoActividad> findByEmpleadoAndActividad(Empleado empleado, ActividadAsignada actividad);

	List<RecursoActividad> findByActividadAndFecha(ActividadAsignada actividad, LocalDate Fecha);

	List<RecursoActividad> deleteByActividadAndFecha(ActividadAsignada actividad, LocalDate Fecha);
}

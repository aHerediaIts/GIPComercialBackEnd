//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.service;

import com.backendgip.model.ActividadAsignada;
import com.backendgip.model.Empleado;
import com.backendgip.model.RecursoActividad;
import java.time.LocalDate;
import java.util.List;

public interface RecursoActividadService {
	List<RecursoActividad> getRecursoActividad();

	RecursoActividad saveRecursoActividad(RecursoActividad recursoActividad);

	void deleteRecursoActividad(RecursoActividad recursoActividad);

	RecursoActividad getRecursoActividadById(Integer id);

	Boolean existsByEmpleado(Empleado empleado);

	Boolean existsByEmpleadoAndFecha(Empleado empleado, LocalDate fecha);

	Boolean existsByActividad(ActividadAsignada actividad);

	Boolean existsByActividadAndFecha(ActividadAsignada actividad, LocalDate fecha);

	List<RecursoActividad> findByActividad(ActividadAsignada actividad);

	List<RecursoActividad> findByEmpleado(Empleado empleado);

	List<RecursoActividad> findByEmpleadoAndActividad(Empleado empleado, ActividadAsignada actividad);

	List<RecursoActividad> findByEmpleadoAndActividadNewDates(Empleado empleado, ActividadAsignada actividad);
}

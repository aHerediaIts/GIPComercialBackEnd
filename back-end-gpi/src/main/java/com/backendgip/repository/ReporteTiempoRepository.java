//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.repository;

import com.backendgip.model.ActividadAsignada;
import com.backendgip.model.Empleado;
import com.backendgip.model.EstadoReporteTiempo;
import com.backendgip.model.Proyecto;
import com.backendgip.model.ReporteTiempo;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReporteTiempoRepository extends CrudRepository<ReporteTiempo, Integer> {
	boolean existsByEmpleadoAndActividadAndFecha(Empleado empleado, ActividadAsignada actividad, LocalDate fecha);

	boolean existsByEmpleado(Empleado empleado);

	boolean existsByFecha(LocalDate fecha);

	boolean existsByEmpleadoAndFecha(Empleado empleado, LocalDate fecha);

	boolean existsByEmpleadoAndFechaAndActividad(Empleado empleado, LocalDate fecha, ActividadAsignada actividad);

	List<ReporteTiempo> findByEmpleadoAndFechaBetween(Empleado empleado, LocalDate fechaInicio, LocalDate fechaFin);

	List<ReporteTiempo> findByFechaAfter(LocalDate fechaFin);

	List<ReporteTiempo>  findByEmpleadoAndFecha(Empleado empleado, LocalDate fecha);

	List<ReporteTiempo> findByProyecto(Proyecto proyecto);

	List<ReporteTiempo> findByEmpleado(Empleado empleado);

	List<ReporteTiempo> findByEstado(EstadoReporteTiempo estado);

	List<ReporteTiempo> findByProyectoAndEmpleado(Proyecto proyecto, Empleado empleado);

	List<ReporteTiempo> findByProyectoAndEstadoAndEmpleado(Proyecto proyecto,EstadoReporteTiempo estado, Empleado empleado);

	List<ReporteTiempo> findByProyectoAndEstado(Proyecto proyecto, EstadoReporteTiempo estado);

	List<ReporteTiempo> findByEmpleadoAndEstado(Empleado empleado, EstadoReporteTiempo estado);

	List<ReporteTiempo> findByActividad(ActividadAsignada actividad);

	List<ReporteTiempo> findByEmpleadoAndActividad(Empleado empleado, ActividadAsignada actividad);

	List<ReporteTiempo> findByEmpleadoAndProyecto(Empleado empleado, Proyecto proyecto);
}

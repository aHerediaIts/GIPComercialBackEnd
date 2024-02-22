package com.backendgip.service;

import com.backendgip.model.ActividadAsignada;
import com.backendgip.model.Empleado;
import com.backendgip.model.EstadoReporteTiempo;
import com.backendgip.model.Proyecto;
import com.backendgip.model.ReporteTiempo;
import java.time.LocalDate;
import java.util.List;

public interface ReporteTiempoService {
	List<ReporteTiempo> getReporteTiempo();
	
	List<ReporteTiempo> getReporteTiempoFechaAfter(LocalDate fechaInicio);
	
	List<ReporteTiempo> getReporteTiempoPaging(int page, int size);

	ReporteTiempo saveReporteTiempo(ReporteTiempo reporteTiempo);

	void deleteReporteTiempo(ReporteTiempo reporteTiempo);

	ReporteTiempo getReporteTiempoById(Integer idReporteTiempo);

	boolean existsByEmpleadoAndActividadAndFecha(Empleado empleado, ActividadAsignada actividad, LocalDate fecha);

	boolean existsByEmpleadoAndFecha(Empleado empleado, LocalDate fecha);

	List<ReporteTiempo> findByActividad(ActividadAsignada actividad);

	boolean validFechaReporteActividadAsig(ReporteTiempo reporte);

	List<ReporteTiempo> findByProyecto(Proyecto proyecto);

	List<ReporteTiempo> findByProyectoAndEstado(Proyecto proyecto, EstadoReporteTiempo estado);

	List<ReporteTiempo> findByEmpleadoAndActividad(Empleado empleado, ActividadAsignada actividad);

	List<ReporteTiempo> findByEmpleado(Empleado empleado);

	List<ReporteTiempo> findByEmpleadoAndProyecto(Empleado empleado, Proyecto proyecto);

	List<ReporteTiempo> findByEstado(EstadoReporteTiempo estado);

	List<LocalDate> getFechasBetween(LocalDate fechaInicio, LocalDate fechaFin);

	List<ReporteTiempo> getAllReporteTiempoByIdEmpleados(Integer recursosAdd);
}
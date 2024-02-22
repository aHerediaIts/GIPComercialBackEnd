//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.imp;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.ActividadAsignada;
import com.backendgip.model.Empleado;
import com.backendgip.model.EstadoReporteTiempo;
import com.backendgip.model.Proyecto;
import com.backendgip.model.RecursoActividad;
import com.backendgip.model.ReporteTiempo;
import com.backendgip.repository.RecursoActividadRepository;
import com.backendgip.repository.ReporteTiempoPagingRepository;
import com.backendgip.repository.ReporteTiempoRepository;
import com.backendgip.service.ReporteTiempoService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReporteTiempoServImp implements ReporteTiempoService {
	@Autowired
	private ReporteTiempoRepository reporteTiempoRepository;
	@Autowired
	private ReporteTiempoPagingRepository reporteTiempoPagingRepository;
	@Autowired
	private RecursoActividadRepository recursoActRepository;

	public ReporteTiempoServImp() {
	}

	public List<ReporteTiempo> getReporteTiempo() {
		List<ReporteTiempo> reporteTiempo = (List) this.reporteTiempoRepository.findAll();
		return reporteTiempo;
	}

	public List<ReporteTiempo> getReporteTiempoFechaAfter(LocalDate fechaInicio) {
		List<ReporteTiempo> reporteTiempo = (List) this.reporteTiempoRepository.findByFechaAfter(fechaInicio);
		return reporteTiempo;
	}

	public List<ReporteTiempo> getReporteTiempoPaging(int page, int size) {
		List<ReporteTiempo> reporteTiempo = new ArrayList<>();
		Pageable pageable = PageRequest.of(page, size);
		Page<ReporteTiempo> objectIterable = reporteTiempoPagingRepository.findAll(pageable);
		if (objectIterable.getContent().iterator().hasNext()) {
			reporteTiempo.addAll(objectIterable.getContent());
		}
		// return new PageImpl<>(reporteTiempo, pageable,
		// reporteTiempoPagingRepository.count());
		return reporteTiempo;
	}

	public ReporteTiempo saveReporteTiempo(ReporteTiempo reporteTiempo) {
		return (ReporteTiempo) this.reporteTiempoRepository.save(reporteTiempo);
	}

	public void deleteReporteTiempo(ReporteTiempo reporteTiempo) {
		this.reporteTiempoRepository.delete(reporteTiempo);
	}

	public ReporteTiempo getReporteTiempoById(Integer idReporteTiempo) {
		return (ReporteTiempo) this.reporteTiempoRepository.findById(idReporteTiempo).orElseThrow(() -> {
			return new ResourceNotFoundException(
					"No se ha encontrado el reporte de tiempo con el id: " + idReporteTiempo);
		});
	}

	public boolean existsByEmpleadoAndActividadAndFecha(Empleado empleado, ActividadAsignada actividad,
			LocalDate fecha) {
		return this.reporteTiempoRepository.existsByEmpleadoAndActividadAndFecha(empleado, actividad, fecha);
	}

	public List<ReporteTiempo> findByActividad(ActividadAsignada actividad) {
		return this.reporteTiempoRepository.findByActividad(actividad);
	}

	public boolean validFechaReporteActividadAsig(ReporteTiempo reporte) {
		List<RecursoActividad> actividades = this.recursoActRepository.findByEmpleadoAndActividad(reporte.getEmpleado(),
				reporte.getActividad());
		boolean flag = false;
		Iterator var4 = actividades.iterator();

		while (var4.hasNext()) {
			RecursoActividad e = (RecursoActividad) var4.next();
			if (reporte.getFecha().isEqual(e.getFecha())) {
				flag = true;
			}
		}

		return flag;
	}

	public List<ReporteTiempo> findByProyecto(Proyecto proyecto) {
		return this.reporteTiempoRepository.findByProyecto(proyecto);
	}

	public List<ReporteTiempo> findByEmpleadoAndActividad(Empleado empleado, ActividadAsignada actividad) {
		return this.reporteTiempoRepository.findByEmpleadoAndActividad(empleado, actividad);
	}

	public List<ReporteTiempo> findByEmpleado(Empleado empleado) {
		return this.reporteTiempoRepository.findByEmpleado(empleado);
	}

	public List<ReporteTiempo> findByEmpleadoAndProyecto(Empleado empleado, Proyecto proyecto) {
		return this.reporteTiempoRepository.findByEmpleadoAndProyecto(empleado, proyecto);
	}

	public List<ReporteTiempo> findByProyectoAndEstado(Proyecto proyecto, EstadoReporteTiempo estado) {
		return this.reporteTiempoRepository.findByProyectoAndEstado(proyecto, estado);
	}

	public List<ReporteTiempo> findByEstado(EstadoReporteTiempo estado) {
		return this.reporteTiempoRepository.findByEstado(estado);
	}

	public boolean existsByEmpleadoAndFecha(Empleado empleado, LocalDate fecha) {
		return this.reporteTiempoRepository.existsByEmpleadoAndFecha(empleado, fecha);
	}

	public List<LocalDate> getFechasBetween(LocalDate fechaInicio, LocalDate fechaFin) {
		List<LocalDate> fechas = new ArrayList();
		LocalDate fecha = fechaInicio.minusDays(1L);

		while (fecha.isBefore(fechaFin)) {
			fecha = fecha.plusDays(1L);
			LocalDate f = LocalDate.of(fecha.getYear(), fecha.getMonthValue(), fecha.getDayOfMonth());
			fechas.add(f);
		}

		return fechas;
	}

	@Override
	public List<ReporteTiempo> getAllReporteTiempoByIdEmpleados(Integer recursosAdd) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getAllReporteTiempoByIdEmpleados'");
	}
}

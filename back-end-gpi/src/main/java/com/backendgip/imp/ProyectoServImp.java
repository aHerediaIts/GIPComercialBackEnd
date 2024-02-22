//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.imp;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.Cliente;
import com.backendgip.model.ComponenteDesarrollo;
import com.backendgip.model.Empleado;
import com.backendgip.model.EstadoProyecto;
import com.backendgip.model.ProjectStatusReport;
import com.backendgip.model.Proyecto;
import com.backendgip.model.RecursoActividad;
import com.backendgip.model.ReporteTiempo;
import com.backendgip.repository.ProyectoRepository;
import com.backendgip.repository.RecursoActividadRepository;
import com.backendgip.service.ProyectoService;
import com.backendgip.service.ReporteTiempoService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProyectoServImp implements ProyectoService {
	@Autowired
	private ProyectoRepository proyectoRepository;
	@Autowired
	private RecursoActividadRepository recursosActRepository;
	@Autowired
	private ReporteTiempoService reporteService;

	public ProyectoServImp() {
	}

	public List<Proyecto> getProyectos() {
		return (List) this.proyectoRepository.findAll();
	}

	public List<Proyecto> getProyectosByFechaInicioFechaFin(LocalDate fechaInicio, LocalDate fechaFin) {

		List<Proyecto> psr = (List<Proyecto>) this.proyectoRepository.findAll();
		List<Proyecto> proyectosFiltradosPorFechaFiltroFechaNula = new ArrayList();
		List<Proyecto> proyectosFiltradosPorFecha = new ArrayList();

		for (Proyecto proyecto : psr) {
			if(proyecto.getFechaInicio() != null && proyecto.getFechaFin() != null){
			proyectosFiltradosPorFechaFiltroFechaNula.add(proyecto);
			}}

		for (Proyecto proyecto : proyectosFiltradosPorFechaFiltroFechaNula) {
			if((proyecto.getFechaInicio().equals(fechaInicio) && proyecto.getFechaFin().equals(fechaFin)) || (proyecto.getFechaInicio().isAfter(fechaInicio) && proyecto.getFechaFin().isBefore(fechaFin))){
				proyectosFiltradosPorFecha.add(proyecto);
			}}
		return 	proyectosFiltradosPorFecha;
	}

	public Proyecto saveProyecto(Proyecto proyecto) {
		return (Proyecto) this.proyectoRepository.save(proyecto);
	}

	public void deleteProyecto(Proyecto proyecto) {
		this.proyectoRepository.delete(proyecto);
	}

	public Proyecto getProyectoById(Integer id) {
		return (Proyecto) this.proyectoRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado el componente con el id: " + id);
		});
	}

	public List<Proyecto> findByEstado(EstadoProyecto estado) {
		return this.proyectoRepository.findByEstadoProyecto(estado);
	}

	public List<Proyecto> findProyectosForReporteTiempo(Empleado empleado) {
		List<Proyecto> proyectos = (List) this.proyectoRepository.findAll();
		List<Proyecto> availablesProyectos = new ArrayList();
		List<RecursoActividad> actividadesAsig = this.recursosActRepository.findByEmpleado(empleado);
		Iterator var5 = proyectos.iterator();

		while (var5.hasNext()) {
			Proyecto e = (Proyecto) var5.next();
			Iterator var7 = actividadesAsig.iterator();

			while (var7.hasNext()) {
				RecursoActividad a = (RecursoActividad) var7.next();
				if (a.getActividad().getProyecto().getId() == e.getId() && !availablesProyectos.contains(e)) {
					availablesProyectos.add(e);
				}
			}
		}

		List<Proyecto> proyectosInt = this.proyectoRepository.findByInterno(Boolean.TRUE);
		availablesProyectos.addAll(proyectosInt);
		return availablesProyectos;
	}

	public List<Proyecto> findByCliente(Cliente cliente) {
		return this.proyectoRepository.findByCliente(cliente);
	}

	public List<Proyecto> findByCodigo(String codigo) {
		return this.proyectoRepository.findByCodigo(codigo);
	}

	public List<Proyecto> findByComponente(ComponenteDesarrollo componente) {
		return this.proyectoRepository.findByComponente(componente);
	}

	public List<Proyecto> findByCodigoAndCliente(String codigo, Cliente cliente) {
		return this.proyectoRepository.findByCodigoAndCliente(codigo, cliente);
	}

	public List<Proyecto> findByCodigoAndComponente(String codigo, ComponenteDesarrollo componente) {
		return this.proyectoRepository.findByCodigoAndComponente(codigo, componente);
	}

	public List<Proyecto> findByCodigoAndClienteAndComponente(String codigo, Cliente cliente,
			ComponenteDesarrollo componente) {
		return this.proyectoRepository.findByCodigoAndClienteAndComponente(codigo, cliente, componente);
	}

	public List<Proyecto> findByComponenteAndCliente(ComponenteDesarrollo componente, Cliente cliente) {
		return this.proyectoRepository.findByComponenteAndCliente(componente, cliente);
	}

	public Integer getHorasPropuesta(Proyecto proyecto) {
		List<ReporteTiempo> reportes = this.reporteService.findByProyecto(proyecto);
		Integer totalHoras = 0;
		Iterator var4 = reportes.iterator();

		while (var4.hasNext()) {
			ReporteTiempo r = (ReporteTiempo) var4.next();
			if (r.getActividad().getActividad().getFase().getFase().equalsIgnoreCase("PROPUESTA")
					&& r.getEstado().getEstado().equalsIgnoreCase("APROBADO")) {
				totalHoras = totalHoras + r.getHoras();
			}
		}

		return totalHoras;
	}

	public List<Proyecto> findByInterno(Boolean interno) {
		return this.proyectoRepository.findByInterno(interno);
	}

	public List<Proyecto> findByLider(Empleado lider) {
		return this.proyectoRepository.findByLider(lider);
	}

	public List<Proyecto> findByLiderFechaInicioFechaFin(LocalDate fechaInicio, LocalDate fechaFin, Empleado lider) {

		List<Proyecto> psr = (List<Proyecto>) this.proyectoRepository.findByLider(lider);
		List<Proyecto> proyectosFiltradosPorFechaFiltroFechaNula = new ArrayList();
		List<Proyecto> proyectosFiltradosPorFecha = new ArrayList();

		for (Proyecto proyecto : psr) {
			if(proyecto.getFechaInicio() != null && proyecto.getFechaFin() != null){
					proyectosFiltradosPorFechaFiltroFechaNula.add(proyecto);
			}}

		for (Proyecto proyecto : proyectosFiltradosPorFechaFiltroFechaNula) {
			if((proyecto.getFechaInicio().equals(fechaInicio) && proyecto.getFechaFin().equals(fechaFin)) || (proyecto.getFechaInicio().isAfter(fechaInicio) && proyecto.getFechaFin().isBefore(fechaFin))){
					proyectosFiltradosPorFecha.add(proyecto);
			}}
		return 	proyectosFiltradosPorFecha;
	}

	public boolean existsByNombre(String nombre) {
		return this.proyectoRepository.existsByNombre(nombre);
	}
}

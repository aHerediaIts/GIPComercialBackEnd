//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.service;

import com.backendgip.model.Cliente;
import com.backendgip.model.ComponenteDesarrollo;
import com.backendgip.model.Empleado;
import com.backendgip.model.EstadoProyecto;
import com.backendgip.model.Proyecto;

import java.time.LocalDate;
import java.util.List;

public interface ProyectoService {
	List<Proyecto> getProyectos();

	List<Proyecto> getProyectosByFechaInicioFechaFin(LocalDate fechaInicio, LocalDate fechaFin);

	Proyecto saveProyecto(Proyecto proyecto);

	void deleteProyecto(Proyecto proyecto);

	Proyecto getProyectoById(Integer id);

	List<Proyecto> findByEstado(EstadoProyecto estado);

	List<Proyecto> findProyectosForReporteTiempo(Empleado empleado);

	List<Proyecto> findByCodigo(String codigo);

	List<Proyecto> findByCliente(Cliente cliente);

	List<Proyecto> findByComponente(ComponenteDesarrollo componente);

	List<Proyecto> findByCodigoAndCliente(String codigo, Cliente cliente);

	List<Proyecto> findByCodigoAndComponente(String codigo, ComponenteDesarrollo componente);

	List<Proyecto> findByCodigoAndClienteAndComponente(String codigo, Cliente cliente, ComponenteDesarrollo componente);

	List<Proyecto> findByComponenteAndCliente(ComponenteDesarrollo componente, Cliente cliente);

	List<Proyecto> findByInterno(Boolean interno);

	List<Proyecto> findByLider(Empleado lider);

	List<Proyecto> findByLiderFechaInicioFechaFin(LocalDate fechaInicio, LocalDate fechaFin, Empleado lider);

	boolean existsByNombre(String nombre);

	Integer getHorasPropuesta(Proyecto proyecto);
}

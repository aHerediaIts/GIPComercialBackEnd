//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.service;

import com.backendgip.model.Cargo;
import com.backendgip.model.Empleado;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EmpleadoService {
	List<Empleado> getEmpleado();

	Empleado saveEmpleado(Empleado empleado);

	void deleteEmpleado(Empleado empleado);

	Empleado getEmpleadoById(Integer id);

	Optional<List<Empleado>> getDirectorCliente(Cargo cargo);

	List<LocalDate> getFechasBetween(LocalDate fechaInicio, LocalDate fechaFin);

	Optional<Empleado> getByNombreUsuario(String nombreUsuario);

	boolean existsByNombreUsuario(String nombreUsuario);

	boolean existsByEmail(String email);
}

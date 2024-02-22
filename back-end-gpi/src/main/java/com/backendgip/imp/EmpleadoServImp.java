//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.imp;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.Cargo;
import com.backendgip.model.Empleado;
import com.backendgip.repository.EmpleadoRepository;
import com.backendgip.service.EmpleadoService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoServImp implements EmpleadoService {
	@Autowired
	private EmpleadoRepository empleadoRepository;

	public EmpleadoServImp() {
	}

	public List<Empleado> getEmpleado() {
		return (List) this.empleadoRepository.findAll();
	}

	public Empleado saveEmpleado(Empleado Empleado) {
		return (Empleado) this.empleadoRepository.save(Empleado);
	}

	public void deleteEmpleado(Empleado Empleado) {
		this.empleadoRepository.delete(Empleado);
	}

	public Empleado getEmpleadoById(Integer idEmpleado) {
		return (Empleado) this.empleadoRepository.findById(idEmpleado).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado el recurso solicitado.");
		});
	}

	public Optional<List<Empleado>> getDirectorCliente(Cargo cargo) {
		return this.empleadoRepository.findByCargo(cargo);
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

	public Optional<Empleado> getByNombreUsuario(String nombreUsuario) {
		return this.empleadoRepository.findByNombreUsuario(nombreUsuario);
	}

	public boolean existsByNombreUsuario(String nombreUsuario) {
		return this.empleadoRepository.existsByNombreUsuario(nombreUsuario);
	}

	public boolean existsByEmail(String email) {
		return this.empleadoRepository.existsByEmail(email);
	}
}

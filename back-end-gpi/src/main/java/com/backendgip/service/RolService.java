package com.backendgip.service;

import com.backendgip.model.Empleado;
import com.backendgip.model.Rol;
import java.util.List;
import java.util.Optional;

public interface RolService {
	Rol save(Rol rol);

	Optional<Rol> getByRol(String rol);

	List<Rol> findAll();

	List<Rol> findRolesByEmpleado(Empleado empleado);

	Rol findById(Integer idRol);
}

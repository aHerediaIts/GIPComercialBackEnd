//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.repository;

import com.backendgip.model.Empleado;
import com.backendgip.model.EmpleadoRol;
import com.backendgip.model.Rol;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRolRepository extends CrudRepository<EmpleadoRol, Integer> {
	List<EmpleadoRol> findByEmpleado(Empleado empleado);

	boolean existsByEmpleadoAndRol(Empleado empleado, Rol rol);

	List<EmpleadoRol> findByRol(Rol rol);
}

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.repository;

import com.backendgip.model.Empleado;
import com.backendgip.model.EmpleadoEspecialidad;
import com.backendgip.model.Especialidad;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoEspecialidadRepository extends CrudRepository<EmpleadoEspecialidad, Integer> {
	List<EmpleadoEspecialidad> findByEmpleado(Empleado empleado);

	boolean existsByEmpleado(Empleado empleado);

	List<EmpleadoEspecialidad> findByEspecialidad(Especialidad especialidad);
}

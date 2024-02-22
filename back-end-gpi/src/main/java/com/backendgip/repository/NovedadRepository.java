//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.repository;

import com.backendgip.model.Empleado;
import com.backendgip.model.Novedad;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NovedadRepository extends CrudRepository<Novedad, Integer> {
	List<Novedad> findByEmpleado(Empleado empleado);

	boolean existsByEmpleado(Empleado empleado);
}

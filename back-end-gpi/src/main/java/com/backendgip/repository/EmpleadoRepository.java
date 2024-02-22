//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.repository;

import com.backendgip.model.Cargo;
import com.backendgip.model.Empleado;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends CrudRepository<Empleado, Integer> {
	Optional<Empleado> findByNombreUsuario(String nombreUsuario);

	Optional<List<Empleado>> findByCargo(Cargo cargo);

	boolean existsByCargo(Cargo cargo);

	boolean existsByNombreUsuario(String nombreUsuario);

	boolean existsByEmail(String email);

	List<Empleado> findByNombreContaining(String nombre);
}

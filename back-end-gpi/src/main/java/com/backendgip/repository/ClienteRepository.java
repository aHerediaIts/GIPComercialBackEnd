//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.repository;

import com.backendgip.model.Cliente;
import com.backendgip.model.Empleado;
import com.backendgip.model.SectorCliente;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Integer> {
	boolean existsByNomenclatura(String nomenclatura);

	Cliente findByNombre(String nombre);

	List<Cliente> findByFechaCreacion(LocalDate fechaCreacion);

	List<Cliente> findBySector(SectorCliente sector);

	List<Cliente> findByGerenteCuenta(Empleado empleado);

	List<Cliente> findBySectorAndGerenteCuenta(SectorCliente sector, Empleado gerente);
}

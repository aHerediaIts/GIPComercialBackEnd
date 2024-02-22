//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.service;

import com.backendgip.model.Cliente;
import com.backendgip.model.Empleado;
import com.backendgip.model.SectorCliente;
import java.time.LocalDate;
import java.util.List;

public interface ClienteService {
	List<Cliente> getClientes();

	Cliente saveCliente(Cliente cliente);

	void deleteCliente(Cliente cliente);

	Cliente getClienteById(Integer idCliente);

	Cliente findByNombre(String nombre);

	List<Cliente> findByFechaCreacion(LocalDate fechaCreacion);

	List<Cliente> findByBeetweenFechas(LocalDate fechaInicio, LocalDate fechaFin);

	List<Cliente> findBySector(SectorCliente sector);

	List<Cliente> findByGerenteCuenta(Empleado empleado);

	List<Cliente> findBySectorAndGerenteCuenta(SectorCliente sector, Empleado gerente);
}

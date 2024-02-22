//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.imp;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.Cliente;
import com.backendgip.model.Empleado;
import com.backendgip.model.SectorCliente;
import com.backendgip.repository.ClienteRepository;
import com.backendgip.service.ClienteService;
import com.backendgip.service.EmpleadoService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImp implements ClienteService {
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EmpleadoService empleadoService;

	public ClienteServiceImp() {
	}

	public List<Cliente> getClientes() {
		return (List) this.clienteRepository.findAll();
	}

	public Cliente saveCliente(Cliente cliente) {
		return (Cliente) this.clienteRepository.save(cliente);
	}

	public void deleteCliente(Cliente cliente) {
		this.clienteRepository.delete(cliente);
	}

	public Cliente getClienteById(Integer idCliente) {
		return (Cliente) this.clienteRepository.findById(idCliente).orElseThrow(() -> {
			return new ResourceNotFoundException("Cliente no existe con el ID" + idCliente);
		});
	}

	public List<Cliente> findBySector(SectorCliente sector) {
		return this.clienteRepository.findBySector(sector);
	}

	public List<Cliente> findByGerenteCuenta(Empleado empleado) {
		return this.clienteRepository.findByGerenteCuenta(empleado);
	}

	public List<Cliente> findBySectorAndGerenteCuenta(SectorCliente sector, Empleado gerente) {
		return this.clienteRepository.findBySectorAndGerenteCuenta(sector, gerente);
	}

	public List<Cliente> findByFechaCreacion(LocalDate fechaCreacion) {
		return this.clienteRepository.findByFechaCreacion(fechaCreacion);
	}

	public List<Cliente> findByBeetweenFechas(LocalDate fechaInicio, LocalDate fechaFin) {
		List<LocalDate> fechas = this.empleadoService.getFechasBetween(fechaInicio, fechaFin);
		List<Cliente> clientes = new ArrayList();
		Iterator var5 = fechas.iterator();

		while (var5.hasNext()) {
			LocalDate f = (LocalDate) var5.next();
			List<Cliente> c = this.clienteRepository.findByFechaCreacion(f);
			clientes.addAll(c);
		}

		return clientes;
	}

	public Cliente findByNombre(String nombre) {
		return this.clienteRepository.findByNombre(nombre);
	}
}

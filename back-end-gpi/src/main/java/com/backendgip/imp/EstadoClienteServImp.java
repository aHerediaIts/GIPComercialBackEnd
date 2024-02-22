//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.imp;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.EstadoCliente;
import com.backendgip.repository.EstadoClienteRepository;
import com.backendgip.service.EstadoClienteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadoClienteServImp implements EstadoClienteService {
	@Autowired
	private EstadoClienteRepository estadoRepository;

	public EstadoClienteServImp() {
	}

	public List<EstadoCliente> getEstados() {
		return (List) this.estadoRepository.findAll();
	}

	public EstadoCliente saveEstado(EstadoCliente estado) {
		return (EstadoCliente) this.estadoRepository.save(estado);
	}

	public void deleteEstado(EstadoCliente estado) {
		this.estadoRepository.delete(estado);
	}

	public EstadoCliente getEstadoById(Integer idEstado) {
		return (EstadoCliente) this.estadoRepository.findById(idEstado).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado el estado con el id:" + idEstado);
		});
	}
}

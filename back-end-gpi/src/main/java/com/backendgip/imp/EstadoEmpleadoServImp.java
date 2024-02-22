//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.imp;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.EstadoEmpleado;
import com.backendgip.repository.EstadoEmpleadoRepository;
import com.backendgip.service.EstadoEmpleadoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadoEmpleadoServImp implements EstadoEmpleadoService {
	@Autowired
	private EstadoEmpleadoRepository estadoRepository;

	public EstadoEmpleadoServImp() {
	}

	public List<EstadoEmpleado> getEstado() {
		return (List) this.estadoRepository.findAll();
	}

	public EstadoEmpleado saveEstado(EstadoEmpleado estado) {
		return (EstadoEmpleado) this.estadoRepository.save(estado);
	}

	public void deleteEstado(EstadoEmpleado estado) {
		this.estadoRepository.delete(estado);
	}

	public EstadoEmpleado getEstadoById(Integer id) {
		return (EstadoEmpleado) this.estadoRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado el recurso solicitado.");
		});
	}
}

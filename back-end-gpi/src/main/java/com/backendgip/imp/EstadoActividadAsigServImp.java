//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.imp;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.EstadoActividadAsig;
import com.backendgip.repository.EstadoActividadAsigRepository;
import com.backendgip.service.EstadoActividadAsigService;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class EstadoActividadAsigServImp implements EstadoActividadAsigService {
	private EstadoActividadAsigRepository estadoRepository;

	public EstadoActividadAsigServImp() {
	}

	public List<EstadoActividadAsig> getEstados() {
		List<EstadoActividadAsig> objectList = new ArrayList<>();
		this.estadoRepository.findAll().forEach(objectList::add);
		return objectList;

	}

	public EstadoActividadAsig saveEstado(EstadoActividadAsig estado) {

		return this.estadoRepository.save(estado);
	}

	public void deleteEstado(EstadoActividadAsig estado) {
		this.estadoRepository.delete(estado);
	}

	public EstadoActividadAsig getEstadoById(Integer idEstado) {
		return (EstadoActividadAsig) this.estadoRepository.findById(idEstado).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado estado con el id:" + idEstado);
		});
	}
}

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.imp;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.EstadoPropuesta;
import com.backendgip.repository.EstadoPropuestaRepository;
import com.backendgip.service.EstadoPropuestaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadoPropuestaServImp implements EstadoPropuestaService {
	@Autowired
	private EstadoPropuestaRepository propuestaRepository;

	public EstadoPropuestaServImp() {
	}

	public List<EstadoPropuesta> getEstados() {
		return (List) this.propuestaRepository.findAll();
	}

	public EstadoPropuesta saveEstado(EstadoPropuesta estado) {
		return (EstadoPropuesta) this.propuestaRepository.save(estado);
	}

	public void deleteEstado(EstadoPropuesta componente) {
		this.propuestaRepository.delete(componente);
	}

	public EstadoPropuesta getEstadoById(Integer id) {
		return (EstadoPropuesta) this.propuestaRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado el componente con el id: " + id);
		});
	}
}

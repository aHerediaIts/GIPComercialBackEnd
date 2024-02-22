//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.imp;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.Especialidad;
import com.backendgip.repository.EspecialidadRepository;
import com.backendgip.service.EspecialidadService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EspecialidadServImp implements EspecialidadService {
	@Autowired
	private EspecialidadRepository especialidadRepository;

	public EspecialidadServImp() {
	}

	public List<Especialidad> getEspecialidad() {
		return (List) this.especialidadRepository.findAll();
	}

	public Especialidad saveEspecialidad(Especialidad especialidad) {
		return (Especialidad) this.especialidadRepository.save(especialidad);
	}

	public void deleteEspecialidad(Especialidad especialidad) {
		this.especialidadRepository.delete(especialidad);
	}

	public Especialidad getEspecialidadById(Integer idEspecialidad) {
		return (Especialidad) this.especialidadRepository.findById(idEspecialidad).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado el recurso solicitado.");
		});
	}
}

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.imp;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.DependenciaEmpleado;
import com.backendgip.repository.DependenciaEmpleadoRepository;
import com.backendgip.service.DependenciaEmpleadoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DependenciaEmpleadoServImp implements DependenciaEmpleadoService {
	@Autowired
	private DependenciaEmpleadoRepository dependenciaRepository;

	public DependenciaEmpleadoServImp() {
	}

	public List<DependenciaEmpleado> getDependencia() {
		return (List) this.dependenciaRepository.findAll();
	}

	public DependenciaEmpleado saveDependencia(DependenciaEmpleado dependencia) {
		return (DependenciaEmpleado) this.dependenciaRepository.save(dependencia);
	}

	public void deleteDependencia(DependenciaEmpleado dependencia) {
		this.dependenciaRepository.delete(dependencia);
	}

	public DependenciaEmpleado getDependenciaById(Integer id) {
		return (DependenciaEmpleado) this.dependenciaRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado el recurso solicitado.");
		});
	}
}

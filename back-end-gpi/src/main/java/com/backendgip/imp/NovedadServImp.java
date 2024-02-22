//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.imp;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.Empleado;
import com.backendgip.model.Novedad;
import com.backendgip.repository.NovedadRepository;
import com.backendgip.service.NovedadService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NovedadServImp implements NovedadService {
	@Autowired
	private NovedadRepository novedadRepository;

	public NovedadServImp() {
	}

	public List<Novedad> findAll() {
		return (List) this.novedadRepository.findAll();
	}

	public Novedad save(Novedad novedad) {
		return (Novedad) this.novedadRepository.save(novedad);
	}

	public void delete(Novedad novedad) {
		this.novedadRepository.delete(novedad);
	}

	public Novedad findById(Integer id) {
		return (Novedad) this.novedadRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado el recurso solicitado " + id);
		});
	}

	public List<Novedad> findByEmpleado(Empleado empleado) {
		return this.novedadRepository.findByEmpleado(empleado);
	}
}

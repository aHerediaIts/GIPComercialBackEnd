//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.imp;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.ComponenteDesarrollo;
import com.backendgip.repository.ComponenteDesarrolloRepository;
import com.backendgip.service.ComponenteDesarrolloService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComponenteDesarrolloServImp implements ComponenteDesarrolloService {
	@Autowired
	private ComponenteDesarrolloRepository componenteRepository;

	public ComponenteDesarrolloServImp() {
	}

	public List<ComponenteDesarrollo> getComponentes() {
		return (List) this.componenteRepository.findAll();
	}

	public ComponenteDesarrollo saveComponente(ComponenteDesarrollo componente) {
		return (ComponenteDesarrollo) this.componenteRepository.save(componente);
	}

	public void deleteComponente(ComponenteDesarrollo componente) {
		this.componenteRepository.delete(componente);
	}

	public ComponenteDesarrollo getComponenteById(Integer id) {
		return (ComponenteDesarrollo) this.componenteRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado el componente con el id: " + id);
		});
	}
}

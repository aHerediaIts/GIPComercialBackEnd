//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.imp;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.TipoProyecto;
import com.backendgip.repository.TipoProyectoRepository;
import com.backendgip.service.TipoProyectoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoProyectoServImp implements TipoProyectoService {
	@Autowired
	private TipoProyectoRepository tipoRepository;

	public TipoProyectoServImp() {
	}

	public List<TipoProyecto> getTipos() {
		List<TipoProyecto> tipo = (List) this.tipoRepository.findAll();
		return tipo;
	}

	public TipoProyecto saveTipo(TipoProyecto tipo) {
		return (TipoProyecto) this.tipoRepository.save(tipo);
	}

	public void deleteTipo(TipoProyecto tipo) {
		this.tipoRepository.delete(tipo);
	}

	public TipoProyecto getTipoById(Integer id) {
		return (TipoProyecto) this.tipoRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado el estado con el id: " + id);
		});
	}
}

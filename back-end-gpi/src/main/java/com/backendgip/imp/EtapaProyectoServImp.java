//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.imp;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.EtapaProyecto;
import com.backendgip.repository.EtapaProyectoRepository;
import com.backendgip.service.EtapaProyectoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EtapaProyectoServImp implements EtapaProyectoService {
	@Autowired
	private EtapaProyectoRepository etapaRepository;

	public EtapaProyectoServImp() {
	}

	public List<EtapaProyecto> getEtapas() {
		return (List) this.etapaRepository.findAll();
	}

	public EtapaProyecto saveEtapa(EtapaProyecto etapa) {
		return (EtapaProyecto) this.etapaRepository.save(etapa);
	}

	public void deleteEtapa(EtapaProyecto etapa) {
		this.etapaRepository.delete(etapa);
	}

	public EtapaProyecto getEtapaById(Integer id) {
		return (EtapaProyecto) this.etapaRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado la etapa con el id: " + id);
		});
	}
}

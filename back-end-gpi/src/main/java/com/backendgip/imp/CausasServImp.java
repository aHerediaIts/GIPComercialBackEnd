//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.imp;

import com.backendgip.model.Causas;
import com.backendgip.repository.CausasRepository;
import com.backendgip.service.CausasService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

@Service
public class CausasServImp implements CausasService {
	@Autowired
	private CausasRepository causaRepo;

	public CausasServImp() {
	}

	public List<Causas> getCausas() {
		return (List) this.causaRepo.findAll();
	}

	public Causas saveCausa(Causas causa) {
		return (Causas) this.causaRepo.save(causa);
	}

	public void deteleCausa(Causas causa) {
		this.causaRepo.delete(causa);
	}

	public Causas getCausaById(Integer id) {
		return (Causas) this.causaRepo.findById(id).orElseThrow(() -> {
			return new ResourceAccessException("No se ha encontrado la causa solicitada.");
		});
	}
}

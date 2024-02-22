//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.imp;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.TipoDocumento;
import com.backendgip.repository.TipoDocumentoRepository;
import com.backendgip.service.TipoDocumentoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoDocumentoServImp implements TipoDocumentoService {
	@Autowired
	private TipoDocumentoRepository tipoRepository;

	public TipoDocumentoServImp() {
	}

	public List<TipoDocumento> getTipos() {
		return (List) this.tipoRepository.findAll();
	}

	public TipoDocumento saveTipo(TipoDocumento tipo) {
		return (TipoDocumento) this.tipoRepository.save(tipo);
	}

	public void deleteTipo(TipoDocumento tipo) {
		this.tipoRepository.delete(tipo);
	}

	public TipoDocumento getTipoById(Integer id) {
		return (TipoDocumento) this.tipoRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado el recurso solicitado.");
		});
	}
}

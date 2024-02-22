package com.backendgip.service;

import com.backendgip.model.TipoDocumento;
import java.util.List;

public interface TipoDocumentoService {
	List<TipoDocumento> getTipos();

	TipoDocumento saveTipo(TipoDocumento tipo);

	void deleteTipo(TipoDocumento tipo);

	TipoDocumento getTipoById(Integer id);
}

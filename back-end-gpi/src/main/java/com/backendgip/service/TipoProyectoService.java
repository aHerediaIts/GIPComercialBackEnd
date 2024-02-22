package com.backendgip.service;

import com.backendgip.model.TipoProyecto;
import java.util.List;

public interface TipoProyectoService {
	List<TipoProyecto> getTipos();

	TipoProyecto saveTipo(TipoProyecto tipo);

	void deleteTipo(TipoProyecto tipo);

	TipoProyecto getTipoById(Integer id);
}
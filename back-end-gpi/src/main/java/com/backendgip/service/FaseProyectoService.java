//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.service;

import com.backendgip.model.FaseProyecto;
import com.backendgip.model.Proyecto;
import java.util.List;

public interface FaseProyectoService {
	List<FaseProyecto> getFases();

	FaseProyecto saveFase(FaseProyecto fase);

	void deleteFase(FaseProyecto fase);

	FaseProyecto getFaseById(Integer idFase);

	List<FaseProyecto> findByProyecto(Proyecto proyecto);

	List<FaseProyecto> findByProyectoAndBase(Proyecto proyecto);

	List<FaseProyecto> findByProyectoActividadAsig(Proyecto proyecto);
}

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.service;

import com.backendgip.model.EtapaProyecto;
import java.util.List;

public interface EtapaProyectoService {
	List<EtapaProyecto> getEtapas();

	EtapaProyecto saveEtapa(EtapaProyecto etapa);

	void deleteEtapa(EtapaProyecto etapa);

	EtapaProyecto getEtapaById(Integer id);
}

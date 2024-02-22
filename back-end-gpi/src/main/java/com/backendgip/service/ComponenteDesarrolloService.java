//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.service;

import com.backendgip.model.ComponenteDesarrollo;
import java.util.List;

public interface ComponenteDesarrolloService {
	List<ComponenteDesarrollo> getComponentes();

	ComponenteDesarrollo saveComponente(ComponenteDesarrollo componente);

	void deleteComponente(ComponenteDesarrollo componente);

	ComponenteDesarrollo getComponenteById(Integer id);
}

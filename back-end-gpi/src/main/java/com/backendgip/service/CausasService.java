//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.service;

import com.backendgip.model.Causas;
import java.util.List;

public interface CausasService {
	List<Causas> getCausas();

	Causas saveCausa(Causas causa);

	void deteleCausa(Causas causa);

	Causas getCausaById(Integer id);
}

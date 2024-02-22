//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.service;

import com.backendgip.model.Cargo;
import java.util.List;

public interface CargoService {
	List<Cargo> getCargo();

	Cargo saveCargo(Cargo cargo);

	void deleteCargo(Cargo cargo);

	Cargo getCargoById(Integer id);
}

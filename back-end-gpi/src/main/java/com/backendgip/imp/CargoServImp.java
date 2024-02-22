//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.imp;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.Cargo;
import com.backendgip.repository.CargoRepository;
import com.backendgip.service.CargoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CargoServImp implements CargoService {
	@Autowired
	private CargoRepository cargoRepository;

	public CargoServImp() {
	}

	public List<Cargo> getCargo() {
		return (List) this.cargoRepository.findAll();
	}

	public Cargo saveCargo(Cargo cargo) {
		return (Cargo) this.cargoRepository.save(cargo);
	}

	public void deleteCargo(Cargo cargo) {
		this.cargoRepository.delete(cargo);
	}

	public Cargo getCargoById(Integer idCargo) {
		return (Cargo) this.cargoRepository.findById(idCargo).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado el recurso solicitado.");
		});
	}
}

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.imp;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.SectorCliente;
import com.backendgip.repository.SectorClienteRepository;
import com.backendgip.service.SectorClienteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SectorClienteServImp implements SectorClienteService {
	@Autowired
	private SectorClienteRepository sectorRepository;

	public SectorClienteServImp() {
	}

	public List<SectorCliente> getSectores() {
		return (List) this.sectorRepository.findAll();
	}

	public SectorCliente saveSector(SectorCliente sector) {
		return (SectorCliente) this.sectorRepository.save(sector);
	}

	public void deleteSector(SectorCliente sector) {
		this.sectorRepository.delete(sector);
	}

	public SectorCliente getSectorById(Integer idSector) {
		return (SectorCliente) this.sectorRepository.findById(idSector).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado el sector economico con el id:" + idSector);
		});
	}
}

package com.backendgip.service;

import com.backendgip.model.SectorCliente;
import java.util.List;

public interface SectorClienteService {
	List<SectorCliente> getSectores();

	SectorCliente saveSector(SectorCliente sector);

	void deleteSector(SectorCliente sector);

	SectorCliente getSectorById(Integer idSector);
}

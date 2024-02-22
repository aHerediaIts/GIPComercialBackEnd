//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.imp;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.EstadoFactura;
import com.backendgip.repository.EstadoFacturaRepository;
import com.backendgip.service.EstadoFacturaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadoFacturaServImp implements EstadoFacturaService {
	@Autowired
	private EstadoFacturaRepository estadoFacturaRepository;

	public EstadoFacturaServImp() {
	}

	public List<EstadoFactura> getEstadoFactura() {
		List<EstadoFactura> estadoFactura = (List) this.estadoFacturaRepository.findAll();
		return estadoFactura;
	}

	public EstadoFactura saveEstadoFactura(EstadoFactura estadoFactura) {
		return (EstadoFactura) this.estadoFacturaRepository.save(estadoFactura);
	}

	public void deleteEstadoFactura(EstadoFactura estadoFactura) {
		this.estadoFacturaRepository.delete(estadoFactura);
	}

	public EstadoFactura getEstadoFacturaById(Integer idEstadoFactura) {
		return (EstadoFactura) this.estadoFacturaRepository.findById(idEstadoFactura).orElseThrow(() -> {
			return new ResourceNotFoundException(
					"No se ha encontrado el estado de factura con el id: " + idEstadoFactura);
		});
	}
}

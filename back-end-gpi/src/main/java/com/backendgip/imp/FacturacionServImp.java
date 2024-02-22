//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.imp;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.Facturacion;
import com.backendgip.model.Proyecto;
import com.backendgip.repository.FacturacionRepository;
import com.backendgip.service.FacturacionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacturacionServImp implements FacturacionService {
	@Autowired
	private FacturacionRepository facturacionRepository;

	public FacturacionServImp() {
	}

	public List<Facturacion> getFacturacion() {
		List<Facturacion> facturacion = (List) this.facturacionRepository.findAll();
		return facturacion;
	}

	public Facturacion saveFacturacion(Facturacion facturacion) {
		return (Facturacion) this.facturacionRepository.save(facturacion);
	}

	public void deleteFacturacion(Facturacion facturacion) {
		this.facturacionRepository.delete(facturacion);
	}

	public Facturacion getFacturacionById(Integer id) {
		return (Facturacion) this.facturacionRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado la facturacion con el id: " + id);
		});
	}

	public List<Facturacion> getCobrosByProyecto(Proyecto proyecto) {
		return this.facturacionRepository.findByProyecto(proyecto);
	}
}

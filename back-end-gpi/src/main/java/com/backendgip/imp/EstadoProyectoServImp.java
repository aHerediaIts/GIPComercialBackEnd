//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.imp;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.EstadoProyecto;
import com.backendgip.repository.EstadoProyectoRepository;
import com.backendgip.service.EstadoProyectoService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadoProyectoServImp implements EstadoProyectoService {
	@Autowired
	private EstadoProyectoRepository estadoRepository;

	public EstadoProyectoServImp() {
	}

	public List<EstadoProyecto> getEstados() {
		return (List) this.estadoRepository.findAll();
	}

	public EstadoProyecto saveEstado(EstadoProyecto estado) {
		return (EstadoProyecto) this.estadoRepository.save(estado);
	}

	public void deleteEstado(EstadoProyecto estado) {
		this.estadoRepository.delete(estado);
	}

	public EstadoProyecto getEstadoById(Integer id) {
		return (EstadoProyecto) this.estadoRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado el componente con el id: " + id);
		});
	}

	public List<EstadoProyecto> getEstadosPRP() {
		List<EstadoProyecto> estados = (List) this.estadoRepository.findAll();
		List<EstadoProyecto> estadosPRP = new ArrayList();
		Iterator var3 = estados.iterator();

		while (true) {
			EstadoProyecto e;
			do {
				if (!var3.hasNext()) {
					return estadosPRP;
				}

				e = (EstadoProyecto) var3.next();
			} while (!e.getEstado().equalsIgnoreCase("LEVANTAMIENTO DEL REQUERIMIENTO")
					&& !e.getEstado().equalsIgnoreCase("CONSTRUCCIÓN DE PROPUESTA")
					&& !e.getEstado().equalsIgnoreCase("PENDIENTE DE APROBACIÓN"));

			estadosPRP.add(e);
		}
	}

	public List<EstadoProyecto> getEstadosCRN() {
		List<EstadoProyecto> estados = (List) this.estadoRepository.findAll();
		List<EstadoProyecto> estadosCRN = new ArrayList();
		Iterator var3 = estados.iterator();

		while (true) {
			EstadoProyecto e;
			do {
				if (!var3.hasNext()) {
					return estadosCRN;
				}

				e = (EstadoProyecto) var3.next();
			} while (!e.getEstado().equalsIgnoreCase("PLANEACIÓN") && !e.getEstado().equalsIgnoreCase("EJECUCIÓN")
					&& !e.getEstado().equalsIgnoreCase("FINALIZADO"));

			estadosCRN.add(e);
		}
	}
}

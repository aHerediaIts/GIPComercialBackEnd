package com.backendgip.controller;

import com.backendgip.model.TipoReporte;

import com.backendgip.repository.TipoReporteRepository;
import com.backendgip.service.TipoReporteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
@RequestMapping({ "/api/reportes" })
public class SeguimientoReportesController {
    @Autowired
	private TipoReporteService tipoReporteService;
	@Autowired
	private TipoReporteRepository tipoRepository;

    public SeguimientoReportesController(){
    }

    @GetMapping({ "/tipos" })
	public List<TipoReporte> getAllTipos() {
		return (List) this.tipoRepository.findAll();
	}

	

}

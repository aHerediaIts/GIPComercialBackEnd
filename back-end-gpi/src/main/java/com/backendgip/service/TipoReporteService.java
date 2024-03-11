package com.backendgip.service;

import com.backendgip.model.TipoReporte;
import java.util.List;

import org.springframework.stereotype.Service;

public interface TipoReporteService {
    List<TipoReporte> getTipoReportes();
}

package com.backendgip.imp;

import com.backendgip.repository.TipoReporteRepository;
import com.backendgip.model.TipoReporte;
import com.backendgip.service.TipoReporteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoReporteServImp implements TipoReporteService {

    @Autowired
    private TipoReporteRepository tipoReporteRepository;

    @Override
    public List<TipoReporte> getTipoReportes() {
       return (List) this.tipoReporteRepository.findAll();
    }
    
}

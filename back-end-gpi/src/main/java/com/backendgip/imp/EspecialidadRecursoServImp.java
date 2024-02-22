package com.backendgip.imp;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backendgip.model.EspecialidadRecurso;
import com.backendgip.repository.EspecialidadRecursoRepository;
import com.backendgip.service.EspecialidadRecursoService;

@Service
public class EspecialidadRecursoServImp implements EspecialidadRecursoService {

    @Autowired
    private EspecialidadRecursoRepository especialidadRecursoRepository;

    @Override
    public List<EspecialidadRecurso> getEspecialidadRecursos() {
        return (List) this.especialidadRecursoRepository.findAll();
    }
    
}

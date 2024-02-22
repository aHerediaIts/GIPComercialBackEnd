package com.backendgip.imp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backendgip.model.PerfilesRecurso;
import com.backendgip.service.PerfilesRecursoService;
import com.backendgip.repository.PerfilesRecursoRepository;

@Service
public class PerfilesRecursoServImp implements PerfilesRecursoService{

    @Autowired
    private PerfilesRecursoRepository perfilesRecursoRepository;

    @Override
    public List<PerfilesRecurso> getPerfilesRecursos() {
       return (List) this.perfilesRecursoRepository.findAll();
    }

    
} 

package com.backendgip.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.backendgip.model.PerfilesRecurso;

@Repository
public interface PerfilesRecursoRepository extends CrudRepository<PerfilesRecurso, Integer> {
    
}

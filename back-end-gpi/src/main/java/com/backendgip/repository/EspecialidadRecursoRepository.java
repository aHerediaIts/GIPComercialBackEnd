package com.backendgip.repository;

import com.backendgip.model.EspecialidadRecurso;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspecialidadRecursoRepository extends CrudRepository<EspecialidadRecurso, Integer>{
}

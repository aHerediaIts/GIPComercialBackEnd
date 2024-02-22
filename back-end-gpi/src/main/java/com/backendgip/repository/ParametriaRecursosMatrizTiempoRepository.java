package com.backendgip.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.backendgip.model.ParametriaRecursosMatrizTiempo;

@Repository
public interface  ParametriaRecursosMatrizTiempoRepository extends CrudRepository<ParametriaRecursosMatrizTiempo, Integer>{
    
}
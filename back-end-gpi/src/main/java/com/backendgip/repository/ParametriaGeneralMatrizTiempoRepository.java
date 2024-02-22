package com.backendgip.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.backendgip.model.ParametriaGeneralMatrizTiempo;

@Repository
public interface  ParametriaGeneralMatrizTiempoRepository extends CrudRepository<ParametriaGeneralMatrizTiempo, Integer>{
    
}



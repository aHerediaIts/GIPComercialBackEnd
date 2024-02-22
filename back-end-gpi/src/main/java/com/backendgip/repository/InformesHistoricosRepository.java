package com.backendgip.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.backendgip.model.GeneracionMatrizTiempo;

public interface InformesHistoricosRepository extends CrudRepository<GeneracionMatrizTiempo, Integer>{
    
}
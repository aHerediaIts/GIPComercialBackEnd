package com.backendgip.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.backendgip.model.GeneracionMatrizTiempo;
import com.backendgip.model.MatrizTiempo;

@Repository
public interface GeneracionMatrizTiempoRepository extends CrudRepository<GeneracionMatrizTiempo, Integer>{

        List<GeneracionMatrizTiempo> findByMatrizTiempo(MatrizTiempo matriz);
}

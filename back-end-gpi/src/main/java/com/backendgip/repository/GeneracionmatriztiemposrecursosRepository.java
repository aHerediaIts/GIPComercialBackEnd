package com.backendgip.repository;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.backendgip.model.Generarmatriztiemposasignacionrecursos;

@Repository
public interface GeneracionmatriztiemposrecursosRepository extends CrudRepository<Generarmatriztiemposasignacionrecursos, Integer>{
        List<Generarmatriztiemposasignacionrecursos> findByGeneracionMatrizTiempoId(Integer id);

} 

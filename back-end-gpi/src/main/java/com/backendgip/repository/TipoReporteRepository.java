package com.backendgip.repository;

import com.backendgip.model.TipoReporte;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoReporteRepository extends CrudRepository<TipoReporte, Integer> {
	
}
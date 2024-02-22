package com.backendgip.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.backendgip.model.VersionMatriz;

@Repository
public interface VersionMatrizRepository extends CrudRepository<VersionMatriz, Integer> {

}
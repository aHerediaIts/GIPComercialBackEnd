package com.backendgip.repository;

import com.backendgip.model.MatrizTiempo;
import com.backendgip.model.Rol;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatrizTiempoRepository extends CrudRepository<MatrizTiempo, Integer> {
}

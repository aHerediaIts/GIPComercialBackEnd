//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.repository;

import com.backendgip.model.TipoProyecto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoProyectoRepository extends CrudRepository<TipoProyecto, Integer> {
	boolean existsByTipo(String tipo);
}

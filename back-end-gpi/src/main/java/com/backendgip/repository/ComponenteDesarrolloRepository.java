//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.repository;

import com.backendgip.model.ComponenteDesarrollo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComponenteDesarrolloRepository extends CrudRepository<ComponenteDesarrollo, Integer> {
	boolean existsByComponente(String componente);
}

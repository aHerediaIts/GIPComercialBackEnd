//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.repository;

import com.backendgip.model.FaseProyecto;
import com.backendgip.model.Proyecto;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaseProyectoRepository extends CrudRepository<FaseProyecto, Integer> {
	List<FaseProyecto> findByProyecto(Proyecto proyecto);

	List<FaseProyecto> findByBase(Boolean base);

	boolean existsByProyecto(Proyecto proyecto);

	boolean existsByFase(String fase);
}

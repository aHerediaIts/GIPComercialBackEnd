//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.repository;

import com.backendgip.model.Facturacion;
import com.backendgip.model.Proyecto;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturacionRepository extends CrudRepository<Facturacion, Integer> {
	List<Facturacion> findByProyecto(Proyecto proyecto);

	boolean existsByProyecto(Proyecto proyecto);
}

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.repository;

import com.backendgip.model.EstadoCliente;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoClienteRepository extends CrudRepository<EstadoCliente, Integer> {
	List<EstadoCliente> findByEstado(String estado);
}

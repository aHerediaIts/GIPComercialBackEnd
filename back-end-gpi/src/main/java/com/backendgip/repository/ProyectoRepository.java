//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.repository;

import com.backendgip.model.Cliente;
import com.backendgip.model.ComponenteDesarrollo;
import com.backendgip.model.Empleado;
import com.backendgip.model.EstadoProyecto;
import com.backendgip.model.Proyecto;
import com.backendgip.model.TipoProyecto;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProyectoRepository extends CrudRepository<Proyecto, Integer> {
	List<Proyecto> findByEstadoProyecto(EstadoProyecto estado);

	boolean existsByCliente(Cliente cliente);

	boolean existsByTipo(TipoProyecto tipo);

	boolean existsByComponente(ComponenteDesarrollo componente);

	boolean existsByNombre(String nombre);

	boolean existsByCodigoAndCliente(String codigo, Cliente cliente);

	List<Proyecto> findByCodigo(String codigo);

	List<Proyecto> findByCliente(Cliente cliente);

	List<Proyecto> findByComponente(ComponenteDesarrollo componente);

	List<Proyecto> findByCodigoAndCliente(String codigo, Cliente cliente);

	List<Proyecto> findByCodigoAndComponente(String codigo, ComponenteDesarrollo componente);

	List<Proyecto> findByCodigoAndClienteAndComponente(String codigo, Cliente cliente, ComponenteDesarrollo componente);

	List<Proyecto> findByComponenteAndCliente(ComponenteDesarrollo componente, Cliente cliente);

	List<Proyecto> findByInterno(Boolean interno);

	List<Proyecto> findByLider(Empleado lider);
}

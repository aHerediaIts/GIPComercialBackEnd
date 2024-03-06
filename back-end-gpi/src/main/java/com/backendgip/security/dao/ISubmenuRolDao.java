package com.backendgip.security.dao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.backendgip.security.models.RolSeg;
import com.backendgip.security.models.SubmenuRol;



public interface ISubmenuRolDao extends JpaRepository<SubmenuRol, Long> {


	@Query("select sr from SubmenuRol sr where sr.rol=?1")
	public List<SubmenuRol> buscarOpcionesRol(RolSeg rol) throws Exception;
	
	@Modifying
	@Transactional
	@Query("delete from SubmenuRol where rol=?1")
	public void eliminarSubmenuPorRol(RolSeg rol) throws Exception;
	
}




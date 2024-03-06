package com.backendgip.security.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.backendgip.security.models.ItemRol;
import com.backendgip.security.models.SubmenuRol;



public interface IItemRolDao extends CrudRepository<ItemRol, Long>{


	@Modifying
	@Transactional
	@Query("delete from ItemRol where submenuRol=?1")
	public void eliminarItemsPorSubmenu(SubmenuRol submenuRol) throws Exception;
	
}

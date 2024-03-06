package com.backendgip.security.services;

import java.util.List;
import org.springframework.stereotype.Service;

import com.backendgip.security.models.ItemRol;
import com.backendgip.security.models.SubmenuRol;

@Service
public interface IItemRolService  {


	public List<ItemRol> listar() throws Exception;
	
	public void eliminar(ItemRol itemRol) throws Exception;
	
	public void eliminarItemsPorSubmenu(SubmenuRol submenuRol) throws Exception;


	
}

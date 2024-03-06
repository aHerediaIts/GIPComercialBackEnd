package com.backendgip.security.services;


import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backendgip.security.models.Item;
import com.backendgip.security.models.ItemRol;
import com.backendgip.security.models.MenuResponse;
import com.backendgip.security.models.RolSeg;
import com.backendgip.security.models.SubmenuRol;

@Service
public class ArmaMenuRol {
	
	@Autowired
	ISubmenuRolService submenuRolService;
	
	@Autowired
	IItemRolService iItemRolService;
	
	MenuResponse menuResponse;
	
	SubmenuRol submenuRol;
	
	List<SubmenuRol> listaSubmenus;
	
	List<MenuResponse> listaMenus;
	
	List<Item>listaItems;
	
	public List<MenuResponse> armarMenu(RolSeg rol) throws Exception {
		listaSubmenus=submenuRolService.buscarOpcionesRol(rol);
		listaMenus=new ArrayList<>();
		for(SubmenuRol submenuRol:listaSubmenus){
			menuResponse=new MenuResponse();
			menuResponse.setIcon(submenuRol.getSubmenu().getIcon());
			menuResponse.setLabel(submenuRol.getSubmenu().getLabel());
			menuResponse.setLink(submenuRol.getSubmenu().getLink());
			
			listaItems=new ArrayList<>();
			
			for(ItemRol i: submenuRol.getItemRol()) {
				listaItems.add(i.getItem());
			}
			menuResponse.setSubMenus(listaItems);
			listaMenus.add(menuResponse);
		}
			
			
		return listaMenus;
	}
	
	public void eliminarOpciones(RolSeg Rol) throws Exception {
			
		
		for(SubmenuRol submenuRol:Rol.getSubmenuRoles()) {
			
				iItemRolService.eliminarItemsPorSubmenu(submenuRol);;
			
		}
		submenuRolService.eliminarSubmenuPorRol(Rol);
	}
}

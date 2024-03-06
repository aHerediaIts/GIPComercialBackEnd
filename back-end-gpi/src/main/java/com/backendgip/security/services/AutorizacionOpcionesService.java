package com.backendgip.security.services;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backendgip.security.models.ItemRol;
import com.backendgip.security.models.OpcionesAccesoRol;


@Service
public class AutorizacionOpcionesService {

	@Autowired
	IItemRolService itemRolService;
	
	public List<OpcionesAccesoRol> obtenerOpcionesRol(String rol) throws Exception{	
		ArrayList<OpcionesAccesoRol> opcionesAccesoRolLista=new  ArrayList<>();   
		OpcionesAccesoRol  opcionesAccesoRol;		
     	List<ItemRol>ListaRoles= itemRolService.listar();
		
		for(ItemRol itemRol:ListaRoles) {
			if(itemRol.getSubmenuRol().getRol().getRolNombre().equals(rol)) {
				opcionesAccesoRol =new OpcionesAccesoRol();
				opcionesAccesoRol.setNombreRol(itemRol.getSubmenuRol().getRol().getRolNombre());
				opcionesAccesoRol.setOpcion(itemRol.getItem().getLink());
				opcionesAccesoRolLista.add(opcionesAccesoRol);
			}
			
		}
		
		return opcionesAccesoRolLista;
	}
	
	
}

package com.backendgip.security.services;

import java.util.List;
import com.backendgip.security.models.RolSeg;
import com.backendgip.security.models.SubmenuRol;



public interface ISubmenuRolService {
	public SubmenuRol guardar(SubmenuRol submenuRol) throws Exception;
	
	public List<SubmenuRol> Listar() throws Exception;
	
	public List<SubmenuRol> buscarOpcionesRol(RolSeg rol) throws Exception;
	
	public void eliminarSubmenuPorRol(RolSeg rol) throws Exception;	
}

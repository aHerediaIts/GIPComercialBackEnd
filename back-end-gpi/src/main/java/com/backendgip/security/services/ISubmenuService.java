package com.backendgip.security.services;
import java.util.List;
import com.backendgip.security.models.Submenu;


public interface ISubmenuService {
    
	public Submenu guardar(Submenu submenu) throws Exception;
	public List<Submenu> Listar() throws Exception;
	
}

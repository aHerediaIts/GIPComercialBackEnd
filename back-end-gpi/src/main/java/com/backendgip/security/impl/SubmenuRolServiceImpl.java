package com.backendgip.security.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backendgip.security.dao.ISubmenuRolDao;
import com.backendgip.security.models.RolSeg;
import com.backendgip.security.models.SubmenuRol;
import com.backendgip.security.services.ISubmenuRolService;





@Service
public class SubmenuRolServiceImpl implements ISubmenuRolService {


	@Autowired
	private ISubmenuRolDao submenuRolDao;
	
	@Override
	public SubmenuRol guardar(SubmenuRol submenuRol) throws Exception {
		return submenuRolDao.save(submenuRol);
	}


	@Override
	public List<SubmenuRol> Listar() throws Exception {
		return (List<SubmenuRol>) submenuRolDao.findAll();
	}


	@Override
	public List<SubmenuRol> buscarOpcionesRol(RolSeg rol) throws Exception {
		return submenuRolDao.buscarOpcionesRol(rol);
	}


	@Override
	public void eliminarSubmenuPorRol(RolSeg rol) throws Exception {
		submenuRolDao.eliminarSubmenuPorRol(rol);		
	}
}





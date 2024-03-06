package com.backendgip.security.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backendgip.security.dao.IItemRolDao;
import com.backendgip.security.models.ItemRol;
import com.backendgip.security.models.SubmenuRol;
import com.backendgip.security.services.IItemRolService;


@Service
public class ItemRolImpl implements IItemRolService {


	@Autowired
	IItemRolDao itemRolDao;
	
	@Override
	public List<ItemRol> listar() throws Exception {
		return (List<ItemRol>) itemRolDao.findAll();
	}


	@Override
	public void eliminar(ItemRol itemRol) throws Exception {
		itemRolDao.delete(itemRol);		
	}


	@Override
	public void eliminarItemsPorSubmenu(SubmenuRol submenuRol) throws Exception {
		itemRolDao.eliminarItemsPorSubmenu(submenuRol);
	}
	
}

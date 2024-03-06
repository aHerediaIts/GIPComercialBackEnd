package com.backendgip.security.impl;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backendgip.security.dao.ISubmenuDao;
import com.backendgip.security.models.Submenu;
import com.backendgip.security.services.ISubmenuService;


@Service
public class SubmenuServiceImpl implements ISubmenuService {


	@Autowired
	private ISubmenuDao submenuDao;
	
	@Override
	public Submenu guardar(Submenu submenu) throws Exception {
		return submenuDao.save(submenu);
	}


	@Override
	public List<Submenu> Listar() throws Exception {


		return (List<Submenu>) submenuDao.findAll();
	}


}

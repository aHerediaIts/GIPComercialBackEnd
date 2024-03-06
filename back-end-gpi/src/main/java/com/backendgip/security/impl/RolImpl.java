package com.backendgip.security.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backendgip.security.dao.IRolDao;
import com.backendgip.security.models.RolSeg;
import com.backendgip.security.services.IRolService;



@Service
public class RolImpl implements IRolService {


	@Autowired
	IRolDao rolDao;
	
	@Override
	public List<RolSeg> listarRoles() throws Exception {
		return rolDao.findAll();
	}


	@Override
	public RolSeg guardarRol(RolSeg rol) throws Exception {
		return rolDao.save(rol);
	}


	@Override
	public RolSeg findByRolNombre(String nombreRol) throws Exception {
		return rolDao.findByRolNombre(nombreRol);
	}


}

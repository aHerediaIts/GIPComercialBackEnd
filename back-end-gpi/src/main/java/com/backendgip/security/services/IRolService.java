package com.backendgip.security.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.backendgip.security.models.RolSeg;


@Service
public interface IRolService {

	public List<RolSeg> listarRoles() throws Exception;
	public RolSeg guardarRol(RolSeg rol) throws Exception;
	public RolSeg findByRolNombre(String nombreRol) throws Exception;
}

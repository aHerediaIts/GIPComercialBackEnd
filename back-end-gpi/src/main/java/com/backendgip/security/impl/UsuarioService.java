package com.backendgip.security.impl;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.backendgip.security.dao.IUsuarioDao;
import com.backendgip.security.models.Usuario;
import com.backendgip.security.services.IUsuarioService;


@Service
public class UsuarioService implements IUsuarioService{
	
	@Autowired
	private IUsuarioDao usuarioDao;


	@Override
	public Usuario guardarUsuario(Usuario usuario) throws Exception {
		return usuarioDao.save(usuario);
	}

	@Override
	public Usuario buscaPorEmpleadoAsociado(Integer idEmpleado) throws UsernameNotFoundException{ 
		return usuarioDao.findByEmpleadoAsociadoId(idEmpleado);
	}


	@Override
	public ArrayList<Usuario> listar() throws Exception {
		return (ArrayList<Usuario>) usuarioDao.findAll();
	}


	@Override
	public void eliminar(Usuario usuario){	
		usuarioDao.delete(usuario);
	}	


}

package com.backendgip.security.services;
import java.util.ArrayList;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.backendgip.security.models.Usuario;


public interface IUsuarioService {
	
	public Usuario guardarUsuario(Usuario usuario) throws Exception;

	public Usuario buscaPorEmpleadoAsociado(Integer idEmpleado) throws UsernameNotFoundException;
	
	public ArrayList<Usuario> listar() throws Exception;
	
	public void eliminar(Usuario usuario);


}

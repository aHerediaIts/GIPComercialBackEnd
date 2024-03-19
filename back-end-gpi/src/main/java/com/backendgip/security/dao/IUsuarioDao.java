package com.backendgip.security.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import com.backendgip.security.models.Usuario;



public interface IUsuarioDao extends JpaRepository<Usuario, Long> {
	public Usuario findByUsername(String username);
	public Usuario findByEmpleadoAsociadoId(Integer idEmpleado);
}



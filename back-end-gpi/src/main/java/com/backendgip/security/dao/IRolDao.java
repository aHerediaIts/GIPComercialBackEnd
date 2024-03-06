package com.backendgip.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.backendgip.security.models.RolSeg;


public interface IRolDao extends JpaRepository<RolSeg, Long>{
	public RolSeg findByRolNombre(String username);
}

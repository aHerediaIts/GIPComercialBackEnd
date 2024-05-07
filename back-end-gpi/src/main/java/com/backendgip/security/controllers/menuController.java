package com.backendgip.security.controllers;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backendgip.security.models.MenuResponse;
import com.backendgip.security.models.Usuario;
import com.backendgip.security.services.ArmaMenuRol;
import com.backendgip.security.services.IUsuarioService;

import javax.servlet.http.HttpServletRequest;



@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api/menu")
public class menuController {
	
	 private static final Logger logger = LoggerFactory.getLogger(menuController.class);
	 
	 @Autowired
	 private IUsuarioService usuarioService;
	 
	 @Autowired
	 private ArmaMenuRol armaMenu;
	

	@GetMapping("/consultarMenu/{idEmpleado}")
	public ResponseEntity<?> consultarMenu(HttpServletRequest request, @PathVariable Integer idEmpleado){

		HashMap<String, Object>response= new HashMap<String, Object>();
		List<MenuResponse> menuResponse =new ArrayList<>();
		Usuario usuario;

		try {
			if(idEmpleado!=null) {
			usuario=usuarioService.buscaPorEmpleadoAsociado(idEmpleado);

			menuResponse=armaMenu.armarMenu(usuario.getUsuarioRoles().get(0).getRol());
			if(menuResponse.size()==0) {
				response.put("error","Usuario sin opciones asignadas");
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND) ;
			}
			}else {
				response.put("error","Usuario no encontrado");
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("mensaje","Error interno");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR) ;
			
		}
		return ResponseEntity.ok(menuResponse.toString());
	}

}

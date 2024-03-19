package com.backendgip.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backendgip.security.models.ItemRol;
import com.backendgip.security.models.RolSeg;
import com.backendgip.security.models.Submenu;
import com.backendgip.security.services.ArmaMenuRol;
import com.backendgip.security.services.IRolService;
import com.backendgip.security.services.ISubmenuService;
import com.backendgip.security.services.IUsuarioService;

import javax.servlet.http.HttpServletRequest;




@RestController
@RequestMapping("/api/gestionUsuariosRoles")
public class GestionUsuariosRolesController {


	@Autowired
	IUsuarioService usuarioService;




/* 
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	EnviarCorreo enviarCorreoService;
	@Autowired
	GenerarCadenaAleatoria generaCadenaAleatoria;
    */
	
	@Autowired
	IRolService iRolService;
	
	@Autowired
	ISubmenuService submenuService; 
	
	@Autowired
	ArmaMenuRol armaMenuRolService;
	
	//@Value("${spring.linkProductivo}")
	//String link;
	

/* 
	@PostMapping("/crearUsuario_Rl")
	public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario,HttpServletRequest request) {


		System.out.println(usuario);
		Usuario usuarioNuevo = null;
		Map<String, Object> response = new HashMap<>();
		String claveGenerada = "";
		try {
				usuarioNuevo = usuarioService.buscarPorusername(usuario.getUsername());
				if (usuarioNuevo != null && usuarioNuevo.getIdUsuario() != 0 && usuario.getIdUsuario()==0) {
					response.put("error", "Este usuario ya se encuentra registrado");
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
				}
				
				if(usuarioNuevo==null) {
					claveGenerada = generaCadenaAleatoria.generarCadenaAleatoria(8);
					usuario.setPassword(passwordEncoder.encode(claveGenerada));
					usuario.setCambiarClave(true);
					usuario.setFechaCambioclave(LocalDateTime.now());
					usuario.setFechaCreacion(LocalDateTime.now());
				}else {
					usuario.setPassword(usuarioNuevo.getPassword());
				}
				usuario.setApellido(usuario.getApellido()==null?"":usuario.getApellido());
				usuario.getUsuarioRoles().get(0).setUsuario(usuario);
				usuarioNuevo = usuarioService.guardarUsuario(usuario);
				
				if(!claveGenerada.isEmpty()) {
					Map<String,Object> parametrosCorreo=new HashMap<>();
			        parametrosCorreo.put("nombreApellido",   usuario.getNombre() +" "+usuario.getApellido());
			        parametrosCorreo.put("nombreUsuario",usuario.getUsername() );
			        parametrosCorreo.put("clave",claveGenerada);
			        parametrosCorreo.put("url",link);
					
			        enviarCorreoService.envioMailSinAdjunto(parametrosCorreo, usuario.getCorreo(), "correoBienvenidaTemplate", "Bienvenido a Coper");
				}


		} catch (Exception e) {
			if(!claveGenerada.isEmpty()) {
				response.put("mensaje", "Error al enviar el correo, favor verificar.");
				response.put("error", "Correo no válido");
				usuarioService.eliminar(usuarioNuevo);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}


		response.put("mensaje", "El usuario ha sido creado con éxito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}


	@GetMapping("/listarUsuarios")
	public ResponseEntity<?> listar() {


		try {
			List<Usuario>usuarios=usuarioService.listar();
			usuarios.forEach(usuario->{	
				usuario.setPassword(null);
				usuario.getTipoUsuario().setRoles(null);
				usuario.getTipoUsuario().setUsuario(null);
				usuario.getUsuarioRoles().forEach(usuarioRol->{				
					usuarioRol.setUsuario(null);
					usuarioRol.getRol().setUsuarioRoles(null);
					usuarioRol.getRol().setSubmenuRoles(null);
					usuarioRol.getRol().setTipoUsuario(null);
				});
			});
			return ResponseEntity.ok(usuarios);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	


	@PostMapping("/cambiarContrasena")
	public ResponseEntity<?> cambiarContrasena() {


		Usuario usuarioNuevo = null;
		Map<String, Object> response = new HashMap<>();
		String claveGenerada = "NataliaSofia1";
		try {
				usuarioNuevo = usuarioService.buscarPorusername("AHeredia");
				usuarioNuevo.setPassword(passwordEncoder.encode(claveGenerada));
				usuarioNuevo = usuarioService.guardarUsuario(usuarioNuevo);


		} catch (Exception e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}


		response.put("mensaje", "El usuario ha sido creado con éxito!");
		// response.put("usuario", usuarioNuevo);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}*/
	
	
	@PostMapping("/crearRol")
	public ResponseEntity<?> crearRol(@RequestBody RolSeg rol) {


		System.out.println(rol);
		RolSeg rolNuevo = null;
		Map<String, Object> response = new HashMap<>();


		try {
			rolNuevo = iRolService.findByRolNombre(rol.getRolNombre());
				if (rolNuevo != null && rolNuevo.getRolId() != 0 && rol.getRolId()==null) {
					response.put("error", "Este Rol ya se encuentra registrado");
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
				}
				
				if(rolNuevo!=null && rolNuevo.getRolId()!=0) {
					armaMenuRolService.eliminarOpciones(rolNuevo);
				}
				rol.getSubmenuRoles().forEach(subMenuRol -> {
					subMenuRol.setRol(rol);
					subMenuRol.setItemRol(new ArrayList<ItemRol>());
					subMenuRol.getSubmenu().getItems().forEach(Item ->{
						System.out.println(Item);
						ItemRol itemRol =new ItemRol();
						itemRol.setItem(Item);
						itemRol.setSubmenuRol(subMenuRol);
						subMenuRol.getItemRol().add(itemRol);
						//ItemRol.setSubmenu(subMenuRol);
					});
			        });
				
				System.out.println(rol);
				rolNuevo = iRolService.guardarRol(rol);


		} catch (Exception e) {
			e.printStackTrace();
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}


		response.put("mensaje", "El usuario ha sido creado con éxito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	@GetMapping("/listarRoles")
	public ResponseEntity<?> listarRoles() {
	    try {
	        List<RolSeg> roles = iRolService.listarRoles();
	        
	        roles.forEach(rol->{
	        	rol.getSubmenuRoles().forEach(submenu->{
	        		submenu.getItemRol().forEach(itemRol->{
	        			itemRol.setSubmenuRol(null);
	        		});
	        		submenu.setRol(null);	        		
	        	});
	        	rol.setUsuarioRoles(null);
	        });
	        System.out.println("----------> "+roles);
	        return ResponseEntity.ok(roles);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener la lista de roles");
	    }
	}
	
	@GetMapping("/listarMenu")
	public ResponseEntity<?> listarMenu() {
		
		try {
	        List<Submenu> submenu = submenuService.Listar();
	        
	        submenu.forEach(sub->{

				if(sub.getLabel().equalsIgnoreCase("INICIO")){
					sub.setSeleccionado(true);
				}

	        	sub.getItems().forEach(item->{
	        		item.setSubmenu(null);
	        	});
	        	
	        });
	        
	        return ResponseEntity.ok(submenu);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener las opciones de menu");
	    }
		
	}
	
	

	
}







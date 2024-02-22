//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.controller;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.Empleado;
import com.backendgip.model.EmpleadoRol;
import com.backendgip.repository.EmpleadoRepository;
import com.backendgip.repository.EmpleadoRolRepository;
import com.backendgip.service.EmpleadoRolService;
import com.backendgip.service.RolService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
@RequestMapping({ "/api/usuarios" })
public class EmpleadoRolController {
	@Autowired
	private EmpleadoRolService empRolService;
	@Autowired
	private EmpleadoRepository empleadoRepository;
	@Autowired
	private EmpleadoRolRepository empRolRepository;
	@Autowired
	private RolService rolService;

	public EmpleadoRolController() {
	}

	@GetMapping({ "/roles-empleado" })
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok(this.empRolService.findAll());
	}

	@PostMapping({ "/roles-empleado" })
	public ResponseEntity<?> save(@RequestBody EmpleadoRol rol) {
		if (this.empRolRepository.existsByEmpleadoAndRol(rol.getEmpleado(), rol.getRol())) {
			return ResponseEntity.badRequest().body("El empleado ya tiene agregado ese rol");
		} else {
			return this.empRolRepository.findByEmpleado(rol.getEmpleado()).size() >= 1
					? ResponseEntity.badRequest().body("El empleado ya tiene un rol asignado")
					: ResponseEntity.ok(this.empRolService.save(rol));
		}
	}

	@GetMapping({ "/roles-empleado/{id}" })
	public ResponseEntity<?> findById(@PathVariable Integer id) {
		return ResponseEntity.ok(this.empRolService.findById(id));
	}

	@GetMapping({ "/roles-empleado/empleado/{idEmpleado}" })
	public ResponseEntity<?> findByEmpleado(@PathVariable Integer idEmpleado) {
		Empleado empleado = (Empleado) this.empleadoRepository.findById(idEmpleado).orElseThrow(() -> {
			return new ResourceNotFoundException("Empleado no encontrado con id: " + idEmpleado);
		});
		return ResponseEntity.ok(this.empRolService.findByEmpleado(empleado));
	}

	@DeleteMapping({ "/roles-empleado/{id}" })
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		EmpleadoRol rol = this.empRolService.findById(id);
		this.empRolService.delete(rol);
		Map<String, Boolean> response = new HashMap();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

	@GetMapping({ "/roles-empleado/rol/{idRol}" })
	public ResponseEntity<?> findByCargo(@PathVariable Integer idRol) {
		List<Empleado> empleados = new ArrayList();
		List<EmpleadoRol> empConRol = this.empRolService.findByRol(this.rolService.findById(idRol));
		Iterator var4 = empConRol.iterator();

		while (var4.hasNext()) {
			EmpleadoRol e = (EmpleadoRol) var4.next();
			empleados.add(e.getEmpleado());
		}

		return ResponseEntity.ok(empleados);
	}
}

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.controller;

import com.backendgip.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
@RequestMapping({ "/api/usuarios" })
public class RolController {
	@Autowired
	private RolService rolService;

	public RolController() {
	}

	@GetMapping({ "/roles" })
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok(this.rolService.findAll());
	}
}

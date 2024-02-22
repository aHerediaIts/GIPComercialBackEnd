//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.controller;

import com.backendgip.dto.JwtDto;
import com.backendgip.dto.LoginUsuario;
import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.jwt.JwtProvider;
import com.backendgip.model.Empleado;
import com.backendgip.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping({"/api/auth"})
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private EmpleadoService empleadoService;

    public AuthController() {
    }

    @PostMapping({"/login"})
    @Transactional(timeout = 500)
    public ResponseEntity<JwtDto> login(@RequestBody LoginUsuario login) {
        String username = login.getNombreUsuario().split("@")[0];
        String domain = login.getNombreUsuario().split("@")[1];
        String email = username+"@"+domain;

        Empleado empleado = this.findEmpleadoByNombreUsuario(email);
        if (empleado == null) {
            domain =  "ITSTRATEGYS.COM";
            email = username+"@"+domain;
            empleado = Optional.ofNullable(this.findEmpleadoByNombreUsuario(email))
            .orElseThrow(ResourceNotFoundException::new);
        }

        Authentication authentication = this.authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(email, email));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = this.jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities(), empleado);
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }

    public Empleado findEmpleadoByNombreUsuario(String nombreUsuario) {
        return (Empleado) this.empleadoService.getByNombreUsuario(nombreUsuario).orElse(null);
    }
}

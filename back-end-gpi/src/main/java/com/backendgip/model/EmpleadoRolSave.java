package com.backendgip.model;


import java.util.List;

import com.backendgip.security.models.UsuarioRol;

public class EmpleadoRolSave {
    public Empleado empleado;
    public List<UsuarioRol> usuarioRoles;
    public Integer idRol;
    public Integer usuarioRolId;

    public EmpleadoRolSave() {
    }

    public EmpleadoRolSave(Empleado empleado, List<UsuarioRol> usuarioRoles) {
        this.empleado = empleado;
        this.usuarioRoles = usuarioRoles;
    }
    
    public Empleado getEmpleado() {
        return empleado;
    }
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
    public List<UsuarioRol> getUsuarioRoles() {
        return usuarioRoles;
    }
    public void setUsuarioRoles(List<UsuarioRol> usuarioRoles) {
        this.usuarioRoles = usuarioRoles;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public Integer getUsuarioRolId() {
        return usuarioRolId;
    }

    public void setUsuarioRolId(Integer usuarioRolId) {
        this.usuarioRolId = usuarioRolId;
    }

    

    

    
}

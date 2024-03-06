package com.backendgip.security.models;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "rolesSeg")
public class RolSeg {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rolId;
    private String rolNombre;
    
    private String rolDescripcion;


    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "rol")
    private Set<UsuarioRol> usuarioRoles = new HashSet<>();
    
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "rol")
   // @JsonIgnore
    private List<SubmenuRol> submenuRoles;
      
    public RolSeg(){

    }


    public RolSeg(Long rolId, String rolNombre,String rolDescripcion) {
        this.rolId = rolId;
        this.rolNombre = rolNombre;
        this.rolDescripcion=rolDescripcion;
    }


    public Long getRolId() {
        return rolId;
    }


    public void setRolId(Long rolId) {
        this.rolId = rolId;
    }


    public String getRolNombre() {
        return rolNombre;
    }


    public void setRolNombre(String rolNombre) {
        this.rolNombre = rolNombre;
    }


    public Set<UsuarioRol> getUsuarioRoles() {
        return usuarioRoles;
    }


    public void setUsuarioRoles(Set<UsuarioRol> usuarioRoles) {
        this.usuarioRoles = usuarioRoles;
    }


	public List<SubmenuRol> getSubmenuRoles() {
		return submenuRoles;
	}


	public void setSubmenuRoles(ArrayList<SubmenuRol> submenuRoles) {
		this.submenuRoles = submenuRoles;
	}


	public String getRolDescripcion() {
		return rolDescripcion;
	}


	public void setRolDescripcion(String rolDescripcion) {
		this.rolDescripcion = rolDescripcion;
	}

	@Override
	public String toString() {
		return "Rol [rolId=" + rolId + ", rolNombre=" + rolNombre + ", submenuRoles=" + submenuRoles + "]";
	}
    
    
    
}



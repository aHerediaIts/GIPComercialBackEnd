package com.backendgip.security.models;
import javax.persistence.*;


@Entity
public class UsuarioRol {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long usuarioRolId;
   @ManyToOne(fetch = FetchType.EAGER)
   private Usuario usuario;
   @ManyToOne
   private RolSeg rol;
   public Long getUsuarioRolId() {
       return usuarioRolId;
   }
   public void setUsuarioRolId(Long usuarioRolId) {
       this.usuarioRolId = usuarioRolId;
   }
   public Usuario getUsuario() {
       return usuario;
   }
   public void setUsuario(Usuario usuario) {
       this.usuario = usuario;
   }
   public RolSeg getRol() {
       return rol;
   }
   public void setRol(RolSeg rol) {
       this.rol = rol;
   }
	@Override
	public String toString() {
		return "UsuarioRol [usuarioRolId=" + usuarioRolId + ", rol=" + rol + "]";
	}
}

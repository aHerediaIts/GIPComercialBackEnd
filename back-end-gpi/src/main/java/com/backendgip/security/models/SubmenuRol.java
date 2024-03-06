package com.backendgip.security.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;



@Entity
public class SubmenuRol {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;


	    @ManyToOne(fetch = FetchType.EAGER)
	    private RolSeg rol;


	    @ManyToOne
	    private Submenu submenu;
	    
	    
	    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "submenuRol")
	  //  @JsonIgnore
	    private List<ItemRol> itemRol;


		public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}


		public RolSeg getRol() {
			return rol;
		}


		public void setRol(RolSeg rol) {
			this.rol = rol;
		}


		public Submenu getSubmenu() {
			return submenu;
		}


		public void setSubmenu(Submenu submenu) {
			this.submenu = submenu;
		}


		public List<ItemRol> getItemRol() {
			return itemRol;
		}


		public void setItemRol(List<ItemRol> itemRol) {
			this.itemRol = itemRol;
		}


		@Override
		public String toString() {
			return "SubmenuRol [id=" + id + ", submenu= "+ submenu +" ]";
		}




	    
	    


}





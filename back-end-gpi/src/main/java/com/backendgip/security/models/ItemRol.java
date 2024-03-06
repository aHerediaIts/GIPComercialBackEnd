package com.backendgip.security.models;



import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class ItemRol {


	
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;


	    @ManyToOne(fetch = FetchType.EAGER)
	    private Item item;


	    @ManyToOne
	    private SubmenuRol submenuRol;


		public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}


		public Item getItem() {
			return item;
		}


		public void setItem(Item item) {
			this.item = item;
		}


		public SubmenuRol getSubmenuRol() {
			return submenuRol;
		}


		public void setSubmenuRol(SubmenuRol submenuRol) {
			this.submenuRol = submenuRol;
		}


	/*	@Override
		public String toString() {
			return "ItemRol [id=" + id + ", item=" + item + "]";
		}*/
	       


	
}

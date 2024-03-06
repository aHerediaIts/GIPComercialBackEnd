package com.backendgip.security.models;



import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="submenu")
public class Submenu {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	@Column(name="label")
	
	private String label;
	@Column(name="icon")
	private String icon;
	
	@Column(name="link")
	private String link;
	
	
	@OneToMany(mappedBy = "submenu", cascade = CascadeType.ALL)
    private List<Item> Items;
	
	@Transient
	private boolean seleccionado; 

	/* 
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "submenu")
    @JsonIgnore
    private List<SubmenuRol> submenuRoles;
*/

	public Submenu() {


	}


	public Submenu(Long id, String label, String icon, String link) {
		super();
		this.id = id;
		this.label = label;
		this.icon = icon;
		this.link = link;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getLabel() {
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}


	public String getIcon() {
		return icon;
	}


	public void setIcon(String icon) {
		this.icon = icon;
	}


	public String getLink() {
		return link;
	}


	public void setLink(String link) {
		this.link = link;
	}


	public List<Item> getItems() {
		return Items;
	}


	public void setItems(List<Item> items) {
		Items = items;
	}


	
	public boolean isSeleccionado() {
		return seleccionado;
	}


	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}


	@Override
	public String toString() {
		return "Submenu [Id=" + id + ", label=" + label + ", icon=" + icon + ", link=" + link + ", Items=" + Items
				+ "]";
	}	
	
	
}





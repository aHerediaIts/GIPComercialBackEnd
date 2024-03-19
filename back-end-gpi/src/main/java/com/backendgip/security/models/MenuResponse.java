package com.backendgip.security.models;
import java.util.List;
public class MenuResponse {
	
	String label;
	String icon;
	String link;
	List<Item> subMenus;
		
	public MenuResponse() {
	}
	public MenuResponse(String label, String icon, List<Item> subMenus ,String link) {
		this.label = label;
		this.icon = icon;
		this.subMenus = subMenus;
		this.link=link;
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
	public List<Item> getSubMenus() {
		return subMenus;
	}
	public void setSubMenus(List<Item> subMenus) {
		this.subMenus = subMenus;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
	@Override
	public String toString() {
		String retorno="{\"label\":\"" + label + "\", \"icon\":\"" + icon + "\", \"link\":\"" + link+"\"";
		//return retorno +=(subMenus.size()>0)? ", \"subMenus\":[{\"subMenuItems\":" + subMenus + "}]}":"}";			
		
		if (subMenus.size() > 0) {
			retorno += ", \"subItems\":[";
			for (int i = 0; i < subMenus.size(); i++) {
				retorno += subMenus.get(i).toString(); 
				if (i < subMenus.size() - 1) {
					retorno += ","; 
				}
			}
			retorno += "]";
		}
	
		retorno += "}";
		return retorno;

	}
	
}





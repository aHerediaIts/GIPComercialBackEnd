package com.backendgip.security.models;

public class OpcionesAccesoRol {
	
	String opcion;
	String nombreRol;
	
	public OpcionesAccesoRol() {	
		
	}
	
	public OpcionesAccesoRol(String opcion, String nombreRol) {	
		this.opcion = opcion;
		this.nombreRol = nombreRol;
	}
	
	public String getOpcion() {
		return opcion;
	}
	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}
	public String getNombreRol() {
		return nombreRol;
	}
	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}
}

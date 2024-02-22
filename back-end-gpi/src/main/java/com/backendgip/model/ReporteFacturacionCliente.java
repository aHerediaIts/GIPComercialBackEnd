//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.model;

public class ReporteFacturacionCliente {
	private String cliente;
	private String proyecto;
	private String descripcion;
	private Integer nPagos;
	private Integer porcentaje;
	private Integer precio;
	private String fechaPlaneada;
	private String estado;
	private String fechaPago;
	private Integer valorAprobado;
	private Integer valorCobrado;
	private Integer facturasPagas;
	private Integer facturasPendientes;

	public ReporteFacturacionCliente() {
	}

	public String getCliente() {
		return this.cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getProyecto() {
		return this.proyecto;
	}

	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}

	public String getdescripcion() {
		return this.descripcion;
	}

	public void setdescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getnPagos() {
		return this.nPagos;
	}

	public void setnPagos(Integer nPagos) {
		this.nPagos = nPagos;
	}

	public Integer getPorcentaje() {
		return this.porcentaje;
	}

	public void setPorcentaje(Integer porcentaje) {
		this.porcentaje = porcentaje;
	}

	public Integer getPrecio() {
		return this.precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

	public String getFechaPlaneada() {
		return this.fechaPlaneada;
	}

	public void setFechaPlaneada(String fechaPlaneada) {
		this.fechaPlaneada = fechaPlaneada;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getFechaPago() {
		return this.fechaPago;
	}

	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}

	public Integer getValorAprobado() {
		return this.valorAprobado;
	}

	public void setValorAprobado(Integer valorAprobado) {
		this.valorAprobado = valorAprobado;
	}

	public Integer getValorCobrado() {
		return this.valorCobrado;
	}

	public void setValorCobrado(Integer valorCobrado) {
		this.valorCobrado = valorCobrado;
	}

	public Integer getFacturasPagas() {
		return this.facturasPagas;
	}

	public void setFacturasPagas(Integer facturasPagas) {
		this.facturasPagas = facturasPagas;
	}

	public Integer getFacturasPendientes() {
		return this.facturasPendientes;
	}

	public void setFacturasPendientes(Integer facturasPendientes) {
		this.facturasPendientes = facturasPendientes;
	}

	public String toString() {
		return "ReporteFacturacionCliente [cliente=" + this.cliente + ", proyecto=" + this.proyecto + ", nPagos="
				+ this.nPagos + ", porcentaje=" + this.porcentaje + ", precio=" + this.precio + ", fechaPlaneada="
				+ this.fechaPlaneada + ", estado=" + this.estado + ", fechaPago=" + this.fechaPago + ", valorAprobado="
				+ this.valorAprobado + ", valorCobrado=" + this.valorCobrado + ", facturasPagas=" + this.facturasPagas
				+ ", facturasPendientes=" + this.facturasPendientes + "]";
	}
}

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.model;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "facturacion")
public class Facturacion implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "pk_facturacion")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "porcentaje_indicador")
	private Integer porcentaje;
	@Column(name = "fecha_planeada")
	private LocalDate fechaPlaneada;
	@Column(name = "fecha_Pago")
	private LocalDate fechaPago;
	@Column(name = "valor_pagar")
	private Integer valorPagar;
	@Column(name = "valor_pagado")
	private Integer valorPagado;
	@ManyToOne
	@JoinColumn(name = "fk_estado_factura")
	private EstadoFactura estado;
	@ManyToOne
	@JoinColumn(name = "fk_proyecto")
	private Proyecto proyecto;

	public Facturacion() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPorcentaje() {
		return this.porcentaje;
	}

	public void setPorcentaje(Integer porcentaje) {
		this.porcentaje = porcentaje;
	}

	public LocalDate getFechaPlaneada() {
		return this.fechaPlaneada;
	}

	public void setFechaPlaneada(LocalDate fechaPlaneada) {
		this.fechaPlaneada = fechaPlaneada;
	}

	public LocalDate getFechaPago() {
		return this.fechaPago;
	}

	public void setFechaPago(LocalDate fechaPago) {
		this.fechaPago = fechaPago;
	}

	public Integer getValorPagar() {
		return this.valorPagar;
	}

	public void setValorPagar(Integer valorPagar) {
		this.valorPagar = valorPagar;
	}

	public Integer getValorPagado() {
		return this.valorPagado;
	}

	public void setValorPagado(Integer valorPagado) {
		this.valorPagado = valorPagado;
	}

	public EstadoFactura getEstado() {
		return this.estado;
	}

	public void setEstado(EstadoFactura estado) {
		this.estado = estado;
	}

	public Proyecto getProyecto() {
		return this.proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	public String toString() {
		return "Facturacion [id=" + this.id + ", porcentaje=" + this.porcentaje + ", fechaPlaneada="
				+ this.fechaPlaneada + ", fechaPago=" + this.fechaPago + ", valorPagar=" + this.valorPagar
				+ ", valorPagado=" + this.valorPagado + ", estado=" + this.estado + ", proyecto=" + this.proyecto + "]";
	}
}

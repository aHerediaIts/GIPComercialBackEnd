
package com.backendgip.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "parametria_recursos")
public class ParametriaRecursosMatrizTiempo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "pk_parametria_recursos")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_empleado")
    private Empleado empleado;

    @Column(name = "tarifa_hora")
    private Integer tarifaHora;

    @Column(name = "tarifa_mensual")
    private Integer tarifaMensual;
    
    @ManyToOne
    @JoinColumn(name = "fk_cliente")
    private Cliente cliente;


    public ParametriaRecursosMatrizTiempo() {
    }

    public ParametriaRecursosMatrizTiempo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Integer getTarifaHora() {
        return tarifaHora;
    }

    public void setTarifaHora(Integer tarifaHora) {
        this.tarifaHora = tarifaHora;
    }

    public Integer getTarifaMensual() {
        return tarifaMensual;
    }

    public void setTarifaMensual(Integer tarifaMensual) {
        this.tarifaMensual = tarifaMensual;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ParametriaRecursosMatrizTiempo(Integer id, Empleado empleado, Integer tarifaHora, Integer tarifaMensual,
            Cliente cliente) {
        this.id = id;
        this.empleado = empleado;
        this.tarifaHora = tarifaHora;
        this.tarifaMensual = tarifaMensual;
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "ParametriaRecursosMatrizTiempo [id=" + id + ", empleado=" + empleado + ", tarifaHora=" + tarifaHora
                + ", tarifaMensual=" + tarifaMensual + ", cliente=" + cliente + "]";
    }
    
}

package com.backendgip.model;

public class ActividadProyectoVencer {
    private Proyecto proyecto;
    private RecursoActividad recursoActividad;

    // Constructor
    public ActividadProyectoVencer(Proyecto proyecto, RecursoActividad recursoActividad) {
        this.proyecto = proyecto;
        this.recursoActividad = recursoActividad;
    }

    // Getters y Setters
    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public RecursoActividad getRecursoActividad() {
        return recursoActividad;
    }

    public void setRecursoActividad(RecursoActividad recursoActividad) {
        this.recursoActividad = recursoActividad;
    }
}

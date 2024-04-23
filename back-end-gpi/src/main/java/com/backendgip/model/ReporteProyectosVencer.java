package com.backendgip.model;

import java.util.List;

public class ReporteProyectosVencer {

 private Proyecto proyecto;
    private RecursoActividad recursoActividad;

    // Constructor
    public ActividadProyectoVencer(Proyecto proyecto, RecursoActividad recursoActividad) {
        this.proyecto = proyecto;
        this.recursoActividad = recursoActividad;
    }

    // Getters y Setters (opcional, dependiendo de tus necesidades)
    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public RecursoActividad getRecursoActividad() {
        return recursoActividad;
    }

    
   
}

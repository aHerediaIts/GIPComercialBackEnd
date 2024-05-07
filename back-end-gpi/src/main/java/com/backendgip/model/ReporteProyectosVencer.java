package com.backendgip.model;

import java.util.List;

public class ReporteProyectosVencer {

 private Proyecto proyecto;
private RecursoActividad recursoActividad;

  
    public Proyecto getProyecto() {
        return proyecto;
    }

    public ReporteProyectosVencer() {
    }

    public ReporteProyectosVencer(Proyecto proyecto, RecursoActividad recursoActividad) {
        this.proyecto = proyecto;
        this.recursoActividad = recursoActividad;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public RecursoActividad getRecursoActividad() {
        return recursoActividad;
    }

    
   
}

package com.backendgip.model;

import java.io.Serializable;
import java.util.List;

public class GeneracionMatrizTiempoRecursos implements Serializable{
    
    
    public List<GeneracionMatrizTiempo> matrizTiempo;
    public List<Generarmatriztiemposasignacionrecursos> recursosAsignados;

    public List<Generarmatriztiemposasignacionrecursos> getRecursosAsignados() {
        return recursosAsignados;
    }
    public void setRecursosAsignados(List<Generarmatriztiemposasignacionrecursos> recursosAsignados) {
        this.recursosAsignados = recursosAsignados;
    }
    public List<GeneracionMatrizTiempo> getMatriztTiempo() {
        return matrizTiempo;
    }
    public void setMatrizTiempo(List<GeneracionMatrizTiempo> matrizTiempo) {
        this.matrizTiempo = matrizTiempo;
    }
    @Override
    public String toString() {
        return "GeneracionMatrizTiempoRecursos [recursosAsignados=" + recursosAsignados + ", matrizGeneral="
                + matrizTiempo + "]";
    }
}
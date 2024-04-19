package com.backendgip.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class ReporteControlHoras {

    private ReporteTiempo reporte;
    private Integer totalHoras;

    public ReporteControlHoras() {
    }

    public ReporteControlHoras(ReporteTiempo reporte, Integer totalHoras) {
        this.reporte = reporte;
        this.totalHoras = totalHoras;
    }

    public ReporteTiempo getReporte() {
        return reporte;
    }

    public void setReporte(ReporteTiempo reporte) {
        this.reporte = reporte;
    }

    public Integer getTotalHoras() {
        return totalHoras;
    }

    public void setTotalHoras(Integer totalHoras) {
        this.totalHoras = totalHoras;
    }

    @Override
    public String toString() {
        return "ReporteControlHoras [reporte=" + reporte + ", totalHoras=" + totalHoras + "]";
    }

    
}

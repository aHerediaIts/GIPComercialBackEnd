package com.backendgip.model;

import java.util.List;

public class ValorTotalRecurso {

    private ParametriaRecursosMatrizTiempo parametriaRecursosMatrizTiempo;
    private ReporteTiempo reporteTiempo;
    private Double total;
    private Integer totalHoras;
    private Integer tarifa;
    

    public ValorTotalRecurso(ParametriaRecursosMatrizTiempo parametriaRecursosMatrizTiempo, ReporteTiempo reporteTiempo,
            Double total, Integer totalHoras, Integer tarifa) {
        this.parametriaRecursosMatrizTiempo = parametriaRecursosMatrizTiempo;
        this.reporteTiempo = reporteTiempo;
        this.total = total;
        this.totalHoras = totalHoras;
        this.tarifa = tarifa;
    }

    public ValorTotalRecurso() {
    }

    public ParametriaRecursosMatrizTiempo getParametriaRecursosMatrizTiempo() {
        return parametriaRecursosMatrizTiempo;
    }

    public void setParametriaRecursosMatrizTiempo(ParametriaRecursosMatrizTiempo parametriaRecursosMatrizTiempo) {
        this.parametriaRecursosMatrizTiempo = parametriaRecursosMatrizTiempo;
    }

    public ReporteTiempo getReporteTiempo() {
        return reporteTiempo;
    }

    public Integer getTotalHoras() {
        return totalHoras;
    }

    public void setTotalHoras(Integer totalHoras) {
        this.totalHoras = totalHoras;
    }

    public void setReporteTiempo(ReporteTiempo reporteTiempo) {
        this.reporteTiempo = reporteTiempo;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getTarifa() {
        return tarifa;
    }

    public void setTarifa(Integer tarifa) {
        this.tarifa = tarifa;
    }

}
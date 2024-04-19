package com.backendgip.model;

import java.util.List;

public class ValorTotalRecurso {

    private  ParametriaRecursosMatrizTiempo parametriaRecursosMatrizTiempo;
    private ReporteTiempo reporteTiempo;
    private Double total;
    public ValorTotalRecurso(ParametriaRecursosMatrizTiempo parametriaRecursosMatrizTiempo, ReporteTiempo reporteTiempo,
            Double total) {
        this.parametriaRecursosMatrizTiempo = parametriaRecursosMatrizTiempo;
        this.reporteTiempo = reporteTiempo;
        this.total = total;
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
    public void setReporteTiempo(ReporteTiempo reporteTiempo) {
        this.reporteTiempo = reporteTiempo;
    }
    public Double getTotal() {
        return total;
    }
    public void setTotal(Double total) {
        this.total = total;
    }

}
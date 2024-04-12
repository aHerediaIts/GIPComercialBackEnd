package com.backendgip.model;
import com.backendgip.model.ReporteTiempo;
import com.backendgip.model.ParametriaRecursosMatrizTiempo;
public class ReporteAnual {
    public ReporteTiempo reporteTiempo;
    public ParametriaRecursosMatrizTiempo parametriaRecursosMatrizTiempo;
    public Integer valor;
    public ReporteTiempo getReporteTiempo() {
        return reporteTiempo;
    }
    public void setReporteTiempo(ReporteTiempo reporteTiempo) {
        this.reporteTiempo = reporteTiempo;
    }
    public ParametriaRecursosMatrizTiempo getParametriaRecursosMatrizTiempo() {
        return parametriaRecursosMatrizTiempo;
    }
    public void setParametriaRecursosMatrizTiempo(ParametriaRecursosMatrizTiempo parametriaRecursosMatrizTiempo) {
        this.parametriaRecursosMatrizTiempo = parametriaRecursosMatrizTiempo;
    }
    public Integer getValor() {
        return valor;
    }
    public void setValor(Integer valor) {
        this.valor = valor;
    }
    @Override
    public String toString() {
        return "ReporteAnual [reporteTiempo=" + reporteTiempo + ", parametriaRecursosMatrizTiempo="
                + parametriaRecursosMatrizTiempo + ", valor=" + valor + "]";
    }

    
}

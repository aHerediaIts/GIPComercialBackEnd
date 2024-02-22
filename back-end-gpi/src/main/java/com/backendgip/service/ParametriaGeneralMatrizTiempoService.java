package com.backendgip.service;
import java.util.List;
import com.backendgip.model.ParametriaGeneralMatrizTiempo;
import com.backendgip.model.Generarmatriztiemposasignacionrecursos;


public interface ParametriaGeneralMatrizTiempoService {

    List<ParametriaGeneralMatrizTiempo> getParametrosGenerales();

    ParametriaGeneralMatrizTiempo saveParametrosGenerales(ParametriaGeneralMatrizTiempo parametriaGeneralMatrizTiempo);

    Generarmatriztiemposasignacionrecursos saveRecursosAsignados(Generarmatriztiemposasignacionrecursos recursosAsignados);

}

package com.backendgip.service;

import java.util.List;
import com.backendgip.model.ParametriaRecursosMatrizTiempo;


public interface ParametriaRecursosMatrizTiempoService {
    List<ParametriaRecursosMatrizTiempo> getParametriaRecursos();

    ParametriaRecursosMatrizTiempo saveParametriaRecursos(ParametriaRecursosMatrizTiempo parametriaRecursosMatrizTiempo);

    void deleteParametriaRecursosById(Integer id);

    ParametriaRecursosMatrizTiempo findById(Integer id);

}

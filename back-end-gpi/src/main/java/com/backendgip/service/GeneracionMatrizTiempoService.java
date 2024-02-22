package com.backendgip.service;

import java.util.List;
import com.backendgip.model.GeneracionMatrizTiempo;
import com.backendgip.model.Generarmatriztiemposasignacionrecursos;
import com.backendgip.model.MatrizTiempo;
import com.backendgip.model.ParametriaGeneralMatrizTiempo;

public interface GeneracionMatrizTiempoService {
    List<ParametriaGeneralMatrizTiempo> getGeneracionTiempos();

    GeneracionMatrizTiempo saveGeneracionTiempos(GeneracionMatrizTiempo generacionMatrizTiempo);
    
    void deleteGeneracionTiemposById(Integer id);

    List<GeneracionMatrizTiempo> findByMatrizTiempo( MatrizTiempo matrizTiempo);

    //List<Generarmatriztiemposasignacionrecursos> searchRecursosAsignados(Integer id);
}

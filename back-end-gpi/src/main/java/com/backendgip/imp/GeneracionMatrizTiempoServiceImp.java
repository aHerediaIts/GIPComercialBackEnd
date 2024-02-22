package com.backendgip.imp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backendgip.model.GeneracionMatrizTiempo;
import com.backendgip.model.Generarmatriztiemposasignacionrecursos;
import com.backendgip.model.MatrizTiempo;
import com.backendgip.model.ParametriaGeneralMatrizTiempo;
import com.backendgip.repository.GeneracionMatrizTiempoRepository;
import com.backendgip.repository.GeneracionmatriztiemposrecursosRepository;
import com.backendgip.repository.ParametriaGeneralMatrizTiempoRepository;
import com.backendgip.service.GeneracionMatrizTiempoService;

@Service
public class GeneracionMatrizTiempoServiceImp implements GeneracionMatrizTiempoService {
    @Autowired
    private GeneracionMatrizTiempoRepository generacionMatrizTiempoRepository;
    private ParametriaGeneralMatrizTiempoRepository parametrosGeneralesMatrizTiempoRepository;
    
    @Override
    public List<ParametriaGeneralMatrizTiempo> getGeneracionTiempos() {
        return (List<ParametriaGeneralMatrizTiempo>) this.parametrosGeneralesMatrizTiempoRepository.findAll();
    }
        @Override
    public List<GeneracionMatrizTiempo> findByMatrizTiempo( MatrizTiempo matrizTiempo) {
        return (List<GeneracionMatrizTiempo>) this.generacionMatrizTiempoRepository.findByMatrizTiempo(matrizTiempo);
    }

    @Override
    public GeneracionMatrizTiempo saveGeneracionTiempos(GeneracionMatrizTiempo generacionMatrizTiempo) {
        return (GeneracionMatrizTiempo) this.generacionMatrizTiempoRepository.save(generacionMatrizTiempo);
    }

    @Override
    public void deleteGeneracionTiemposById(Integer id) {
        this.generacionMatrizTiempoRepository.deleteById(id);
    }


    
/* 
      @Override
    public List<Generarmatriztiemposasignacionrecursos> searchRecursosAsignados(Integer id) {
        return (List<Generarmatriztiemposasignacionrecursos>) this.generacionmatriztiemposrecursosRepository.FindByidRecursosAsignados(id);
    }
*/
}

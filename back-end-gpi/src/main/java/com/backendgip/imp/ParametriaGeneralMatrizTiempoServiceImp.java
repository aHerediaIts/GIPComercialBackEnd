package com.backendgip.imp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backendgip.model.ParametriaGeneralMatrizTiempo;
import com.backendgip.repository.ParametriaGeneralMatrizTiempoRepository;
import com.backendgip.service.ParametriaGeneralMatrizTiempoService;
import com.backendgip.model.Generarmatriztiemposasignacionrecursos;
import com.backendgip.repository.GeneracionmatriztiemposrecursosRepository;
import com.backendgip.repository.GenerarmatriztiempostiporecursosRepository;



@Service
public class ParametriaGeneralMatrizTiempoServiceImp implements ParametriaGeneralMatrizTiempoService {
  @Autowired
  private ParametriaGeneralMatrizTiempoRepository parametrosGeneralesMatrizTiempoRepository;

  @Autowired
  private GeneracionmatriztiemposrecursosRepository generacionmatriztiemposTipoRecursosRepository;
  


  @Override
  public List<ParametriaGeneralMatrizTiempo> getParametrosGenerales() {
    return (List<ParametriaGeneralMatrizTiempo>) this.parametrosGeneralesMatrizTiempoRepository.findAll();
  }

  @Override
  public Generarmatriztiemposasignacionrecursos saveRecursosAsignados(Generarmatriztiemposasignacionrecursos recursosAsignados) {
   return (Generarmatriztiemposasignacionrecursos) this.generacionmatriztiemposTipoRecursosRepository.save(recursosAsignados);
  }

  
  @Override
  public ParametriaGeneralMatrizTiempo saveParametrosGenerales(ParametriaGeneralMatrizTiempo parametrosGeneralesMatrizTiempo){
    return (ParametriaGeneralMatrizTiempo) this.parametrosGeneralesMatrizTiempoRepository.save(parametrosGeneralesMatrizTiempo);
  }

}
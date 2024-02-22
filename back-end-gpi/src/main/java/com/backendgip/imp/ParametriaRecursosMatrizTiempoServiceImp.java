package com.backendgip.imp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.ParametriaRecursosMatrizTiempo;
import com.backendgip.model.Proyecto;
import com.backendgip.repository.ParametriaRecursosMatrizTiempoRepository;
import com.backendgip.service.ParametriaRecursosMatrizTiempoService;



@Service
public class ParametriaRecursosMatrizTiempoServiceImp implements ParametriaRecursosMatrizTiempoService {
  @Autowired
  private ParametriaRecursosMatrizTiempoRepository parametriaRecursosMatrizTiempoRepository;

  @Override
  public List<ParametriaRecursosMatrizTiempo> getParametriaRecursos() {
    return (List<ParametriaRecursosMatrizTiempo>) this.parametriaRecursosMatrizTiempoRepository.findAll();
  }
  
  @Override
  public ParametriaRecursosMatrizTiempo saveParametriaRecursos(ParametriaRecursosMatrizTiempo parametrosGeneralesMatrizTiempo){
    return (ParametriaRecursosMatrizTiempo) this.parametriaRecursosMatrizTiempoRepository.save(parametrosGeneralesMatrizTiempo);
  }

  @Override
  public void deleteParametriaRecursosById(Integer id) {
      this.parametriaRecursosMatrizTiempoRepository.deleteById(id);
  }

  @Override
  public ParametriaRecursosMatrizTiempo findById(Integer id) {
    return (ParametriaRecursosMatrizTiempo)  this.parametriaRecursosMatrizTiempoRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado el componente con el id: " + id);
		});
  }




}
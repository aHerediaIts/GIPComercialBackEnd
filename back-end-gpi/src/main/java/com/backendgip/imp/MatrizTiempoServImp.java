package com.backendgip.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backendgip.service.MatrizTiempoService;
import com.backendgip.model.MatrizTiempo;
import com.backendgip.model.ProjectStatusReport;
import com.backendgip.repository.MatrizTiempoRepository;

@Service
public class MatrizTiempoServImp implements MatrizTiempoService {
  @Autowired
  private MatrizTiempoRepository matrizTiempoRepository;

  @Override
  public MatrizTiempo saveMatrizTiempo(MatrizTiempo matrizTiempo) {
    return (MatrizTiempo) this.matrizTiempoRepository.save(matrizTiempo);
  }

  @Override
  public List<MatrizTiempo> getMatrizTiempo() {
    return (List<MatrizTiempo>) this.matrizTiempoRepository.findAll();

  }
}
package com.backendgip.service;

import java.util.List;

import com.backendgip.model.MatrizTiempo;

public interface MatrizTiempoService {

    MatrizTiempo saveMatrizTiempo(MatrizTiempo matrizTiempo);

    List<MatrizTiempo> getMatrizTiempo();

}

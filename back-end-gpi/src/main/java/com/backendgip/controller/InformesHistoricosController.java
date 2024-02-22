package com.backendgip.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backendgip.model.GeneracionMatrizTiempo;
import com.backendgip.model.ParametriaGeneralMatrizTiempo;
import com.backendgip.service.GeneracionMatrizTiempoService;
import com.backendgip.service.ParametriaGeneralMatrizTiempoService;

@RestController
@Transactional
@RequestMapping({ "/api" })
public class InformesHistoricosController {
    
}
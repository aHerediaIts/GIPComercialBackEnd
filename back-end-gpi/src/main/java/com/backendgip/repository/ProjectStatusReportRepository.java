//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.repository;

import com.backendgip.model.Empleado;
import com.backendgip.model.ProjectStatusReport;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectStatusReportRepository extends CrudRepository<ProjectStatusReport, Integer> {    
    List<ProjectStatusReport> findByEmpleado(Empleado empleado);
    ProjectStatusReport findByFechaCreacionPsrAndCodigo(LocalDateTime fechaCreacion,String codigo);
}

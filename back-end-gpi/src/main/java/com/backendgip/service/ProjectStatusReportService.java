//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.service;

import com.backendgip.model.ProjectStatusReport;

import java.time.LocalDate;
import java.util.List;

public interface ProjectStatusReportService {

    List<ProjectStatusReport> getProjectStatusReports();

    List<ProjectStatusReport> getProjectStatusReportsByFechaInicio(LocalDate fechaInicio, LocalDate fechaFin);

    ProjectStatusReport saveProjectStatusReport(ProjectStatusReport projectstatusreport);

    void deleteProjectStatusReport(ProjectStatusReport projectstatusreport);

    ProjectStatusReport getProjectStatusReportById(Integer id);

    List<ProjectStatusReport> findByEmpleado(Integer id);

    ProjectStatusReport findByFechaCreacionPsrAndCodigo(LocalDate fechaCreacionPsr, String codigo);
    
}

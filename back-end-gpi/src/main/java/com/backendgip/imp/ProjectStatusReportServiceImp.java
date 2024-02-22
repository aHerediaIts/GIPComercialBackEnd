//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.imp;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.Empleado;
import com.backendgip.model.ProjectStatusReport;
import com.backendgip.model.Proyecto;
import com.backendgip.model.ReporteTiempo;
import com.backendgip.repository.ProjectStatusReportRepository;
import com.backendgip.service.ProjectStatusReportService;
import java.util.Iterator;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectStatusReportServiceImp implements ProjectStatusReportService {
  @Autowired
  private ProjectStatusReportRepository psrRepository;

  @Override
  public List<ProjectStatusReport> getProjectStatusReports() {
    return (List) this.psrRepository.findAll();

  }

  @Override
  public List<ProjectStatusReport> getProjectStatusReportsByFechaInicio(LocalDate fechaInicio, LocalDate fechaFin) {

    List<ProjectStatusReport> psr = (List<ProjectStatusReport>) this.psrRepository.findAll();
    List<ProjectStatusReport> psrFiltradasPorFecha = new ArrayList();
    Iterator var7 = psr.iterator();
    while (true) {
      ProjectStatusReport r;
      do {
        if (!var7.hasNext()) {
          return psrFiltradasPorFecha;
        }
        r = (ProjectStatusReport) var7.next();
      } while (!r.getFechaCreacionPsr().toLocalDate().isEqual(fechaFin)
          && !r.getFechaCreacionPsr().toLocalDate().isEqual(fechaInicio)
          && (!r.getFechaCreacionPsr().toLocalDate().isBefore(fechaFin)
              || !r.getFechaCreacionPsr().toLocalDate().isAfter(fechaInicio)));
      psrFiltradasPorFecha.add(r);
    }

  }

  @Override
  public ProjectStatusReport saveProjectStatusReport(ProjectStatusReport projectstatusreport) {
    return (ProjectStatusReport) this.psrRepository.save(projectstatusreport);
  }

  @Override
  public void deleteProjectStatusReport(ProjectStatusReport projectstatusreport) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteProjectStatusReport'");
  }

  @Override
  public ProjectStatusReport getProjectStatusReportById(Integer id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getProjectStatusReportById'");
  }

  @Override
  public List<ProjectStatusReport> findByEmpleado(Integer id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findByEmpleado'");
  }

    @Override
  public ProjectStatusReport findByFechaCreacionPsrAndCodigo(LocalDate fechaCreacionPsr, String codigo) {
    LocalDateTime localDateTimeFechaCreacionPsr = fechaCreacionPsr.atStartOfDay();
    return this.psrRepository.findByFechaCreacionPsrAndCodigo(localDateTimeFechaCreacionPsr, codigo);  
  }

}

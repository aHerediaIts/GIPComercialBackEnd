//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.repository;

import com.backendgip.model.Empleado;
import com.backendgip.model.PSRStatus;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PSRStatusRepository extends CrudRepository<PSRStatus, Integer> {
    List<PSRStatus> findByEmpleado(Empleado empleado);
    List<PSRStatus> findByProjectStatusReportId(Integer id);
    List<PSRStatus> findByProjectStatusReportCodigo(String codigo);
}

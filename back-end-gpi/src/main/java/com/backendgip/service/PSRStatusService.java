//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.service;

import com.backendgip.model.PSRStatus;
import com.backendgip.model.ProjectStatusReport;

import java.util.List;

public interface PSRStatusService {
    List<PSRStatus> getPSRStatus();

    PSRStatus savePSRStatus(PSRStatus projectstatusreportstatus);

    void deletePSRStatus(PSRStatus projectstatusreportstatus);

    void deletePSRStatusById(Integer id);

    PSRStatus getPSRStatusById(Integer id);

    List<PSRStatus> findByProjectStatusReportId(Integer id);

    List<PSRStatus> findByCodigoProyecto(String codigo);


}

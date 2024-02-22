//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.imp;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.PSRStatus;
import com.backendgip.model.ProjectStatusReport;
import com.backendgip.model.Proyecto;
import com.backendgip.service.PSRStatusService;
import com.backendgip.repository.PSRStatusRepository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PSRStatusServiceImp implements PSRStatusService  {
	@Autowired
	private PSRStatusRepository PSRStatusrepository;

    @Override
    public List<PSRStatus> getPSRStatus() {
		return (List) this.PSRStatusrepository.findAll();
    }

    @Override
    public PSRStatus savePSRStatus(PSRStatus PSRStatus) {
        return (PSRStatus) this.PSRStatusrepository.save(PSRStatus);
    }

    @Override
    public void deletePSRStatus(PSRStatus PSRStatus) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletePSRStatus'");
    }

    @Override
    public void deletePSRStatusById(Integer id) {
        this.PSRStatusrepository.deleteById(id);
        //throw new UnsupportedOperationException("Unimplemented method 'deletePSRStatusById'");
    }

    @Override
    public PSRStatus getPSRStatusById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPSRstatusById'");
    }

    @Override
    public List<PSRStatus> findByProjectStatusReportId(Integer id) {
       		return (List<PSRStatus>) this.PSRStatusrepository.findByProjectStatusReportId(id);
    }

    @Override
    public List<PSRStatus> findByCodigoProyecto(String codigo) {
       		return (List<PSRStatus>) this.PSRStatusrepository.findByProjectStatusReportCodigo(codigo);
    }
    
}


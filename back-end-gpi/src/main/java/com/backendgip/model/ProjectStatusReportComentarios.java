package com.backendgip.model;

public class ProjectStatusReportComentarios {

	public ProjectStatusReport projectStatusReport;
    public PSRStatus psrStatus ;
	public boolean psrExiste ;

    public ProjectStatusReport getProjectStatusReport() {
		return this.projectStatusReport;
	}

	public void setProjectStatusReport(ProjectStatusReport psr) {
		this.projectStatusReport = psr;
	}

    public PSRStatus getPsrStatus() {
		return this.psrStatus;
	}

	public void setPsrStatus(PSRStatus psrStatus) {
		this.psrStatus = psrStatus;
	}

	public String toString() {
		return "ReporteProyectoRecurso [projectStatusReport=" + this.projectStatusReport + ", psrStatus=" + this.psrStatus + "]";
	}

	public boolean getPsrExiste() {
		return psrExiste;
	}

	public void setPsrExiste(boolean psrExiste) {
		this.psrExiste = psrExiste;
	}

}

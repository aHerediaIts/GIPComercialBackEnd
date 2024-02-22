package com.backendgip.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "recursos_asignados")
public class Generarmatriztiemposasignacionrecursos implements Serializable{

    private static final long serialVersionUID = 1L;
	@Id

    @Column(name = "pk_recursoAsignado")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "numero_sprint")
    private Integer Sprint; 

    @Column( name = "perfil")
    private String Perfil;

    @Column (name = "especialidad")
    private String Especialidad;

    @Column (name = "id_especialidad")
    private Integer idEspecialidad;

    @Column ( name = "horas_laborales")
    private Integer horasLaborales;

    @Column( name = "tarifa_hora")
    private Integer tarifaHora;

    @Column( name = "tarifa_mensual")
    private Integer tarifaMensual;

    @Column( name = "asignacion_Porcentajes")
    private Integer porcentajesAsignados;

    @ManyToOne
    @JoinColumn(name = "fk_generacionMatrizTiempo")
    private GeneracionMatrizTiempo generacionMatrizTiempo;

    @ManyToOne
    @JoinColumn(name = "fk_version")
    private VersionMatriz version;

    public Generarmatriztiemposasignacionrecursos(){

    }

    public Generarmatriztiemposasignacionrecursos(Integer id, Integer sprint, String perfil, String especialidad,
            Integer horasLaborales, Integer tarifaHora, Integer tarifaMensual, Integer porcentajesAsignados,
            GeneracionMatrizTiempo generacionMatrizTiempo, VersionMatriz version) {
        this.id = id;
        Sprint = sprint;
        Perfil = perfil;
        Especialidad = especialidad;
        this.idEspecialidad = idEspecialidad;
        this.horasLaborales = horasLaborales;
        this.tarifaHora = tarifaHora;
        this.tarifaMensual = tarifaMensual;
        this.porcentajesAsignados = porcentajesAsignados;
        this.generacionMatrizTiempo = generacionMatrizTiempo;
        this.version = version;
    }

    public Generarmatriztiemposasignacionrecursos(Integer id) {
        this.id = id;
    }

    public Generarmatriztiemposasignacionrecursos(Integer id, Integer sprint, String perfil, String especialidad,
            Integer idEspecialidad, Integer horasLaborales, Integer tarifaHora, Integer tarifaMensual,
            Integer porcentajesAsignados, GeneracionMatrizTiempo generacionMatrizTiempo, VersionMatriz version) {
        this.id = id;
        Sprint = sprint;
        Perfil = perfil;
        Especialidad = especialidad;
        this.idEspecialidad = idEspecialidad;
        this.horasLaborales = horasLaborales;
        this.tarifaHora = tarifaHora;
        this.tarifaMensual = tarifaMensual;
        this.porcentajesAsignados = porcentajesAsignados;
        this.generacionMatrizTiempo = generacionMatrizTiempo;
        this.version = version;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSprint() {
        return Sprint;
    }

    public void setSprint(Integer sprint) {
        Sprint = sprint;
    }

    public String getPerfil() {
        return Perfil;
    }

    public void setPerfil(String perfil) {
        Perfil = perfil;
    }

    public String getEspecialidad() {
        return Especialidad;
    }

    public void setEspecialidad(String especialidad) {
        Especialidad = especialidad;
    }

    public Integer getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(Integer idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public Integer getHorasLaborales() {
        return horasLaborales;
    }

    public void setHorasLaborales(Integer horasLaborales) {
        this.horasLaborales = horasLaborales;
    }

    public Integer getTarifaHora() {
        return tarifaHora;
    }

    public void setTarifaHora(Integer tarifaHora) {
        this.tarifaHora = tarifaHora;
    }

    public Integer getTarifaMensual() {
        return tarifaMensual;
    }

    public void setTarifaMensual(Integer tarifaMensual) {
        this.tarifaMensual = tarifaMensual;
    }

    public Integer getPorcentajesAsignados() {
        return porcentajesAsignados;
    }

    public void setPorcentajesAsignados(Integer porcentajesAsignados) {
        this.porcentajesAsignados = porcentajesAsignados;
    }

    public GeneracionMatrizTiempo getGeneracionMatrizTiempo() {
        return generacionMatrizTiempo;
    }

    public void setGeneracionMatrizTiempo(GeneracionMatrizTiempo generacionMatrizTiempo) {
        this.generacionMatrizTiempo = generacionMatrizTiempo;
    }

    public VersionMatriz getVersion() {
        return version;
    }

    public void setVersion(VersionMatriz version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Generarmatriztiemposasignacionrecursos [id=" + id + ", Sprint=" + Sprint + ", Perfil=" + Perfil
                + ", Especialidad=" + Especialidad + ", idEspecialidad=" + idEspecialidad + ", horasLaborales="
                + horasLaborales + ", tarifaHora=" + tarifaHora + ", tarifaMensual=" + tarifaMensual
                + ", porcentajesAsignados=" + porcentajesAsignados + ", generacionMatrizTiempo="
                + generacionMatrizTiempo + ", version=" + version + "]";
    }

}

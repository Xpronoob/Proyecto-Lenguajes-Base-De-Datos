package com.lenguajes.technomind.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Unidades {
     

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPlaca;

    public Long getIdPlaca() {
        return idPlaca;
    }

    public void setIdPlaca(Long idPlaca) {
        this.idPlaca = idPlaca;
    }
    private int idEstadoUnidad;  
    private int idEmpresa;  
    private String unidadAnio;  
    private String capacidadCarga;  
    private String chasis;  
    private String descripcion; 
    
    public int getIdEstadoUnidad() {
        return idEstadoUnidad;
    }

    public void setIdEstadoUnidad(int idEstadoUnidad) {
        this.idEstadoUnidad = idEstadoUnidad;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getUnidadAnio() {
        return unidadAnio;
    }

    public void setUnidadAnio(String unidadAnio) {
        this.unidadAnio = unidadAnio;
    }

    public String getCapacidadCarga() {
        return capacidadCarga;
    }

    public void setCapacidadCarga(String capacidadCarga) {
        this.capacidadCarga = capacidadCarga;
    }

    public String getChasis() {
        return chasis;
    }

    public void setChasis(String chasis) {
        this.chasis = chasis;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setUnidades(String Unidades) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

package com.lenguajes.technomind.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.persistence.Table;

@NamedStoredProcedureQuery(
        name = "obtenerDirecciones",
        procedureName = "OBTENER_DIRECCIONES",
        resultClasses = Direccion.class,
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, type = void.class)
        }
)
@Table(name = "DIRECCION")
@Entity
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDireccion;
    private String referencias;

    public Long getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(Long idDireccion) {
        this.idDireccion = idDireccion;
    }

    public String getReferencias() {
        return referencias;
    }

    public void setReferencias(String referencias) {
        this.referencias = referencias;
    }

}

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
        name = "obtenerEstadosUnidad",
        procedureName = "OBTENER_ESTADOS_UNIDAD",
        resultClasses = EstadoUnidad.class,
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, type = void.class)
        }
)

@NamedStoredProcedureQuery(
        name = "actualizarEstadoUnidad",
        procedureName = "ACTUALIZAR_ESTADO_UNIDAD",
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_estado_unidad", type = Long.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_estado_unidad", type = String.class)
        }
)

@NamedStoredProcedureQuery(
        name = "eliminarEstadoUnidad",
        procedureName = "ELIMINAR_ESTADO_UNIDAD",
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_estado_unidad", type = Long.class)
        }
)

@Table(name = "Estado_Unidad")
@Entity
public class EstadoUnidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEstadoUnidad;
    private String estadoUnidad;

    public Long getIdEstadoUnidad() {
        return idEstadoUnidad;
    }

    public void setIdEstadoUnidad(Long idEstadoUnidad) {
        this.idEstadoUnidad = idEstadoUnidad;
    }

    public String getEstadoUnidad() {
        return estadoUnidad;
    }

    public void setEstadoUnidad(String estadoUnidad) {
        this.estadoUnidad = estadoUnidad;
    }

}

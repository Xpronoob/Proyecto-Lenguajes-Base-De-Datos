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
        name = "obtenerTiposEmpresa",
        procedureName = "OBTENER_TIPOS_EMPRESA",
        resultClasses = TipoEmpresa.class,
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, type = void.class)
        }
)

@NamedStoredProcedureQuery(
        name = "actualizarTipoEmpresa",
        procedureName = "ACTUALIZAR_TIPO_EMPRESA",
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_tipo_empresa", type = Long.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_tipo_empresa", type = String.class)
        }
)

@NamedStoredProcedureQuery(
        name = "eliminarTipoEmpresa",
        procedureName = "ELIMINAR_TIPO_EMPRESA",
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_tipo_empresa", type = Long.class)
        }
)

@Table(name = "Tipo_Empresa")
@Entity
public class TipoEmpresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipoEmpresa;
    private String tipoEmpresa;

    public Long getIdTipoEmpresa() {
        return idTipoEmpresa;
    }

    public void setIdTipoEmpresa(Long idTipoEmpresa) {
        this.idTipoEmpresa = idTipoEmpresa;
    }

    public String getTipoEmpresa() {
        return tipoEmpresa;
    }

    public void setTipoEmpresa(String tipoEmpresa) {
        this.tipoEmpresa = tipoEmpresa;
    }

}

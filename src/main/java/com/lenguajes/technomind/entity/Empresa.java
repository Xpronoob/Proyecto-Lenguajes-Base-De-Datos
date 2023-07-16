package com.lenguajes.technomind.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@NamedStoredProcedureQuery(
        name = "agregarEmpresa",
        procedureName = "agregar_empresa",
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_nombre_empresa", type = String.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_identificacion", type = String.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_fecha_ingreso", type = LocalDate.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_fecha_registro", type = LocalDate.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_direccion", type = String.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_observaciones", type = String.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_tipo_empresa", type = Long.class)
        }
)
@NamedStoredProcedureQuery(
        name = "obtenerEmpresas",
        procedureName = "obtener_empresas",
        resultClasses = Empresa.class,
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, type = void.class)
        }
)
@NamedStoredProcedureQuery(
        name = "actualizarEmpresa",
        procedureName = "actualizar_empresa",
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_empresa", type = Long.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_nombre_empresa", type = String.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_identificacion", type = String.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_fecha_ingreso", type = LocalDate.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_fecha_registro", type = LocalDate.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_direccion", type = String.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_observaciones", type = String.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_tipo_empresa", type = Long.class)
        }
)
@NamedStoredProcedureQuery(
        name = "eliminarEmpresa",
        procedureName = "eliminar_empresa",
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_empresa", type = Long.class)
        }
)
@Entity
@Table(name = "Empresa")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empresa")
    private Long idEmpresa;

    @Column(name = "nombre_empresa", nullable = false)
    private String nombreEmpresa;

    @Column(name = "identificacion", nullable = false)
    private String identificacion;

    @Column(name = "fecha_ingreso", nullable = false)
    private LocalDate fechaIngreso;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDate fechaRegistro;

    @Column(name = "direccion", nullable = false)
    private String direccion;

    @Column(name = "observaciones")
    private String observaciones;

    @ManyToOne
    @JoinColumn(name = "id_tipo_empresa")
    private TipoEmpresa tipoEmpresa;

    public Long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public TipoEmpresa getTipoEmpresa() {
        return tipoEmpresa;
    }

    public void setTipoEmpresa(TipoEmpresa tipoEmpresa) {
        this.tipoEmpresa = tipoEmpresa;
    }
}

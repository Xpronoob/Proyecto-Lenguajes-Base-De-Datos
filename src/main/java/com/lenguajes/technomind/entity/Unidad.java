package com.lenguajes.technomind.entity;

import jakarta.persistence.*;

@Entity
@NamedStoredProcedureQuery(
        name = "agregarUnidad",
        procedureName = "agregar_unidad",
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_estado_unidad", type = Long.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_empresa", type = Long.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_unidad_year", type = String.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_capacidad_carga", type = String.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_chasis", type = String.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_descripcion", type = String.class)
        }
)
@NamedStoredProcedureQuery(
        name = "obtenerUnidades",
        procedureName = "obtener_unidades",
        resultClasses = Unidad.class,
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, type = void.class)
        }
)
@NamedStoredProcedureQuery(
        name = "actualizarUnidad",
        procedureName = "actualizar_unidad",
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_placa", type = Long.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_estado_unidad", type = Long.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_empresa", type = Long.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_unidad_year", type = String.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_capacidad_carga", type = String.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_chasis", type = String.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_descripcion", type = String.class)
        }
)
@NamedStoredProcedureQuery(
        name = "eliminarUnidad",
        procedureName = "eliminar_unidad",
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_placa", type = Long.class)
        }
)
@Table(name = "Unidades")
public class Unidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_placa")
    private Long idPlaca;

    @ManyToOne
    @JoinColumn(name = "id_estado_unidad")
    private EstadoUnidad estadoUnidad;

    @ManyToOne
    @JoinColumn(name = "id_empresa")
    private Empresa empresa;

    @Column(name = "unidad_year", nullable = false)
    private String unidadYear;

    @Column(name = "capacidad_carga", nullable = false)
    private String capacidadCarga;

    @Column(name = "chasis", nullable = false)
    private String chasis;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    // Getters and Setters
    public Long getIdPlaca() {
        return idPlaca;
    }

    public void setIdPlaca(Long idPlaca) {
        this.idPlaca = idPlaca;
    }

    public EstadoUnidad getEstadoUnidad() {
        return estadoUnidad;
    }

    public void setEstadoUnidad(EstadoUnidad estadoUnidad) {
        this.estadoUnidad = estadoUnidad;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getUnidadYear() {
        return unidadYear;
    }

    public void setUnidadYear(String unidadYear) {
        this.unidadYear = unidadYear;
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
}

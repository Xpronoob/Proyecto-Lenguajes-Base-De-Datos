package com.lenguajes.technomind.entity;

import jakarta.persistence.*;

@Entity
@NamedStoredProcedureQuery(
        name = "agregarEmpleado",
        procedureName = "agregar_empleado",
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_nombre_empleado", type = String.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_apellido_empleado", type = String.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_identificacion_empleado", type = Long.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_direccion", type = Long.class)
        }
)
@NamedStoredProcedureQuery(
        name = "obtenerEmpleados",
        procedureName = "obtener_empleados",
        resultClasses = Empleado.class,
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, type = void.class)
        }
)
@NamedStoredProcedureQuery(
        name = "actualizarEmpleado",
        procedureName = "actualizar_empleado",
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_empleado", type = Long.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_nombre_empleado", type = String.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_apellido_empleado", type = String.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_identificacion_empleado", type = Long.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_direccion", type = Long.class)
        }
)
@NamedStoredProcedureQuery(
        name = "eliminarEmpleado",
        procedureName = "eliminar_empleado",
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_empleado", type = Long.class)
        }
)
@Table(name = "Empleado")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado")
    private Long idEmpleado;

    @Column(name = "nombre_empleado")
    private String nombreEmpleado;

    @Column(name = "apellido_empleado")
    private String apellidoEmpleado;

    @Column(name = "identificacion_empleado")
    private Long identificacionEmpleado;

    @ManyToOne
    @JoinColumn(name = "id_direccion")
    private Direccion direccion;

    public Long getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getApellidoEmpleado() {
        return apellidoEmpleado;
    }

    public void setApellidoEmpleado(String apellidoEmpleado) {
        this.apellidoEmpleado = apellidoEmpleado;
    }

    public Long getIdentificacionEmpleado() {
        return identificacionEmpleado;
    }

    public void setIdentificacionEmpleado(Long identificacionEmpleado) {
        this.identificacionEmpleado = identificacionEmpleado;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }
}

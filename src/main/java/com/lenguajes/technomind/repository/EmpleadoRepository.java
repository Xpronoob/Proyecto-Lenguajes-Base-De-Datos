package com.lenguajes.technomind.repository;

import com.lenguajes.technomind.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    @Procedure(name = "agregarEmpleado")
    void agregarEmpleado(
            @Param("p_nombre_empleado") String nombreEmpleado,
            @Param("p_apellido_empleado") String apellidoEmpleado,
            @Param("p_identificacion_empleado") Long identificacionEmpleado,
            @Param("p_id_direccion") Long idDireccion
    );

    @Procedure(name = "obtenerEmpleados")
    List<Empleado> obtenerEmpleados();

    @Procedure(name = "actualizarEmpleado")
    void actualizarEmpleado(
            @Param("p_id_empleado") Long idEmpleado,
            @Param("p_nombre_empleado") String nombreEmpleado,
            @Param("p_apellido_empleado") String apellidoEmpleado,
            @Param("p_identificacion_empleado") Long identificacionEmpleado,
            @Param("p_id_direccion") Long idDireccion
    );

    @Procedure(name = "eliminarEmpleado")
    void eliminarEmpleado(@Param("p_id_empleado") Long idEmpleado);

}

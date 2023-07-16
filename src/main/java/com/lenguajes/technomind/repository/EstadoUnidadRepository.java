package com.lenguajes.technomind.repository;

import com.lenguajes.technomind.entity.EstadoUnidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstadoUnidadRepository extends JpaRepository<EstadoUnidad, Long> {

    @Procedure("agregar_estado_unidad")
    void agregarEstadoUnidad(String estadoUnidad);

    @Procedure(name = "obtenerEstadosUnidad")
    List<EstadoUnidad> obtenerEstadosUnidad();

    @Procedure(name = "actualizarEstadoUnidad")
    void actualizarEstadoUnidad(
            @Param("p_id_estado_unidad") Long idEstadoUnidad,
            @Param("p_estado_unidad") String estadoUnidad
    );

    @Procedure(name = "eliminarEstadoUnidad")
    void eliminarEstadoUnidad(@Param("p_id_estado_unidad") Long idEstadoUnidad);

}

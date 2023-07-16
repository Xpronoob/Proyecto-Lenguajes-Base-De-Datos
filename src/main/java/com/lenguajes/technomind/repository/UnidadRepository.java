package com.lenguajes.technomind.repository;

import com.lenguajes.technomind.entity.Unidad;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadRepository extends JpaRepository<Unidad, Long> {

    @Procedure("agregar_Unidad")
    void agregarUnidad(
            @Param("p_id_estado_unidad") Long idEstadoUnidad,
            @Param("p_id_empresa") Long idEmpresa,
            @Param("p_unidad_year") String unidadYear,
            @Param("p_capacidad_carga") String capacidadCarga,
            @Param("p_chasis") String chasis,
            @Param("p_descripcion") String descripcion
    );

    @Procedure(name = "obtenerUnidades")
    List<Unidad> obtenerUnidades();

    @Procedure(name = "actualizarUnidad")
    void actualizarUnidad(
            @Param("p_id_placa") Long idPlaca,
            @Param("p_id_estado_unidad") Long idEstadoUnidad,
            @Param("p_id_empresa") Long idEmpresa,
            @Param("p_unidad_year") String unidadYear,
            @Param("p_capacidad_carga") String capacidadCarga,
            @Param("p_chasis") String chasis,
            @Param("p_descripcion") String descripcion
    );

    @Procedure(name = "eliminarUnidad")
    void eliminarUnidad(@Param("p_id_placa") Long idPlaca);

    //Unidad findByIdPlaca(Long idPlaca);
    Optional<Unidad> findByIdPlaca(Long idPlaca);
}

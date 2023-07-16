package com.lenguajes.technomind.repository;

import com.lenguajes.technomind.entity.Empresa;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    @Procedure("agregar_empresa")
    void agregarEmpresa(
            @Param("p_nombre_empresa") String nombreEmpresa,
            @Param("p_identificacion") String identificacion,
            @Param("p_fecha_ingreso") LocalDate fechaIngreso,
            @Param("p_fecha_registro") LocalDate fechaRegistro,
            @Param("p_direccion") String direccion,
            @Param("p_observaciones") String observaciones,
            @Param("p_id_tipo_empresa") Long idTipoEmpresa
    );

    @Procedure(name = "obtenerEmpresas")
    List<Empresa> obtenerEmpresas();

    @Procedure(name = "actualizarEmpresa")
    void actualizarEmpresa(
            @Param("p_id_empresa") Long idEmpresa,
            @Param("p_nombre_empresa") String nombreEmpresa,
            @Param("p_identificacion") String identificacion,
            @Param("p_fecha_ingreso") LocalDate fechaIngreso,
            @Param("p_fecha_registro") LocalDate fechaRegistro,
            @Param("p_direccion") String direccion,
            @Param("p_observaciones") String observaciones,
            @Param("p_id_tipo_empresa") Long idTipoEmpresa
    );

    @Procedure(name = "eliminarEmpresa")
    void eliminarEmpresa(@Param("p_id_empresa") Long idEmpresa);

    Optional<Empresa> findById(Long id);
}

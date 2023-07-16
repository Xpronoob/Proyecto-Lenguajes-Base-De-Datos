package com.lenguajes.technomind.repository;

import com.lenguajes.technomind.entity.TipoEmpresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoEmpresaRepository extends JpaRepository<TipoEmpresa, Long> {

    @Procedure("agregar_tipo_empresa")
    void agregarTipoEmpresa(String tipoEmpresa);

    @Procedure(name = "obtenerTiposEmpresa")
    List<TipoEmpresa> obtenerTiposEmpresa();

    @Procedure(name = "actualizarTipoEmpresa")
    void actualizarTipoEmpresa(
            @Param("p_id_tipo_empresa") Long idTipoEmpresa,
            @Param("p_tipo_empresa") String tipoEmpresa
    );

    @Procedure(name = "eliminarTipoEmpresa")
    void eliminarTipoEmpresa(@Param("p_id_tipo_empresa") Long idTipoEmpresa);

}

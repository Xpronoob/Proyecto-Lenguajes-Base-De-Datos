package com.lenguajes.technomind.repository;

import com.lenguajes.technomind.entity.Direccion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface DireccionRepository extends JpaRepository<Direccion, Long> {

    @Procedure("agregar_direccion")
    void agregarDireccion(String referencias);

    @Procedure(name = "obtenerDirecciones")
    List<Direccion> obtenerDirecciones();

}

package com.lenguajes.technomind.repository;
import com.lenguajes.technomind.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmpleadoRepository extends JpaRepository<Empleado, Long> {

}

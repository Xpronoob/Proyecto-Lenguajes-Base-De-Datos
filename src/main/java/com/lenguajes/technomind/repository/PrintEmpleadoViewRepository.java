package com.lenguajes.technomind.repository;

import com.lenguajes.technomind.entity.PrintEmpleadoView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrintEmpleadoViewRepository extends JpaRepository<PrintEmpleadoView, Long>{
    
}

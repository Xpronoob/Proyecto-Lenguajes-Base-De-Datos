package com.lenguajes.technomind.Service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.lenguajes.technomind.repository.IEmpleadoRepository;
import com.lenguajes.technomind.entity.Empleado;
import org.springframework.beans.factory.annotation.Autowired;

@Service
@Transactional
public class EmpleadoService {

    @Autowired
    private final IEmpleadoRepository empleadoRepository;

    public EmpleadoService(IEmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    public void agregarEmpleado(Empleado empleado) {
        empleadoRepository.save(empleado);
    }
}

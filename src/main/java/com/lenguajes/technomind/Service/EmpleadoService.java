package com.lenguajes.technomind.service;

import com.lenguajes.technomind.entity.Empleado;
import com.lenguajes.technomind.repository.EmpleadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    public void agregarEmpleado(String nombreEmpleado, String apellidoEmpleado, Long identificacionEmpleado, Long idDireccion) {
        empleadoRepository.agregarEmpleado(nombreEmpleado, apellidoEmpleado, identificacionEmpleado, idDireccion);
    }

    public List<Empleado> obtenerEmpleados() {
        return empleadoRepository.obtenerEmpleados();
    }

    public void actualizarEmpleado(Long idEmpleado, String nombreEmpleado, String apellidoEmpleado, Long identificacionEmpleado, Long idDireccion) {
        empleadoRepository.actualizarEmpleado(idEmpleado, nombreEmpleado, apellidoEmpleado, identificacionEmpleado, idDireccion);
    }

    public Empleado obtenerEmpleadoPorId(Long id) {
        return empleadoRepository.findById(id).orElse(null);
    }

    public void eliminarEmpleado(Long idEmpleado) {
        empleadoRepository.eliminarEmpleado(idEmpleado);
    }
}

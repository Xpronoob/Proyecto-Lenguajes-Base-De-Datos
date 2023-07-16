package com.lenguajes.technomind.service;

import com.lenguajes.technomind.entity.EstadoUnidad;
import com.lenguajes.technomind.repository.EstadoUnidadRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
public class EstadoUnidadService {

    private final EstadoUnidadRepository estadoUnidadRepository;

    @Autowired
    public EstadoUnidadService(EstadoUnidadRepository estadoUnidadRepository) {
        this.estadoUnidadRepository = estadoUnidadRepository;
    }

    public void agregarEstadoUnidad(String estadoUnidad) {
        estadoUnidadRepository.agregarEstadoUnidad(estadoUnidad);
    }

    public List<EstadoUnidad> obtenerEstadosUnidad() {
        return estadoUnidadRepository.obtenerEstadosUnidad();
    }

    public void actualizarEstadoUnidad(Long idEstadoUnidad, String estadoUnidad) {
        estadoUnidadRepository.actualizarEstadoUnidad(idEstadoUnidad, estadoUnidad);
    }

    public EstadoUnidad obtenerEstadoUnidadPorId(Long id) {
        return estadoUnidadRepository.findById(id).orElse(null);
    }

    public void eliminarEstadoUnidad(Long idEstadoUnidad) {
        estadoUnidadRepository.eliminarEstadoUnidad(idEstadoUnidad);
    }
}

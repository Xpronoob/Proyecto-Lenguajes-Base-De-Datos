package com.lenguajes.technomind.service;

import com.lenguajes.technomind.entity.Unidad;
import com.lenguajes.technomind.repository.UnidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UnidadService {

    private final UnidadRepository unidadRepository;

    @Autowired
    public UnidadService(UnidadRepository unidadRepository) {
        this.unidadRepository = unidadRepository;
    }

    public void agregarUnidad(Unidad unidad) {
        unidadRepository.agregarUnidad(
                unidad.getEstadoUnidad().getIdEstadoUnidad(),
                unidad.getEmpresa().getIdEmpresa(),
                unidad.getUnidadYear(),
                unidad.getCapacidadCarga(),
                unidad.getChasis(),
                unidad.getDescripcion()
        );
    }

    public List<Unidad> obtenerUnidades() {
        return unidadRepository.obtenerUnidades();
    }

    public void actualizarUnidad(Unidad unidad) {
        unidadRepository.actualizarUnidad(
                unidad.getIdPlaca(),
                unidad.getEstadoUnidad().getIdEstadoUnidad(),
                unidad.getEmpresa().getIdEmpresa(),
                unidad.getUnidadYear(),
                unidad.getCapacidadCarga(),
                unidad.getChasis(),
                unidad.getDescripcion()
        );
    }

    public void eliminarUnidad(Long idPlaca) {
        unidadRepository.eliminarUnidad(idPlaca);
    }

    public Optional<Unidad> obtenerUnidadPorId(Long idPlaca) {
        return unidadRepository.findByIdPlaca(idPlaca);
    }
}

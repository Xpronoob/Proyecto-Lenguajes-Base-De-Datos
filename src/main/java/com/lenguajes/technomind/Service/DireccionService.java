package com.lenguajes.technomind.Service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.lenguajes.technomind.repository.IDireccionRepository;
import com.lenguajes.technomind.entity.Direccion;
import org.springframework.beans.factory.annotation.Autowired;

@Service
@Transactional
public class DireccionService {

    @Autowired
    private final IDireccionRepository direccionRepository;

    public DireccionService(IDireccionRepository direccionRepository) {
        this.direccionRepository = direccionRepository;
    }

    public void agregarDireccion(Direccion direccion) {
        direccionRepository.save(direccion);
    }
}

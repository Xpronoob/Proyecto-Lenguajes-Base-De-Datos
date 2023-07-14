package com.lenguajes.technomind.Service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.lenguajes.technomind.entity.Unidades;
import com.lenguajes.technomind.repository.IUnidadesRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
@Transactional
public class UnidadesService {

    @Autowired
    private final IUnidadesRepository unidadesRepository;

    public UnidadesService(IUnidadesRepository unidadesRepository) {
        this.unidadesRepository = unidadesRepository;
    }

    public void agregarUnidades(Unidades unidades) {
        unidadesRepository.save(unidades);
    }
}

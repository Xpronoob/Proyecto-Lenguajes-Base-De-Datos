package com.lenguajes.technomind.Service;

import com.lenguajes.technomind.entity.Direccion;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
//import com.lenguajes.technomind.entity.Direccion;
import org.springframework.beans.factory.annotation.Autowired;
import com.lenguajes.technomind.repository.DireccionRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.StoredProcedureQuery;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DireccionService {

    @Autowired
    private final DireccionRepository direccionRepository;
    private final EntityManager entityManager;

    public DireccionService(DireccionRepository direccionRepository, EntityManager entityManager) {
        this.direccionRepository = direccionRepository;
        this.entityManager = entityManager;
    }

    public void agregarDireccion(String referencias) {
        //direccionRepository.save(direccion);
        direccionRepository.agregarDireccion(referencias);
    }

    public List<Direccion> obtenerDirecciones() {
        return direccionRepository.obtenerDirecciones();
    }

    public void actualizarDireccion(Long idDireccion, String referencias) {
        direccionRepository.actualizarDireccion(idDireccion, referencias);
    }

    public Direccion obtenerDireccionPorId(Long id) {
        return direccionRepository.findById(id).orElse(null);
    }

    public void eliminarDireccion(Long idDireccion) {
        direccionRepository.eliminarDireccion(idDireccion);
    }

}

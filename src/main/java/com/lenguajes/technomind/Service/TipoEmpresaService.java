package com.lenguajes.technomind.service;

import com.lenguajes.technomind.entity.TipoEmpresa;
import com.lenguajes.technomind.repository.TipoEmpresaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
public class TipoEmpresaService {

    private final TipoEmpresaRepository tipoEmpresaRepository;

    @Autowired
    public TipoEmpresaService(TipoEmpresaRepository tipoEmpresaRepository) {
        this.tipoEmpresaRepository = tipoEmpresaRepository;
    }

    public void agregarTipoEmpresa(String tipoEmpresa) {
        tipoEmpresaRepository.agregarTipoEmpresa(tipoEmpresa);
    }

    public List<TipoEmpresa> obtenerTiposEmpresa() {
        return tipoEmpresaRepository.obtenerTiposEmpresa();
    }

    public void actualizarTipoEmpresa(Long idTipoEmpresa, String tipoEmpresa) {
        tipoEmpresaRepository.actualizarTipoEmpresa(idTipoEmpresa, tipoEmpresa);
    }

    public TipoEmpresa obtenerTipoEmpresaPorId(Long id) {
        return tipoEmpresaRepository.findById(id).orElse(null);
    }

    public void eliminarTipoEmpresa(Long idTipoEmpresa) {
        tipoEmpresaRepository.eliminarTipoEmpresa(idTipoEmpresa);
    }
}

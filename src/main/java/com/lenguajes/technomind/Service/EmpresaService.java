package com.lenguajes.technomind.service;

import com.lenguajes.technomind.entity.Empresa;
import com.lenguajes.technomind.repository.EmpresaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    @Autowired
    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public void agregarEmpresa(Empresa empresa) {
        empresaRepository.agregarEmpresa(
                empresa.getNombreEmpresa(),
                empresa.getIdentificacion(),
                empresa.getFechaIngreso(),
                empresa.getFechaRegistro(),
                empresa.getDireccion(),
                empresa.getObservaciones(),
                empresa.getTipoEmpresa().getIdTipoEmpresa()
        );
    }

    public List<Empresa> obtenerEmpresas() {
        return empresaRepository.obtenerEmpresas();
    }

    public void actualizarEmpresa(Empresa empresa) {
        empresaRepository.actualizarEmpresa(
                empresa.getIdEmpresa(),
                empresa.getNombreEmpresa(),
                empresa.getIdentificacion(),
                empresa.getFechaIngreso(),
                empresa.getFechaRegistro(),
                empresa.getDireccion(),
                empresa.getObservaciones(),
                empresa.getTipoEmpresa().getIdTipoEmpresa()
        );
    }

    public void eliminarEmpresa(Long idEmpresa) {
        empresaRepository.eliminarEmpresa(idEmpresa);
    }

    public Empresa obtenerEmpresaPorId(Long id) {
        Optional<Empresa> empresaOptional = empresaRepository.findById(id);
        return empresaOptional.orElse(null);
    }
}

package com.lenguajes.technomind.controller;

import com.lenguajes.technomind.entity.Empresa;
import com.lenguajes.technomind.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/empresas")
public class EmpresaController {

    private final EmpresaService empresaService;

    @Autowired
    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @PostMapping("/agregar")
    public String agregarEmpresa(@ModelAttribute("empresa") Empresa empresa, Model model) {
        empresaService.agregarEmpresa(empresa);
        model.addAttribute("mensaje", "Empresa agregada correctamente");
        return "redirect:/empresas/";
    }

    @GetMapping("/")
    public String obtenerEmpresas(Model model) {
        List<Empresa> empresas = empresaService.obtenerEmpresas();
        model.addAttribute("empresas", empresas);
        return "empresa.html";
    }

    @GetMapping("/{id}/editar")
    public String editarEmpresa(@PathVariable("id") Long idEmpresa, Model model) {
        Empresa empresa = empresaService.obtenerEmpresaPorId(idEmpresa);
        model.addAttribute("empresa", empresa);
        return "empresas/empresaUpdate.html";
    }

    @PostMapping("/{id}/editar")
    public String guardarEmpresaEditada(@PathVariable("id") Long idEmpresa, @ModelAttribute("empresa") Empresa empresa) {
        empresa.setIdEmpresa(idEmpresa);
        empresaService.actualizarEmpresa(empresa);
        return "redirect:/empresas/";
    }

    @PostMapping("/eliminar")
    public String eliminarEmpresa(@RequestParam("idEliminar") Long idEmpresa) {
        empresaService.eliminarEmpresa(idEmpresa);
        return "redirect:/empresas/";
    }
}

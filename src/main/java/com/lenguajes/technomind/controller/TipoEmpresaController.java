package com.lenguajes.technomind.controller;

import com.lenguajes.technomind.entity.TipoEmpresa;
import com.lenguajes.technomind.service.TipoEmpresaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tipo-empresas")
public class TipoEmpresaController {

    private final TipoEmpresaService tipoEmpresaService;

    public TipoEmpresaController(TipoEmpresaService tipoEmpresaService) {
        this.tipoEmpresaService = tipoEmpresaService;
    }

    @PostMapping("/agregar")
    public String agregarTipoEmpresa(@RequestParam("tipoEmpresa") String tipoEmpresa, Model model) {
        tipoEmpresaService.agregarTipoEmpresa(tipoEmpresa);
        model.addAttribute("mensaje", "Tipo de empresa agregado correctamente");
        return "redirect:/tipo-empresas/";
    }

    @GetMapping("/")
    public String obtenerTiposEmpresa(Model model) {
        List<TipoEmpresa> tiposEmpresa = tipoEmpresaService.obtenerTiposEmpresa();
        model.addAttribute("tiposEmpresa", tiposEmpresa);
        return "tipo-empresa.html";
    }

    @GetMapping("/{id}/editar")
    public String editarTipoEmpresa(@PathVariable("id") Long id, Model model) {
        TipoEmpresa tipoEmpresa = tipoEmpresaService.obtenerTipoEmpresaPorId(id);
        model.addAttribute("tipoEmpresa", tipoEmpresa);
        return "tipo-empresas/tipoEmpresaUpdate.html";
    }

    @PostMapping("/{id}/editar")
    public String guardarTipoEmpresaEditado(@PathVariable("id") Long id,
            @RequestParam("tipoEmpresa") String tipoEmpresa) {
        tipoEmpresaService.actualizarTipoEmpresa(id, tipoEmpresa);
        return "redirect:/tipo-empresas/";
    }

    @PostMapping("/eliminar")
    public String eliminarTipoEmpresa(@RequestParam("idEliminar") Long idEliminar) {
        tipoEmpresaService.eliminarTipoEmpresa(idEliminar);
        return "redirect:/tipo-empresas/";
    }
}

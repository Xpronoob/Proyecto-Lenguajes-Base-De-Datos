package com.lenguajes.technomind.controller;

import com.lenguajes.technomind.entity.Unidad;
import com.lenguajes.technomind.service.UnidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/unidades")
public class UnidadController {

    private final UnidadService unidadService;

    @Autowired
    public UnidadController(UnidadService unidadService) {
        this.unidadService = unidadService;
    }

    @PostMapping("/agregar")
    public String agregarUnidad(@ModelAttribute("unidad") Unidad unidad, Model model) {
        unidadService.agregarUnidad(unidad);
        model.addAttribute("mensaje", "Unidad agregada correctamente");
        return "redirect:/unidades/";
    }

    @GetMapping("/")
    public String obtenerUnidades(Model model) {
        List<Unidad> unidades = unidadService.obtenerUnidades();
        model.addAttribute("unidades", unidades);
        return "unidad.html";
    }

    @GetMapping("/{idPlaca}/editar")
    public String editarUnidad(@PathVariable("idPlaca") Long idPlaca, Model model) {
        Optional<Unidad> unidad = unidadService.obtenerUnidadPorId(idPlaca);
        if (unidad.isPresent()) {
            model.addAttribute("unidad", unidad.get());
            return "unidades/unidadUpdate.html";
        } else {
            return "redirect:/unidades/";
        }
    }

    @PostMapping("/{idPlaca}/editar")
    public String guardarUnidadEditada(@PathVariable("idPlaca") Long idPlaca, @ModelAttribute("unidad") Unidad unidad) {
        unidad.setIdPlaca(idPlaca);
        unidadService.actualizarUnidad(unidad);
        return "redirect:/unidades/";
    }

    @PostMapping("/eliminar")
    public String eliminarUnidad(@RequestParam("idEliminar") Long idPlaca) {
        unidadService.eliminarUnidad(idPlaca);
        return "redirect:/unidades/";
    }
}

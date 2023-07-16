package com.lenguajes.technomind.controller;

import com.lenguajes.technomind.entity.EstadoUnidad;
import com.lenguajes.technomind.service.EstadoUnidadService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/estado-unidades")
public class EstadoUnidadController {

    private final EstadoUnidadService estadoUnidadService;

    public EstadoUnidadController(EstadoUnidadService estadoUnidadService) {
        this.estadoUnidadService = estadoUnidadService;
    }

    @PostMapping("/agregar")
    public String agregarEstadoUnidad(@RequestParam("estadoUnidad") String estadoUnidad, Model model) {
        estadoUnidadService.agregarEstadoUnidad(estadoUnidad);
        model.addAttribute("mensaje", "Estado de Unidad agregado correctamente");
        return "redirect:/estado-unidades/";
    }

    @GetMapping("/")
    public String obtenerEstadosUnidad(Model model) {
        List<EstadoUnidad> estadosUnidad = estadoUnidadService.obtenerEstadosUnidad();
        model.addAttribute("estadosUnidad", estadosUnidad);
        return "estado-unidad.html";
    }

    @GetMapping("/{id}/editar")
    public String editarEstadoUnidad(@PathVariable("id") Long id, Model model) {
        EstadoUnidad estadoUnidad = estadoUnidadService.obtenerEstadoUnidadPorId(id);
        model.addAttribute("estadoUnidad", estadoUnidad);
        return "estado-unidades/estadoUnidadUpdate.html";
    }

    @PostMapping("/{id}/editar")
    public String guardarEstadoUnidadEditado(@PathVariable("id") Long id,
            @RequestParam("estadoUnidad") String estadoUnidad) {
        estadoUnidadService.actualizarEstadoUnidad(id, estadoUnidad);
        return "redirect:/estado-unidades/";
    }

    @PostMapping("/eliminar")
    public String eliminarEstadoUnidad(@RequestParam("idEliminar") Long idEliminar) {
        estadoUnidadService.eliminarEstadoUnidad(idEliminar);
        return "redirect:/estado-unidades/";
    }
}

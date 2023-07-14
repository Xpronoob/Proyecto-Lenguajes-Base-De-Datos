package com.lenguajes.technomind.controller;

import com.lenguajes.technomind.Service.UnidadesService;
import com.lenguajes.technomind.entity.Unidades;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UnidadesController {

    private final UnidadesService unidadesService;

    public UnidadesController(UnidadesService unidadesService) {
        this.unidadesService = unidadesService;
    }

    @PostMapping("/unidades")
    public String agregarUnidades(@RequestParam("Unidades") String Unidades, Model model) {
        Unidades nuevoUnidades = new Unidades();
        nuevoUnidades.setUnidades(Unidades);
        unidadesService.agregarUnidades(nuevoUnidades);
        model.addAttribute("mensaje", "Unidades agregada correctamente");
        return "unidades";
    }
    @GetMapping("/unidades")
    public String mostrarFormulario(Model model) {
        model.addAttribute("unidades", new Unidades());
        return "unidades.html";
    }
}


package com.lenguajes.technomind.controller;

import com.lenguajes.technomind.Service.DireccionService;
import com.lenguajes.technomind.entity.Direccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class DireccionController {

    private final DireccionService direccionService;

    public DireccionController(DireccionService direccionService) {
        this.direccionService = direccionService;
    }

    @PostMapping("/direcciones")
    public String agregarDireccion(@RequestParam("referencias") String referencias, Model model) {
        Direccion nuevaDireccion = new Direccion();
        nuevaDireccion.setReferencias(referencias);

        direccionService.agregarDireccion(nuevaDireccion);

        model.addAttribute("mensaje", "Dirección agregada correctamente");
        return "direccion";
    }

    @GetMapping("/direcciones")
    public String mostrarFormulario(Model model) {
        model.addAttribute("direccion", new Direccion());
        return "direccion.html";
    }

    // Resto del código del controlador
}

package com.lenguajes.technomind.controller;

import com.lenguajes.technomind.Service.DireccionService;
import com.lenguajes.technomind.entity.Direccion;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/direcciones")
public class DireccionController {

    private final DireccionService direccionService;

    public DireccionController(DireccionService direccionService) {
        this.direccionService = direccionService;
    }

    @PostMapping("/agregar")
    public String agregarDireccion(@RequestParam("referencias") String referencias, Model model) {
        String nuevaDireccion = referencias;

        direccionService.agregarDireccion(nuevaDireccion);

        model.addAttribute("mensaje", "Dirección agregada correctamente");
        return "redirect:/direcciones/";
    }

    @GetMapping("/")
    public String obtenerDirecciones(Model model) {
        List<Direccion> direcciones = direccionService.obtenerDirecciones();
        model.addAttribute("direcciones", direcciones);
        return "direccion.html";
    }

    @GetMapping("/{id}/editar")
    public String editarDireccion(@PathVariable("id") String id, Model model) {
        Long direccionId = Long.parseLong(id);
        Direccion direccion = direccionService.obtenerDireccionPorId(direccionId);
        model.addAttribute("direccion", direccion);
        return "direcciones/direccionUpdate.html";
    }

    @PostMapping("/{id}/editar")
    public String guardarDireccionEditada(@PathVariable("id") Long id,
            @RequestParam("referencias") String referencias) {
        direccionService.actualizarDireccion(id, referencias);
        return "redirect:/direcciones/";
    }

    @PostMapping("/eliminar")
    public String eliminarDireccion(@RequestParam("idEliminar") Long idEliminar) {
        direccionService.eliminarDireccion(idEliminar);
        return "redirect:/direcciones/";
    }

    //
    @GetMapping("/ejecutar-cccc")
    public String ejecutarCCCC(Model model) {
        direccionService.ejecutarCCCC();

        List<Direccion> resultados = direccionService.ejecutarCCCC();
        model.addAttribute("resultados", resultados);

        return "resultado-cccc";
    }

}

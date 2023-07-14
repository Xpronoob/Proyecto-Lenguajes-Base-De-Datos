package com.lenguajes.technomind.controller;

import com.lenguajes.technomind.Service.EmpleadoService;
import com.lenguajes.technomind.entity.Empleado;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @PostMapping("/empleado")
    public String agregarEmpleado(@RequestParam("Nombre") String nombreEmpleado, Model model) {
        Empleado nuevoEmpleado = new Empleado();
        nuevoEmpleado.setNombreEmpleado(nombreEmpleado);

        empleadoService.agregarEmpleado(nuevoEmpleado);

        model.addAttribute("mensaje", "Empleado agregada correctamente");
        return "empleado";
    }
    @GetMapping("/empleado")
    public String mostrarFormulario(Model model) {
        model.addAttribute("empleado", new Empleado());
        return "empleado.html";
    }
}


package com.lenguajes.technomind.controller;

import com.lenguajes.technomind.entity.Empleado;
import com.lenguajes.technomind.service.EmpleadoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/empleados")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @PostMapping("/agregar")
    public String agregarEmpleado(@RequestParam("nombre") String nombre,
            @RequestParam("apellido") String apellido,
            @RequestParam("identificacion") Long identificacion,
            @RequestParam("idDireccion") Long idDireccion,
            Model model) {
        empleadoService.agregarEmpleado(nombre, apellido, identificacion, idDireccion);
        model.addAttribute("mensaje", "Empleado agregado correctamente");
        return "redirect:/empleados/";
    }

    @GetMapping("/")
    public String obtenerEmpleados(Model model) {
        List<Empleado> empleados = empleadoService.obtenerEmpleados();
        model.addAttribute("empleados", empleados);
        return "empleado.html";
    }

    @GetMapping("/{id}/editar")
    public String editarEmpleado(@PathVariable("id") Long id, Model model) {
        Empleado empleado = empleadoService.obtenerEmpleadoPorId(id);
        model.addAttribute("empleado", empleado);
        return "empleados/empleadoUpdate.html";
    }

    @PostMapping("/{id}/editar")
    public String guardarEmpleadoEditado(@PathVariable("id") Long id,
            @RequestParam("nombre") String nombre,
            @RequestParam("apellido") String apellido,
            @RequestParam("identificacion") Long identificacion,
            @RequestParam("idDireccion") Long idDireccion) {
        empleadoService.actualizarEmpleado(id, nombre, apellido, identificacion, idDireccion);
        return "redirect:/empleados/";
    }

    @PostMapping("/eliminar")
    public String eliminarEmpleado(@RequestParam("idEliminar") Long idEliminar) {
        empleadoService.eliminarEmpleado(idEliminar);
        return "redirect:/empleados/";
    }
}

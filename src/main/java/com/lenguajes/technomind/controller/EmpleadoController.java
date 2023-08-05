package com.lenguajes.technomind.controller;

import com.lenguajes.technomind.Service.PrintEmpleadoViewService;
import com.lenguajes.technomind.Service.funcionesOracle;
import com.lenguajes.technomind.entity.Empleado;
import com.lenguajes.technomind.entity.PrintEmpleadoView;
import com.lenguajes.technomind.service.EmpleadoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping("/empleados")
public class EmpleadoController {

    private final EmpleadoService empleadoService;
    private final PrintEmpleadoViewService pevS;
    private final funcionesOracle funcionesOracleService;

    @Autowired
    public EmpleadoController(EmpleadoService empleadoService, PrintEmpleadoViewService pevS, funcionesOracle funcionesOracleService) {
        this.pevS = pevS;
        this.empleadoService = empleadoService;
        this.funcionesOracleService = funcionesOracleService;
    }

    //FUNCTION
    @GetMapping("/imprimirFunc")
    public String obtenerEmpleadoss(Model model) {
        Iterable<Empleado> empleados = funcionesOracleService.obtenerEmpleadosSinDireccion();
        model.addAttribute("empleados", empleados);
        return "funcion_empleados"; // Nombre de la vista Thymeleaf para mostrar los empleados
    }

    //VIEW
    @GetMapping("/imprimirView")
    public String imprimirEmpleados(Model model) {
        // Llamamos al servicio para obtener los datos de la vista
        List<PrintEmpleadoView> empleados = pevS.obtenerPevR();

        // Agregamos los datos de la vista al modelo para que puedan ser utilizados en la plantilla Thymeleaf
        model.addAttribute("empleados", empleados);

        // Devolvemos el nombre de la plantilla Thymeleaf que queremos mostrar
        return "imprimir_empleados";
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

package com.micarrera.dashboardbackend.controlador;


import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.micarrera.dashboardbackend.dto.TerminalDTO;
import com.micarrera.dashboardbackend.entidad.Proyecto;
import com.micarrera.dashboardbackend.servicio.ServicioProyecto;
import com.micarrera.dashboardbackend.servicio.ServicioTerminal;

@RestController
@RequestMapping("/api/terminal")
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorTerminal {

    private final ServicioProyecto servicioProyecto;
    private final ServicioTerminal servicioTerminal;
    
    public ControladorTerminal(ServicioProyecto servicioProyecto, ServicioTerminal servicioTerminal) {
        this.servicioProyecto = servicioProyecto;
        this.servicioTerminal = servicioTerminal;
    }

    @GetMapping("/help")
    public TerminalDTO help() {
        String salida = servicioTerminal.formatearAyuda();
        return new TerminalDTO(salida, "exito", null);
    }

    @GetMapping("/proyectos")
    public TerminalDTO proyectos(@RequestParam(required = false) String estado, @RequestParam(required = false) String tecnologia) {
        List<Proyecto> lista = servicioProyecto.obtenerTodosLosProyectos();

        lista = estado != null || tecnologia != null ? servicioProyecto.filtrarProyectos(estado, tecnologia) : servicioProyecto.obtenerTodosLosProyectos();
        String salida = servicioTerminal.formatearListaProyectos(lista);
    
        return new TerminalDTO(salida, "exito", lista);
    }

    @GetMapping("/proyectos/{id}")
    public TerminalDTO proyectoDetalle(@PathVariable Long id) {
        String salida;
        String tipo;
        Object datos;
        
        try {
            Proyecto proyecto = servicioProyecto.obtenerProyectoPorId(id)
                    .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));

            salida = servicioTerminal.formatearDetalleProyecto(proyecto);
            tipo = "exito";
            datos = proyecto;
        } catch (Exception e) {
            salida = "Error: Proyecto con ID [" + id + "] no encontrado\n" +
                    "Usa 'proyectos' para ver IDs disponibles";
            tipo = "error";
            datos = null;
        }
        
        return new TerminalDTO(salida, tipo, datos);
    }


    @GetMapping("/about")
    public TerminalDTO about() {
        String salida = servicioTerminal.formatearAbout();
        return new TerminalDTO(salida, "info", null);
    }

    @GetMapping("/skills")
    public TerminalDTO skills() {
        String salida = servicioTerminal.formatearSkills();
        return new TerminalDTO(salida, "info", null);
    }

    @GetMapping("/stats")
    public TerminalDTO stats() {
        List<Proyecto> lista = servicioProyecto.obtenerTodosLosProyectos();
        String salida = servicioTerminal.formatearEstadisticas(lista);
        return new TerminalDTO(salida, "info", lista);
    }

    @GetMapping("/contacto")
    public TerminalDTO contact() {
        String salida = servicioTerminal.formatearContacto();
        return new TerminalDTO(salida, "info", null);
    }

    @GetMapping("/h")
    public TerminalDTO aliasHelp() {
        return help();
    }

    @GetMapping("/p")
    public TerminalDTO aliasProyectos(@RequestParam(required = false) String estado, @RequestParam(required = false) String tecnologia) {
        return proyectos(estado, tecnologia);
    }

    @GetMapping("/a")
    public TerminalDTO aliasAbout() {
        return about();
    }

    @GetMapping("/s")
    public TerminalDTO aliasSkills() {
        return skills();
    }

    @GetMapping("/c")
    public TerminalDTO aliasContact() {
        return contact();
    }

    @GetMapping("/{comando}")
    public TerminalDTO comandoInvalido(@PathVariable String comando) {
        String salida = servicioTerminal.formatearComandoInvalido(comando);
        return new TerminalDTO(salida, "error", null);
    }
}
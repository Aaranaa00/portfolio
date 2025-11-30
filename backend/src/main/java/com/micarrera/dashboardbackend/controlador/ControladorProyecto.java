package com.micarrera.dashboardbackend.controlador;

import com.micarrera.dashboardbackend.modelo.Proyecto;
import com.micarrera.dashboardbackend.servicio.ServicioProyecto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/proyectos")
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorProyecto {

    private ServicioProyecto servicioProyecto;

    public ControladorProyecto(ServicioProyecto servicioProyecto) {
        this.servicioProyecto = servicioProyecto;
    }

    @GetMapping
    public List<Proyecto> obtenerTodos() {
        return servicioProyecto.obtenerTodosLosProyectos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proyecto> obtenerPorId(@PathVariable Long id) {
        return servicioProyecto.obtenerProyectoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Proyecto> crear(@RequestBody Proyecto proyecto) {
        Proyecto nuevoProyecto = servicioProyecto.guardarProyecto(proyecto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProyecto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proyecto> actualizar(@PathVariable Long id, @RequestBody Proyecto proyecto) {
        return servicioProyecto.obtenerProyectoPorId(id)
                .map(p -> {
                    proyecto.setId(id);
                    return ResponseEntity.ok(servicioProyecto.guardarProyecto(proyecto));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        servicioProyecto.eliminarProyecto(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/destacados")
    public List<Proyecto> obtenerDestacados() {
        return servicioProyecto.obtenerProyectosDestacados();
    }
}

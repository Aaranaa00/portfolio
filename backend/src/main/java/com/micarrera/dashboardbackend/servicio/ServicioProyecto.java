package com.micarrera.dashboardbackend.servicio;

import com.micarrera.dashboardbackend.modelo.Proyecto;
import com.micarrera.dashboardbackend.repositorio.RepositorioProyecto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioProyecto {
    private RepositorioProyecto repositorioProyecto;

    public ServicioProyecto(RepositorioProyecto repositorioProyecto) {
        this.repositorioProyecto = repositorioProyecto;
    }

    public List<Proyecto> obtenerTodosLosProyectos() {
        return repositorioProyecto.findAll();
    }

    public Optional<Proyecto> obtenerProyectoPorId(Long id) {
        return repositorioProyecto.findById(id);
    }

    public Proyecto guardarProyecto(Proyecto proyecto) {
        return repositorioProyecto.save(proyecto);
    }

    public void eliminarProyecto(Long id) {
        repositorioProyecto.deleteById(id);
    }

    public List<Proyecto> obtenerProyectosDestacados() {
        return repositorioProyecto.findByDestacadoTrue();
    }

    public List<Proyecto> obtenerProyectosPorEstado(String estado) {
        return repositorioProyecto.findByEstado(estado);
    }
}
package com.micarrera.dashboardbackend.servicio;

import com.micarrera.dashboardbackend.entidad.Proyecto;
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

    private boolean cumpleFiltros(Proyecto p, String estado, String tecnologia) {
        boolean cumpleEstado = (estado == null || estado.isEmpty() || 
                            p.getEstado().equalsIgnoreCase(estado));
        
        boolean cumpleTecnologia = (tecnologia == null || tecnologia.isEmpty() || 
                            p.getTecnologias().toLowerCase().contains(tecnologia.toLowerCase()));
        
        return cumpleEstado && cumpleTecnologia;
    }

    public List<Proyecto> filtrarProyectos(String estado, String tecnologia) {
        // Obtener TODOS los proyectos de la BD
        List<Proyecto> todosLosProyectos = obtenerTodosLosProyectos();

        // Devolver proyectos segun el filtro
        List<Proyecto> resultado = todosLosProyectos.stream()
                .filter(p -> cumpleFiltros(p, estado, tecnologia))
                .toList();
        return resultado;
    }
}
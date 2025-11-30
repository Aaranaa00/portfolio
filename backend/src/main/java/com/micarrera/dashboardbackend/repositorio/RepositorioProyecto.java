package com.micarrera.dashboardbackend.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.micarrera.dashboardbackend.entidad.Proyecto;

import java.util.List;

@Repository
public interface RepositorioProyecto extends JpaRepository<Proyecto, Long> {
    List<Proyecto> findByDestacadoTrue();
    List<Proyecto> findByEstado(String estado);
}

package com.micarrera.dashboardbackend.entidad;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "proyectos")
@Data
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombreProyecto;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(length = 500)
    private String tecnologias;

    private LocalDate fechaInicio;

    @Column(length = 50)
    private String estado; // "En desarrollo", "Completado", "Pausado"

    private String urlGithub;

    private String urlDemo;

    private Boolean destacado = false;
}
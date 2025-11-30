import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProyectoService } from '../../services/proyecto';
import { Proyecto } from '../../models/proyecto';

@Component({
  selector: 'app-proyectos',
  imports: [CommonModule],
  templateUrl: './proyectos.html',
  styleUrl: './proyectos.scss'
})

export class ProyectosComponent implements OnInit {
  proyectos: Proyecto[] = [];

  constructor(
    private proyectoService: ProyectoService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.cargarProyectos();
  }

  cargarProyectos(): void {
    this.proyectoService.obtenerTodos().subscribe({
      next: (data : Proyecto[]) => {
        this.proyectos = data;
        console.log('Proyectos cargados:', this.proyectos);
        this.cdr.detectChanges();
      },
      error: (error: any) => {
        console.error('Error al cargar proyectos:', error);
      }
    });
  }
}

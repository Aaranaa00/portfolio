import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ProyectosComponent } from './components/proyectos/proyectos';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, ProyectosComponent],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
  protected readonly title = signal('frontend');
}

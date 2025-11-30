import { Routes } from '@angular/router';
import { ProyectosComponent } from './components/proyectos/proyectos';

export const routes: Routes = [
  { path: '', component: ProyectosComponent },
  { path: 'proyectos', component: ProyectosComponent }
];

export interface Proyecto {
  id?: number;
  nombreProyecto: string;
  descripcion: string;
  tecnologias: string;
  fechaInicio: string;
  estado: string;
  urlGithub?: string;
  urlDemo?: string;
  destacado: boolean;
}
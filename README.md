# Mi Portfolio - Manuel Arana

Hola! Este es mi portfolio personal que hice con la idea de que fuera diferente. En vez de una página web tradicional, lo diseñé como una terminal de comandos porque me pareció más interesante.

## ¿Qué es esto?

Es una aplicación web fullstack que simula una terminal Linux. Básicamente puedes escribir comandos y la terminal te responde con mi información profesional. Lo hice así porque quería algo que se viera distinto y que mostrara mis habilidades técnicas de una forma creativa.

## Stack Tecnológico

Elegí estas tecnologías porque son las que manejo y están muy demandadas en el mercado:

- **Frontend**: Angular 21 (standalone components)
- **Backend**: Spring Boot 4.0 con Java 21
- **Estilos**: SCSS con tema oscuro/claro
- **Despliegue**: Docker multi-stage
- **Hosting**: Koyeb

## Comandos Disponibles

Cuando entres a la terminal, puedes usar estos comandos:

- `proyectos` o `p` - Muestra mis proyectos de GitHub en tiempo real (se conecta a la API)
- `about` o `a` - Info sobre mí, mi formación y experiencia
- `skills` o `s` - Tecnologías que manejo (frontend, backend, herramientas)
- `contacto` o `c` - Mis redes sociales y email
- `clear` - Limpia la pantalla
- `help` - Lista todos los comandos
- `theme` - Cambia entre modo oscuro y claro

También puedes filtrar proyectos con: `proyectos --tecnologia java` por ejemplo.

## ¿Por qué lo hice así?

Quería crear algo que:
1. Mostrara mis habilidades fullstack
2. Fuera interactivo y entretenido
3. Se diferenciara de los portfolios típicos
4. Demostrara que puedo integrar frontend con backend
5. Me permitiera practicar con tecnologías modernas

## Arquitectura

La app funciona así:
- El frontend en Angular se comunica con mi backend
- El backend tiene endpoints REST que procesan los comandos
- Hay un servicio que consulta la API de GitHub para mostrar proyectos reales
- Todo empaquetado en Docker para facilitar el despliegue

## Puedes acceder a la web cin este enlace

https://beneficial-wendi-arana-5afbd4fd.koyeb.app/

## Cómo ejecutarlo localmente

Si quieres probarlo en tu máquina:

### Con Docker (más fácil)
```bash
docker build -t portfolio .
docker run -p 8080:8080 portfolio
```

Luego abre: `http://localhost:8080`

### Sin Docker (modo desarrollo)

**Backend:**
```bash
cd backend
./mvnw spring-boot:run
```

**Frontend:**
```bash
cd frontend
npm install
npm start
```

El frontend corre en `http://localhost:4200` y el backend en `http://localhost:8080`

## 🌐 Despliegue

Esta aplicación está desplegada en Koyeb usando el Dockerfile incluido. El proceso de build tarda unos minutos porque compila tanto el frontend como el backend.

## Contacto

Si quieres contactarme o ver más de mi trabajo:

- **Email**: manuelaranajob@gmail.com
- **LinkedIn**: [linkedin.com/in/arana00](https://linkedin.com/in/arana00)
- **GitHub**: [github.com/Aaranaa00](https://github.com/Aaranaa00)

## Notas

Este proyecto lo desarrollé como parte de mi portfolio profesional. Estoy abierto a feedback y sugerencias de mejora. Si ves algo que se puede optimizar o tienes ideas, no dudes en contactarme!

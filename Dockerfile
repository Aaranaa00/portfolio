# Stage 1: Build Frontend
FROM node:20-alpine AS frontend-build
WORKDIR /app/frontend

# Copiar solo package files primero para aprovechar cache
COPY frontend/package*.json ./
RUN npm ci --only=production --silent

# Copiar código y construir
COPY frontend/ ./
RUN npm run build -- --configuration production

# Stage 2: Build Backend
FROM eclipse-temurin:21-jdk-alpine AS backend-build
WORKDIR /app

# Copiar archivos Maven primero para cache de dependencias
COPY backend/pom.xml .
COPY backend/.mvn .mvn
COPY backend/mvnw .
RUN chmod +x mvnw && ./mvnw dependency:go-offline -B

# Copiar código fuente
COPY backend/src ./src

# Copiar frontend build a static resources
COPY --from=frontend-build /app/frontend/dist/frontend/browser/ ./src/main/resources/static/

# Construir JAR optimizado
RUN ./mvnw clean package -DskipTests -Dmaven.test.skip=true

# Stage 3: Runtime (imagen mínima)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Crear usuario no-root para seguridad
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

# Copiar JAR
COPY --from=backend-build /app/target/*.jar app.jar

# Cambiar permisos
RUN chown -R appuser:appgroup /app
USER appuser

EXPOSE 8080

# Variables de entorno para optimización JVM
ENV JAVA_OPTS="-Xms256m -Xmx512m -XX:+UseContainerSupport"

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]

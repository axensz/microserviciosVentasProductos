# Build stage
FROM eclipse-temurin:17-jdk-alpine AS builder

# Instalar bash porque lo necesita mvnw
RUN apk add --no-cache bash

WORKDIR /app

# Copiar todo el proyecto (incluye src/, pom.xml, mvnw, etc.)
COPY . .

# Dar permisos al wrapper
RUN chmod +x mvnw

# Descargar dependencias
RUN ./mvnw dependency:go-offline

# Construir el JAR (sin ejecutar tests)
RUN ./mvnw clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copiar solo el JAR generado desde el builder
COPY --from=builder /app/target/*.jar app.jar

# Exponer el puerto por defecto de Spring Boot
EXPOSE 8081

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"] 
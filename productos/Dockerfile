# Build stage
FROM eclipse-temurin:17-jdk-alpine AS builder

# Instalar bash porque lo necesita mvnw
RUN apk add --no-cache bash

WORKDIR /app

# Copiar archivos necesarios de Maven wrapper
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x mvnw

# Descargar dependencias
RUN ./mvnw dependency:go-offline

# Copiar el resto del código fuente
COPY src ./src

# Construir el JAR del proyecto (sin ejecutar tests)
RUN ./mvnw clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copiar solo el JAR desde el stage de construcción
COPY --from=builder /app/target/*.jar app.jar

# Exponer el puerto por defecto de Spring Boot
EXPOSE 8080

# Ejecutar el microservicio
ENTRYPOINT ["java", "-jar", "app.jar"] 
# Microservicio de Productos

Este microservicio es responsable de gestionar la información de los productos, incluyendo operaciones CRUD (Crear, Leer, Actualizar, Eliminar) y la actualización de stock.

Está desarrollado con Java 17, Spring Boot 3 y sigue principios de Arquitectura Limpia.

## Requisitos Previos

- Java 17 o superior
- Maven 3.8 o superior
- Docker y Docker Compose (si se ejecuta con la configuración de `docker-compose.yml` del proyecto principal)

## Configuración

La configuración de la aplicación se encuentra en `src/main/resources/application.yml`. Por defecto, está configurado para conectarse a una base de datos PostgreSQL que se espera esté disponible a través de Docker (ver el `docker-compose.yml` en la raíz del proyecto `microserviciosVentasProductos`).

- **URL JDBC:** `jdbc:postgresql://postgres-productos:5432/productos_db`
- **Usuario:** `productos_user`
- **Contraseña:** `productos_pass`

## Compilación y Empaquetado

Para compilar el proyecto y generar el archivo JAR, ejecuta el siguiente comando Maven desde el directorio raíz del microservicio (`productos`):

```bash
./mvnw clean package
# o si estás en Windows y mvnw no funciona directamente:
# mvnw.cmd clean package
```

Esto generará un archivo JAR en el directorio `target/`.

## Ejecución

### Desde el JAR (después de compilar)

Una vez compilado, puedes ejecutar el microservicio con el siguiente comando:

```bash
java -jar target/productos-0.0.1-SNAPSHOT.jar
```

(Asegúrate de que la base de datos PostgreSQL esté accesible).

### Con Docker Compose (Recomendado para desarrollo)

El proyecto principal `microserviciosVentasProductos` incluye un archivo `docker-compose.yml` que orquesta este microservicio y su base de datos. Para levantarlo:

1.  Navega al directorio raíz del proyecto `microserviciosVentasProductos`.
2.  Ejecuta:
    ```bash
    docker-compose up -d --build productos
    ```

El servicio estará disponible en `http://localhost:8081` (según la configuración del `docker-compose.yml`).

## Documentación de la API (Swagger UI)

Una vez que el microservicio esté en ejecución, puedes acceder a la documentación interactiva de la API a través de Swagger UI en la siguiente URL:

[http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html)

La especificación OpenAPI (JSON) está disponible en:

[http://localhost:8081/api-docs](http://localhost:8081/api-docs)

## Generación de JavaDoc

Para generar la documentación JavaDoc del código fuente, ejecuta el siguiente comando Maven desde el directorio raíz del microservicio (`productos`):

```bash
./mvnw javadoc:javadoc
# o si estás en Windows:
# mvnw.cmd javadoc:javadoc
```

La documentación se generará en el directorio `target/site/apidocs/`. Puedes abrir el archivo `index.html` en tu navegador para verla.

## Estructura del Proyecto (Arquitectura Limpia)

El proyecto sigue una estructura basada en la Arquitectura Limpia, dividida en las siguientes capas principales:

-   **Domain (`com.pragma.domain`):** Contiene los modelos de negocio (entidades de dominio) y las interfaces de los repositorios. Es el núcleo de la aplicación y no depende de ninguna otra capa.
-   **Application (`com.pragma.application`):** Contiene la lógica de los casos de uso (servicios de aplicación) y los puertos de entrada (interfaces que exponen los casos de uso). Orquesta el flujo de datos entre el dominio y la infraestructura.
-   **Infrastructure (`com.pragma.infrastructure`):** Contiene las implementaciones concretas de las interfaces definidas en las capas de dominio y aplicación. Esto incluye:
    -   **Input/REST (`com.pragma.infrastructure.input.rest`):** Controladores REST, DTOs y mappers para la exposición de la API.
    -   **Output/Persistence (`com.pragma.infrastructure.output.persistence`):** Adaptadores para la base de datos (JPA Entities, Repositorios Spring Data JPA, mappers de persistencia).

## Endpoints Principales

El controlador principal es `ProductController.java` y expone los siguientes endpoints bajo el path base `/api/v1/productos`:

-   `POST /`: Crea un nuevo producto.
-   `GET /{id}`: Obtiene un producto por su ID.
-   `GET /`: Obtiene todos los productos.
-   `PUT /{id}`: Actualiza un producto existente.
-   `DELETE /{id}`: Elimina un producto por su ID.
-   `PATCH /{id}/stock`: Actualiza el stock de un producto (permite cantidades positivas para sumar y negativas para restar).

Consulta Swagger UI para más detalles sobre los DTOs de solicitud y respuesta.

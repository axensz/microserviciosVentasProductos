# microserviciosVentasProductos

Este proyecto contiene dos microservicios desarrollados con Spring Boot siguiendo principios de arquitectura limpia. Los servicios implementan una solución modular para la gestión de productos y ventas, con una comunicación desacoplada vía Feign.

## Microservicios incluidos

### productos

Encargado de administrar los productos del sistema. Permite agregar, quitar o modificar el stock.

### ventas

Encargado de registrar ventas, disminuir el stock de productos correspondientes y mantener un histórico de ventas.

## Estructura del proyecto

```
microserviciosVentasProductos/
├── productos/
│   └── src/main/java/com/pragma/
│       ├── domain/
│       ├── application/
│       │   ├── useCase/
│       │   └── service/
│       ├── infrastructure/
│       │   ├── input/
│       │   ├── output/
│       │   └── persistence/
│       └── ProductosApplication.java
├── ventas/
│   └── src/main/java/com/pragma/
│       ├── domain/
│       ├── application/
│       │   ├── useCase/
│       │   └── service/
│       ├── infrastructure/
│       │   ├── input/
│       │   ├── output/
│       │   └── persistence/
│       └── VentasApplication.java
```

## Tecnologías

* Java 17
* Spring Boot 3.x
* Maven
* Feign

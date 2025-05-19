package com.pragma.infrastructure.input.rest.controller;

import com.pragma.application.port.input.ProductInputPort;
import com.pragma.domain.model.Producto;
import com.pragma.infrastructure.input.rest.dto.ProductRequestDto;
import com.pragma.infrastructure.input.rest.dto.ProductResponseDto;
import com.pragma.infrastructure.input.rest.mapper.ProductRestMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de productos.
 * <p>
 * Proporciona endpoints para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * sobre los productos, así como para actualizar su stock.
 * Utiliza {@link ProductInputPort} para interactuar con la lógica de negocio
 * y {@link ProductRestMapper} para el mapeo entre DTOs y el modelo de dominio.
 */
@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
@Tag(name = "Productos", description = "API para la gestión de productos")
public class ProductController {

    private final ProductInputPort productInputPort;
    private final ProductRestMapper productRestMapper;

    /**
     * Crea un nuevo producto.
     *
     * @param productRequestDto DTO con la información del producto a crear.
     * @return ResponseEntity con el producto creado y estado HTTP 201 (Created).
     */
    @PostMapping
    @Operation(summary = "Crear un nuevo producto",
            description = "Crea un nuevo producto en el sistema.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Producto creado exitosamente",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponseDto.class))),
                    @ApiResponse(responseCode = "400", description = "Solicitud inválida")
            })
    public ResponseEntity<ProductResponseDto> crearProducto(@Valid @RequestBody ProductRequestDto productRequestDto) {
        Producto producto = productRestMapper.toProducto(productRequestDto);
        Producto productoCreado = productInputPort.crearProducto(producto);
        return new ResponseEntity<>(productRestMapper.toProductResponseDto(productoCreado), HttpStatus.CREATED);
    }

    /**
     * Obtiene un producto por su ID.
     *
     * @param id El ID del producto a obtener.
     * @return ResponseEntity con el producto encontrado y estado HTTP 200 (OK),
     * o estado HTTP 404 (Not Found) si el producto no existe.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Obtener un producto por ID",
            description = "Recupera los detalles de un producto específico utilizando su ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Producto encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponseDto.class))),
                    @ApiResponse(responseCode = "404", description = "Producto no encontrado")
            })
    public ResponseEntity<ProductResponseDto> obtenerProductoPorId(@PathVariable Long id) {
        Producto producto = productInputPort.obtenerProductoPorId(id);
        return ResponseEntity.ok(productRestMapper.toProductResponseDto(producto));
    }

    /**
     * Obtiene todos los productos.
     *
     * @return ResponseEntity con la lista de productos y estado HTTP 200 (OK).
     */
    @GetMapping
    @Operation(summary = "Obtener todos los productos",
            description = "Recupera una lista de todos los productos disponibles.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de productos recuperada",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponseDto.class)))
            })
    public ResponseEntity<List<ProductResponseDto>> obtenerTodosLosProductos() {
        List<Producto> productos = productInputPort.obtenerTodosLosProductos();
        return ResponseEntity.ok(productRestMapper.toProductResponseDtoList(productos));
    }

    /**
     * Actualiza un producto existente.
     *
     * @param id                El ID del producto a actualizar.
     * @param productRequestDto DTO con la información actualizada del producto.
     * @return ResponseEntity con el producto actualizado y estado HTTP 200 (OK),
     * o estado HTTP 404 (Not Found) si el producto no existe.
     */
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un producto existente",
            description = "Actualiza los detalles de un producto existente utilizando su ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Producto actualizado exitosamente",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponseDto.class))),
                    @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
                    @ApiResponse(responseCode = "404", description = "Producto no encontrado")
            })
    public ResponseEntity<ProductResponseDto> actualizarProducto(@PathVariable Long id, @Valid @RequestBody ProductRequestDto productRequestDto) {
        Producto producto = productRestMapper.toProducto(productRequestDto);
        // Es importante asegurar que el ID del path se use para la actualización
        // y no se sobrescriba por un ID en el DTO si este fuera diferente.
        // El ProductUseCase debería manejar la lógica de si el ID en el path y el DTO deben coincidir.
        Producto productoActualizado = productInputPort.actualizarProducto(id, producto);
        return ResponseEntity.ok(productRestMapper.toProductResponseDto(productoActualizado));
    }

    /**
     * Elimina un producto por su ID.
     *
     * @param id El ID del producto a eliminar.
     * @return ResponseEntity con estado HTTP 204 (No Content) si la eliminación fue exitosa,
     * o estado HTTP 404 (Not Found) si el producto no existe.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un producto por ID",
            description = "Elimina un producto del sistema utilizando su ID.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Producto eliminado exitosamente"),
                    @ApiResponse(responseCode = "404", description = "Producto no encontrado")
            })
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productInputPort.eliminarProductoPorId(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Actualiza el stock de un producto.
     *
     * @param id       El ID del producto cuyo stock se va a actualizar.
     * @param cantidad La cantidad a sumar o restar del stock (positiva para sumar, negativa para restar).
     * @return ResponseEntity con el producto actualizado y estado HTTP 200 (OK),
     * o estado HTTP 404 (Not Found) si el producto no existe,
     * o estado HTTP 400 (Bad Request) si la actualización del stock no es válida (ej. stock resultante negativo).
     */
    @PatchMapping("/{id}/stock")
    @Operation(summary = "Actualizar el stock de un producto",
            description = "Modifica la cantidad de stock de un producto. Usar valores positivos para incrementar y negativos para decrementar.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Stock actualizado exitosamente",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponseDto.class))),
                    @ApiResponse(responseCode = "400", description = "Actualización de stock inválida (ej. stock negativo)"),
                    @ApiResponse(responseCode = "404", description = "Producto no encontrado")
            })
    public ResponseEntity<ProductResponseDto> actualizarStockProducto(@PathVariable Long id, @RequestParam Integer cantidad) {
        Producto productoActualizado = productInputPort.actualizarStockProducto(id, cantidad);
        return ResponseEntity.ok(productRestMapper.toProductResponseDto(productoActualizado));
    }
}

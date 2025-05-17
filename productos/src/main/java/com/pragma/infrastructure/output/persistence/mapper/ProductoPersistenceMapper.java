package com.pragma.infrastructure.output.persistence.mapper;

import com.pragma.domain.model.Producto;
import com.pragma.infrastructure.output.persistence.entity.ProductoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * Mapper para la conversión entre el modelo de dominio {@link Producto}
 * y la entidad de persistencia {@link ProductoEntity}.
 * Utiliza MapStruct para generar la implementación de este mapeo.
 * El atributo {@code componentModel = "spring"} permite que MapStruct genere un bean de Spring
 * que puede ser inyectado en otras clases (por ejemplo, en el adaptador del repositorio).
 */
@Mapper(componentModel = "spring")
public interface ProductoPersistenceMapper {

    /**
     * Convierte una entidad {@link ProductoEntity} a un objeto de dominio {@link Producto}.
     *
     * @param productoEntity La entidad de persistencia a convertir.
     * @return El objeto de dominio {@link Producto} correspondiente.
     */
    @Mappings({
        @Mapping(source = "id", target = "id"),
        @Mapping(source = "nombre", target = "nombre"),
        @Mapping(source = "descripcion", target = "descripcion"),
        @Mapping(source = "precio", target = "precio"),
        @Mapping(source = "stock", target = "stock")
    })
    Producto toProducto(ProductoEntity productoEntity);

    /**
     * Convierte un objeto de dominio {@link Producto} a una entidad {@link ProductoEntity}.
     *
     * @param producto El objeto de dominio a convertir.
     * @return La entidad de persistencia {@link ProductoEntity} correspondiente.
     */
    @Mappings({
        @Mapping(source = "id", target = "id"),
        @Mapping(source = "nombre", target = "nombre"),
        @Mapping(source = "descripcion", target = "descripcion"),
        @Mapping(source = "precio", target = "precio"),
        @Mapping(source = "stock", target = "stock")
    })
    ProductoEntity toProductoEntity(Producto producto);

    /**
     * Convierte una lista de entidades {@link ProductoEntity} a una lista de objetos de dominio {@link Producto}.
     *
     * @param productoEntities La lista de entidades de persistencia a convertir.
     * @return Una lista de objetos de dominio {@link Producto} correspondientes.
     */
    List<Producto> toProductoList(List<ProductoEntity> productoEntities);

    /**
     * Convierte una lista de objetos de dominio {@link Producto} a una lista de entidades {@link ProductoEntity}.
     *
     * @param productos La lista de objetos de dominio a convertir.
     * @return Una lista de entidades de persistencia {@link ProductoEntity} correspondientes.
     */
    List<ProductoEntity> toProductoEntityList(List<Producto> productos);
}

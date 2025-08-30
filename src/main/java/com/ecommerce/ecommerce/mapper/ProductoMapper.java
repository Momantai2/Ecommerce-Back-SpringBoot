package com.ecommerce.ecommerce.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import com.ecommerce.ecommerce.dto.ProductoRequestDTO;
import com.ecommerce.ecommerce.dto.ProductoResponseDTO;
import com.ecommerce.ecommerce.models.Producto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductoMapper {

    @Mapping(target = "idProducto", ignore = true)
    @Mapping(target = "categoria", ignore = true)
    @Mapping(target = "unidadMedida", ignore = true)
    @Mapping(target = "fechaRegistro", ignore = true)
    Producto toEntity(ProductoRequestDTO dto);

    @Mapping(source = "categoria.idCategoria", target = "idCategoria")
    @Mapping(source = "unidadMedida.idUnidadMedida", target = "idUnidadMedida")
    @Mapping(source = "unidadMedida.nombre", target = "nombreUnidadMedida")
    @Mapping(source = "categoria.nombre", target = "nombreCategoria")

    ProductoResponseDTO toDto(Producto entity);

    @Mapping(target = "idProducto", ignore = true)
    @Mapping(target = "categoria", ignore = true)
    @Mapping(target = "unidadMedida", ignore = true)
    @Mapping(target = "fechaRegistro", ignore = true)
    void updateEntityFromDto(ProductoRequestDTO dto, @MappingTarget Producto entity);

    List<ProductoResponseDTO> toDtoList(List<Producto> entities);
}

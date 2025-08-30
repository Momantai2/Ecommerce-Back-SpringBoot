package com.ecommerce.ecommerce.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoRequestDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede exceder los 100 caracteres")
    private String nombre;

    @Size(max = 500, message = "La descripción no puede exceder los 500 caracteres")
    private String descripcion;

    @NotNull(message = "El precio es obligatorio")
    private BigDecimal precio;

    @Size(max = 255, message = "La URL de la imagen no puede exceder los 255 caracteres")
    private String imagenUrl;

    @NotNull(message = "La categoría es obligatoria")
    private Long idCategoria;

    @NotNull(message = "La unidad de medida es obligatoria")
    private Long idUnidadMedida;

    @NotNull(message = "El stock es obligatorio")
    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stock;

     @NotNull(message = "El estado activo es obligatorio")
    private Boolean estado;
}

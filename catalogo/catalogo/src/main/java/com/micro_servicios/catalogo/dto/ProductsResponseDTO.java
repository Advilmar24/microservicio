package com.micro_servicios.catalogo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductsResponseDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;
    private String categoria;
    
}

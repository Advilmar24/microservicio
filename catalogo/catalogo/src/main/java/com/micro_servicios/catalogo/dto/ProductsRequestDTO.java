package com.micro_servicios.catalogo.dto;

import lombok.Data;

@Data
public class ProductsRequestDTO {
    
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;
    private String categoria;
}

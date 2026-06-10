package com.micro_servicios.catalogo.config;

public class ProductNotFoundException extends RuntimeException {
    
    public ProductNotFoundException(Integer id){
        super("Producto con Id " + id + " no encontrado");
    }
}

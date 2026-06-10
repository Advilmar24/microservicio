package com.micro_servicios.catalogo.entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

@Entity
@Data
@Table(name = "productos")
public class Productos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    
    @Column(name = "nombre")
    private String nombre;

    
    @Column(name = "descripcion")
    private String descripcion;

    
    @Column(name = "precio")
    private Double precio;

    
    @Column(name = "stock")
    private Integer stock;

    
    @Column(name = "categoria")
    private String categoria;




}

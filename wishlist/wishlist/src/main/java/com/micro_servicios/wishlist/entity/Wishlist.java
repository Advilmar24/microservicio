package com.micro_servicios.wishlist.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Entity
@Table(name = "wishlist")
@Data
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "usuario_id")
    private Long usuarioId;

    @NotNull
    @Column(name = "producto_id")
    private Long productoId;

    @NotBlank
    @Column(name = "estado")
    private String estado;

    @Column (name = "fecha_agregado")
    private LocalDateTime fechaAgregado;
}

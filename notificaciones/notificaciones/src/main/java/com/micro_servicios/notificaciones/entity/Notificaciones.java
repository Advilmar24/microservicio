package com.micro_servicios.notificaciones.entity;

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
@Data
@Table(name = "notificaciones")
public class Notificaciones {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "usuario_id")
    private Long usuarioId;

    @NotNull
    @Column(name = "producto_id")
    private Long productoId;

    @NotBlank
    @Column(name = "mensaje")
    private String mensaje;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    @NotBlank
    @Column(name = "estado")
    private String estado;
    
}

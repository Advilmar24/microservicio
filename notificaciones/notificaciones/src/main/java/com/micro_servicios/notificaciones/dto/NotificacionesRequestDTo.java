package com.micro_servicios.notificaciones.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class NotificacionesRequestDTo {

    private Long usuarioId;

    private Long ProductoId;

    private String mensaje;

    private LocalDateTime fecha;

    private String estado;

    
}

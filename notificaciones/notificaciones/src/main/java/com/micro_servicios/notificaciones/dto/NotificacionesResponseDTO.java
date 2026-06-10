package com.micro_servicios.notificaciones.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificacionesResponseDTO {

    private Long id;

    private Long usuarioId;

    private Long productoId;

    private String mensaje;

    private LocalDateTime fecha;

    private String estado;
}

package com.micro_servicios.wishlist.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WishListHistorialResponseDTO {

    private Long Id;

    private Long wishListId;

    private long usuarioId;

    private Long productoId;

    private String accion;

    private LocalDateTime fecha;
    
}

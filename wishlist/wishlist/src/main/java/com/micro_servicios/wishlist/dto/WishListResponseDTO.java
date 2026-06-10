package com.micro_servicios.wishlist.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WishListResponseDTO {
    
    private Long id;

    private Long usuarioId;

    private Long productoId;

    private String estado;

    private LocalDateTime fechaAgregado;
    
}

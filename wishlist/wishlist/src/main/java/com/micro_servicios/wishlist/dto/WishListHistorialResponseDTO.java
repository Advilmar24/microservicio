package com.micro_servicios.wishlist.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class WishListHistorialResponseDTO {

    private Long Id;

    private Long wishListId;

    private Long productoId;

    private String acccion;

    private LocalDateTime fecha;
    
}

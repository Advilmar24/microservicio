package com.micro_servicios.wishlist.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data

public class WishListRequestDTO {

    private Long usuarioId;

    private Long productoId;

    private String estado;

    private LocalDateTime fechaAgregado;
    
}

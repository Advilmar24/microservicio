package com.micro_servicios.auth.dto;

import lombok.Data;

@Data
public class RefreshTokenResponseDTO {
    
    private String message;

    private String jwt;
}

package com.micro_servicios.auth.dto;

import lombok.Data;

@Data
public class LoginResponseDTO extends MessageResponseDTO {
    
    private String jwt;
}

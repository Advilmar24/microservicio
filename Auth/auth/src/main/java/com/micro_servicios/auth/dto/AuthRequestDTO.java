package com.micro_servicios.auth.dto;

import lombok.Data;

@Data
public class AuthRequestDTO {
    
    private String name;
    private String email;
    private String password;
    private Long idrol;
}

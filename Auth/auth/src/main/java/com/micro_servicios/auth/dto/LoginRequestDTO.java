package com.micro_servicios.auth.dto;

import lombok.Data;

@Data
public class LoginRequestDTO {
    
    private String email;

    private String password;
}

package com.micro_servicios.auth.role;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum RolEnum {
    
    VENDEDOR(1L),
    USUARIO(2L);

    private final Long id;
}

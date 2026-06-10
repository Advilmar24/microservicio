package com.micro_servicios.auth.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.micro_servicios.auth.dto.LoginRequestDTO;
import com.micro_servicios.auth.dto.LoginResponseDTO;
import com.micro_servicios.auth.dto.MessageResponseDTO;
import com.micro_servicios.auth.dto.RefreshTokenResponseDTO;
import com.micro_servicios.auth.dto.RegisterRequestDTO;
import com.micro_servicios.auth.entity.UsersEntity;
import com.micro_servicios.auth.repository.AuthRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    public MessageResponseDTO register(RegisterRequestDTO request) {
        MessageResponseDTO response = new MessageResponseDTO();
        response.setMessage("Registro exitoso");

        if (authRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Este nombre de usuario ya está en uso");
        }

        UsersEntity user = new UsersEntity();
        user.setNombre(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setIdRole(request.getIdrol());

        authRepository.save(user);

        return response;
    }

    public LoginResponseDTO login(LoginRequestDTO request) {
        LoginResponseDTO response = new LoginResponseDTO();
        Optional<UsersEntity> user = authRepository.findByEmail(request.getEmail());

        if (user.isEmpty() && request.getEmail() != null) {
            response.setMessage("Este usuario no se encuentra registrado");
            return response;
        }

        UsersEntity userFound = user.get();

        if (!passwordEncoder.matches(request.getPassword(), userFound.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        String jwt = jwtService.generateToken(userFound.getId(), userFound.getIdRole(), userFound.getEmail());

        response.setMessage("Inicio de sesión exitoso");
        response.setJwt(jwt);
        return response;
    }

    /** 
     * Este método es para el refresco del token
     * @param token jwt viejo
     * @return nuevo token
     * @throws Exception 
     */
    public RefreshTokenResponseDTO refreshToken(String token) throws Exception {
        String jwt = jwtService.refreshToken(token);
        RefreshTokenResponseDTO response = new RefreshTokenResponseDTO();
        response.setMessage("ok");
        response.setJwt(jwt);
        return response;
    }
    
}

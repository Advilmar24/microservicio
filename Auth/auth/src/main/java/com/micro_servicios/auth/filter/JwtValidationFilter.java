/*package com.micro_servicios.auth.filter;


import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.micro_servicios.auth.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtValidationFilter extends OncePerRequestFilter {
    /**
     * Servicio de jwt
    *
    private final JwtService jwtService;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
        HttpServletResponse response, 
        FilterChain filterChain) 
        throws IOException {
        String autHeader = request.getHeader("Authorization");

        if (autHeader == null || !autHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Header is missing in the request\"}");
            return; // Cortamos la petición para que no llegue al controller
        }

        String token = autHeader.replace("Bearer ", "");

        try {
            if (jwtService.isTokenValid(token)) {
                String email = jwtService.extractUsername(token);
                Long userId = jwtService.extractUserId(token);
                Long idrole = jwtService.extractRolId(token);

                // Seteamos los atributos del payload para el alcance del controller
                request.setAttribute("email", email);
                request.setAttribute("userId", userId);
                request.setAttribute("idrole", idrole);

                // Si todo está bien, pasamos al siguiente paso, ya sea otra validación o al
                // controller directamente
                filterChain.doFilter(request, response);
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.getWriter().write("{\"error\": \"Token is invalid or expired\"}");
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Validation failed\"}");
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();

        return path.startsWith("/api/v1/auth/login");
    }
} */ //TODO ESTE ARCHIVO SE VA A MOVER AL GATEWAY

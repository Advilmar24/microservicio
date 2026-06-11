package com.micro_servicios.gateway.filter;

import java.util.List;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.micro_servicios.gateway.service.JwtService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter implements GlobalFilter {

    private final JwtService jwtService;

    private static final List<String> PUBLIC_ROUTES = List.of(
    "/api/v1/auth/login",
    "/api/v1/auth/register",
    "/api/v1/auth/refresh"
);

    @Override
    public Mono<Void> filter(
            ServerWebExchange exchange,
            GatewayFilterChain chain) {

        String path = exchange.getRequest().getURI().getPath();

        System.out.println("PATH RECIBIDO: " + path);
        
        if (PUBLIC_ROUTES.stream().anyMatch(path::startsWith)) {
            return chain.filter(exchange);
        }

        String authHeader =
                exchange.getRequest()
                        .getHeaders()
                        .getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader == null
                || !authHeader.startsWith("Bearer ")) {

            exchange.getResponse()
                    .setStatusCode(HttpStatus.UNAUTHORIZED);

            return exchange.getResponse().setComplete();
        }

        String token = authHeader.substring(7);

        try {

            if (!jwtService.isTokenValid(token)) {

                exchange.getResponse()
                        .setStatusCode(HttpStatus.UNAUTHORIZED);

                return exchange.getResponse().setComplete();
            }

            String email = jwtService.extractUsername(token);

            Long userId =
                    jwtService.extractUserId(token);

            Long roleId =
                    jwtService.extractRolId(token);

            ServerHttpRequest mutatedRequest =
                    exchange.getRequest()
                            .mutate()
                            .header("X-User-Id",
                                    String.valueOf(userId))
                            .header("X-User-Role",
                                    String.valueOf(roleId))
                            .header("X-User-Email",
                                    email)
                            .build();

            return chain.filter(
                    exchange.mutate()
                            .request(mutatedRequest)
                            .build());

        } catch (Exception e) {

            exchange.getResponse()
                    .setStatusCode(HttpStatus.UNAUTHORIZED);

            return exchange.getResponse().setComplete();
        }

        
    }

}

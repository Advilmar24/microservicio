package com.micro_servicios.auth.security;

import java.util.Arrays;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RequiresRoleInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        if (!(handler instanceof HandlerMethod method)) {
            return true; // No nos importa
        }

        RequiresRole annotation = method.getMethodAnnotation(RequiresRole.class);

        if (annotation == null) {
            annotation = method.getBeanType().getAnnotation(RequiresRole.class);
        }

        if (annotation == null) {
            return true; // Tampoco nos importa
        }

        Object rol = request.getAttribute("rolId");
        Long rolId = null;
        if (rol instanceof Long) {
            rolId = (Long) rol;
        } else if (rol instanceof String) {
            try {
                rolId = Long.parseLong((String) rol);
            } catch (NumberFormatException e) {
                // ignore
            }
        }

        if (rolId == null) {
            String rolHeader = request.getHeader("X-User-Rol");
            if (rolHeader != null) {
                try {
                    rolId = Long.parseLong(rolHeader);
                } catch (NumberFormatException e) {
                    // ignore
                }
            }
        }

        if (rolId == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Usuario no autenticado\"}");
            return false;
        }

        // Verifica si el rol del usuario esta dentro de los roles permitidos
        final Long finalRolId = rolId;
        boolean hasRole = Arrays.stream(annotation.value()).anyMatch(role -> role.getId() == finalRolId);

        if (!hasRole) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"No tienes permisos para realizar esta acción\"}");
            return false;
        }

        return true;
    }
}

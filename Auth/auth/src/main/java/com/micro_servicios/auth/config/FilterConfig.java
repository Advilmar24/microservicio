
package com.micro_servicios.auth.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.micro_servicios.auth.filter.JwtValidationFilter;

@Configuration
public class FilterConfig {

    @Bean
    FilterRegistrationBean<JwtValidationFilter> jwtfilter(JwtValidationFilter jwtValidationFilter){
    
    // Creamos un contenedor de registro del bean para el filtro
        FilterRegistrationBean<JwtValidationFilter> registrationBean = new FilterRegistrationBean<>();
        
        // Es decirle a spring que este es el filtro con el que quiero que trabaje
        registrationBean.setFilter(jwtValidationFilter);

        // Definir el alcance de este filtro, quiero que revise todas las peticiones que entre a mi aplicacion
        registrationBean.addUrlPatterns("/*");

        // Definimos el orden de prioridad de ejecución de este bean
        registrationBean.setOrder(1);

        // Retornmos el registro configurado para que spring lo guarde en su contexto
        return registrationBean;

    }
} //  ESTE DOCUMENTO SE MOVIO IGUALMENTE PARA GATEWAY***

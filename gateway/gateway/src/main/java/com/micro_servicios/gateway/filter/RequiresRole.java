package com.micro_servicios.gateway.filter;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.micro_servicios.gateway.filter.RolEnum;

// Esta anotacion va a actuar en los metodos
@Target(ElementType.METHOD)
// RUNTIME: Va a estar activa en tiempo de ejecución (siempre)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresRole {
    RolEnum[] value();
}

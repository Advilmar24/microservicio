package com.micro_servicios.notificaciones.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro_servicios.notificaciones.dto.MessageResponseDTO;
import com.micro_servicios.notificaciones.dto.NotificacionesRequestDTo;
import com.micro_servicios.notificaciones.dto.NotificacionesResponseDTO;
import com.micro_servicios.notificaciones.service.NotificacionesService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/notificacion")
@RequiredArgsConstructor
public class NotificacionesController {

    private final NotificacionesService notificacionesService;

    
    @PostMapping
    public MessageResponseDTO crear(@Valid @RequestBody NotificacionesRequestDTo requestDTo){
        return notificacionesService.crearNotificacion(requestDTo);
    }

    @GetMapping("path")
    public List<NotificacionesResponseDTO> listar(){
        return notificacionesService.listarTodas();
    }

    @GetMapping("/{usuarioId}")
    public List<NotificacionesResponseDTO> listarPorId(@PathVariable Integer usuarioId){
        return notificacionesService.listarPorUsuario(usuarioId);
    }
    
    @PutMapping("/{id}/estado")
    public NotificacionesResponseDTO actualizarEstado (@PathVariable Integer id, String nuevoEstado){
        return notificacionesService.actualizarEstado(id, nuevoEstado);
    }

    @DeleteMapping
    public MessageResponseDTO eliminar(@PathVariable Integer id){
        return notificacionesService.eliminarNotificacion(id);
    }
    
}

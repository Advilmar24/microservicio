package com.micro_servicios.notificaciones.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.micro_servicios.notificaciones.dto.MessageResponseDTO;
import com.micro_servicios.notificaciones.dto.NotificacionesRequestDTo;
import com.micro_servicios.notificaciones.dto.NotificacionesResponseDTO;
import com.micro_servicios.notificaciones.entity.Notificaciones;
import com.micro_servicios.notificaciones.repository.NotificacionesRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificacionesService {

    private final NotificacionesRepository notificacionesRepository;

    public MessageResponseDTO crearNotificacion(NotificacionesRequestDTo request){
        Notificaciones notificaciones = new Notificaciones();

        notificaciones.setUsuarioId(request.getUsuarioId());
        notificaciones.setProductoId(request.getProductoId());
        notificaciones.setMensaje(request.getMensaje());
        notificaciones.setEstado("pendiente");
        notificaciones.setFecha(LocalDateTime.now());

        notificacionesRepository.save(notificaciones);
        
        return new MessageResponseDTO("Notificacion Creada");

    }


    public List<NotificacionesResponseDTO> listarTodas(){
        return notificacionesRepository.findAll()
                .stream()
                .map(this:: toResponse)
                .toList();
    }
    

    public List<NotificacionesResponseDTO> listarPorUsuario(Integer usuarioId){
    
        return notificacionesRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(this:: toResponse)
                .toList();
    }

    public NotificacionesResponseDTO actualizarEstado(Integer id, String nuevoEstado){

        Notificaciones notificaciones = notificacionesRepository.findById(id)
                .orElseThrow(()-> new RuntimeException ("Notificacion no encontrada"));


        notificaciones.setEstado(nuevoEstado);
        Notificaciones actualizada = notificacionesRepository.save(notificaciones);

        return toResponse(actualizada);
    }

    public MessageResponseDTO eliminarNotificacion(Integer id){
        Notificaciones notificaciones = notificacionesRepository.findById(id)
                .orElseThrow(() ->  new  RuntimeException("Notificacion o encontrada"));

            notificacionesRepository.delete(notificaciones);
            return new MessageResponseDTO("Notificacion eliminada");
                
    }


    private NotificacionesResponseDTO toResponse(Notificaciones notificaciones){

        return NotificacionesResponseDTO.builder()
                .id(notificaciones.getId())
                .usuarioId(notificaciones.getUsuarioId())
                .productoId(notificaciones.getProductoId())
                .mensaje(notificaciones.getMensaje())
                .fecha(notificaciones.getFecha())
                .estado(notificaciones.getEstado())
                .build();

    }
}


package com.micro_servicios.notificaciones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.micro_servicios.notificaciones.entity.Notificaciones;
@Repository
public interface NotificacionesRepository extends JpaRepository <Notificaciones, Integer> {

    
    List<Notificaciones> findByUsuarioId(Integer usuarioId);


}

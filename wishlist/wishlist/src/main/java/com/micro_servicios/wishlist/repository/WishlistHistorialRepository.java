package com.micro_servicios.wishlist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micro_servicios.wishlist.entity.WishListHistorial;

public interface WishlistHistorialRepository extends JpaRepository<WishListHistorial, Long> {

    List<WishListHistorial> findByUsuarioId(Integer usuarioId);
    
    List<WishListHistorial> findByUsuarioIdOrderByFechaDesc(
        Integer usuarioId);
}

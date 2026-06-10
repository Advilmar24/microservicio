package com.micro_servicios.wishlist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.micro_servicios.wishlist.entity.Wishlist;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist,Integer> {
    
    List<Wishlist> findByUsuarioId(Integer usuarioId);
}

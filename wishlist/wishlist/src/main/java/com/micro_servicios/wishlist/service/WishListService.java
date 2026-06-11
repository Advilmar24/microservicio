package com.micro_servicios.wishlist.service;

import com.micro_servicios.wishlist.repository.WishlistHistorialRepository;
import com.micro_servicios.wishlist.repository.WishlistRepository;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.micro_servicios.wishlist.dto.MessageResponseDTO;
import com.micro_servicios.wishlist.dto.WishListHistorialResponseDTO;
import com.micro_servicios.wishlist.dto.WishListRequestDTO;
import com.micro_servicios.wishlist.dto.WishListResponseDTO;
import com.micro_servicios.wishlist.entity.WishListHistorial;
import com.micro_servicios.wishlist.entity.Wishlist;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WishListService {

    private final WishlistHistorialRepository wishlistHistorialRepository;
    private final WishlistRepository wishlistRepository;


    public MessageResponseDTO agregarProducto(WishListRequestDTO requestDTO){
        Wishlist wishlist = new Wishlist();

        wishlist.setUsuarioId(requestDTO.getUsuarioId());
        wishlist.setProductoId(requestDTO.getProductoId());
        wishlist.setEstado("activo");
        wishlist.setFechaAgregado(LocalDateTime.now());

        wishlistRepository.save(wishlist);


        WishListHistorial wishListHistorial = new WishListHistorial();

        wishListHistorial.setWishListId(wishlist.getId());
        wishListHistorial.setUsuarioId(wishlist.getUsuarioId());
        wishListHistorial.setProductId(requestDTO.getProductoId());
        wishListHistorial.setAccion("agregado");
        wishListHistorial.setFecha(LocalDateTime.now());
        
        wishlistHistorialRepository.save(wishListHistorial);

        return new MessageResponseDTO("Producto agregado a la wishList");
    }

    public List<WishListResponseDTO> listForUser(Integer usuarioId){
        
        return wishlistRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(this::toResponse)
                .toList();
    }
    

    public MessageResponseDTO deleteProduct(Integer id){

        Wishlist wishlist = wishlistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("producto no encontra en wishlist"));

                wishlist.setEstado("eliminado");
                wishlistRepository.save(wishlist);

                WishListHistorial wishListHistorial = new WishListHistorial();
                wishListHistorial.setWishListId(wishlist.getId());
                wishListHistorial.setUsuarioId(wishlist.getUsuarioId());
                wishListHistorial.setProductId(wishlist.getProductoId());
                wishListHistorial.setAccion("eliminado");
                wishListHistorial.setFecha(LocalDateTime.now());
                wishlistHistorialRepository.save(wishListHistorial);

                return new MessageResponseDTO("Producto eliminado");
    }

    public List<WishListHistorialResponseDTO> obtenerHistorial(Integer usuarioId){

        return wishlistHistorialRepository
                .findByUsuarioIdOrderByFechaDesc(usuarioId)
                .stream()
                .map(this :: toHistorialResponse)
                .toList();
    }

    
    private WishListResponseDTO toResponse(Wishlist wishlist){
        return WishListResponseDTO.builder()
            .id(wishlist.getId())
            .usuarioId(wishlist.getUsuarioId())
            .productoId(wishlist.getProductoId())
            .estado(wishlist.getEstado())
            .fechaAgregado(wishlist.getFechaAgregado())
            .build();
    }


    private WishListHistorialResponseDTO toHistorialResponse(WishListHistorial historial){

        return WishListHistorialResponseDTO.builder()
                .usuarioId(historial.getUsuarioId())
                .productoId(historial.getProductId())
                .accion(historial.getAccion())
                .fecha(historial.getFecha())
                .build();
    }


}

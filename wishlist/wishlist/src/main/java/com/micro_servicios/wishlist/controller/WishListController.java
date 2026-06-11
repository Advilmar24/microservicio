package com.micro_servicios.wishlist.controller;

import com.micro_servicios.wishlist.service.WishListService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro_servicios.wishlist.dto.MessageResponseDTO;
import com.micro_servicios.wishlist.dto.WishListHistorialResponseDTO;
import com.micro_servicios.wishlist.dto.WishListRequestDTO;
import com.micro_servicios.wishlist.dto.WishListResponseDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/wishList")
@RequiredArgsConstructor
public class WishListController {

private final WishListService wishListService;


    @PostMapping("/agregar")
    public MessageResponseDTO agregar(@Valid @RequestBody WishListRequestDTO wishListRequestDTO){
        System.out.println("ENTRO A WISHLIST");
        return wishListService.agregarProducto(wishListRequestDTO);
    }

    @GetMapping("/{usuarioId}")
    public List<WishListResponseDTO> listar(@PathVariable Integer usuarioId){
        return wishListService.listForUser(usuarioId);

    }

    @GetMapping("historial/{usuarioId}")
    public List<WishListHistorialResponseDTO> historial(@PathVariable Integer usuarioId){
        return wishListService.obtenerHistorial(usuarioId);
    }
    @DeleteMapping("/{id}")
    public MessageResponseDTO eliminar(@PathVariable Integer id){
        return wishListService.deleteProduct(id);
    }

    



    
}

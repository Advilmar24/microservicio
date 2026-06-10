package com.micro_servicios.wishlist.controller;

import com.micro_servicios.wishlist.service.WishListService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro_servicios.wishlist.dto.MessageResponseDTO;
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
@RequestMapping("/wisjList")
@RequiredArgsConstructor
public class WishListController {

private final WishListService wishListService;


    @PostMapping
    public MessageResponseDTO agregar(@Valid @RequestBody WishListRequestDTO wishListRequestDTO){
        return wishListService.agregarProducto(wishListRequestDTO);
    }

    @GetMapping("/{usuarioId}")
    public List<WishListResponseDTO> listar(@PathVariable Integer usuarioId){
        return wishListService.listForUser(usuarioId);

    }

    @DeleteMapping("/{id}")
    public MessageResponseDTO eliminar(@PathVariable Integer id){
        return wishListService.deleteProduct(id);
    }

    



    
}

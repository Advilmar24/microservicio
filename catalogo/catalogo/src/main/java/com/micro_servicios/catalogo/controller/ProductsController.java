package com.micro_servicios.catalogo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro_servicios.catalogo.dto.MenssageResponseDTO;
import com.micro_servicios.catalogo.dto.ProductsRequestDTO;
import com.micro_servicios.catalogo.dto.ProductsResponseDTO;
import com.micro_servicios.catalogo.service.ProductsService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductsController {

    private final ProductsService productsService;  

    @PostMapping("/create")
    public ResponseEntity<MenssageResponseDTO> create(
        @RequestBody ProductsRequestDTO productsRequestDTO){

        MenssageResponseDTO response = productsService.crearProducto(productsRequestDTO);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProductsResponseDTO>> getAll(){

        List<ProductsResponseDTO> products = 
                productsService.listarProductos();

        return ResponseEntity.ok(products);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProductsResponseDTO> update(@PathVariable Integer id,@RequestBody ProductsRequestDTO request){

        ProductsResponseDTO products = productsService.actualizarProducto(id, request);

        return ResponseEntity.ok(products);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MenssageResponseDTO> delete(
        @PathVariable Integer id){

        MenssageResponseDTO responseDTO = productsService.eliminarProducto(id);

        return ResponseEntity.ok(responseDTO);
    
        }

    
}

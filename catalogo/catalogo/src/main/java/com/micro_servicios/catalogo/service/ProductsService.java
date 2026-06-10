package com.micro_servicios.catalogo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.micro_servicios.catalogo.config.ProductNotFoundException;
import com.micro_servicios.catalogo.dto.MenssageResponseDTO;
import com.micro_servicios.catalogo.dto.ProductsRequestDTO;
import com.micro_servicios.catalogo.dto.ProductsResponseDTO;
import com.micro_servicios.catalogo.entity.Productos;
import com.micro_servicios.catalogo.repository.ProductsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductsService {
    private final ProductsRepository productsRepository;

    public MenssageResponseDTO crearProducto(ProductsRequestDTO productsRequestDTO){
        MenssageResponseDTO message = new MenssageResponseDTO();
        message.setMessage("Producto Creado");

        if (productsRequestDTO.getStock() < 0){
            throw new RuntimeException ("El stock no puede ser negativo");
        }

        Productos productos = new Productos();

        productos.setNombre(productsRequestDTO.getNombre());
        productos.setDescripcion(productsRequestDTO.getDescripcion());
        productos.setPrecio(productsRequestDTO.getPrecio());
        productos.setStock(productsRequestDTO.getStock());
        productos.setCategoria(productsRequestDTO.getCategoria());

        productsRepository.save(productos);

        return message;
    }

    public List<ProductsResponseDTO> listarProductos(){
        return productsRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ProductsResponseDTO obtenerPorId(Integer id){
        Productos productos = productsRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

                return toResponse(productos);
    }


    public ProductsResponseDTO actualizarProducto(Integer id, ProductsRequestDTO request){
        Productos productos = productsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        productos.setNombre(request.getNombre());
        productos.setDescripcion(request.getDescripcion());
        productos.setPrecio(request.getPrecio());
        productos.setStock(request.getStock());
        productos.setCategoria(request.getCategoria());

        Productos actualizado = productsRepository.save(productos);
    
            return toResponse(actualizado);
    
    }
    
    public MenssageResponseDTO eliminarProducto(Integer id){
        Productos productos = productsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        productsRepository.delete(productos);
        return new MenssageResponseDTO("Producto eliminado");
    }

    private ProductsResponseDTO toResponse(Productos productos){
        return ProductsResponseDTO.builder()
                .id(productos.getId())
                .nombre(productos.getNombre())
                .descripcion(productos.getDescripcion())
                .precio(productos.getPrecio())
                .stock(productos.getStock())
                .categoria(productos.getCategoria())
                .build();

    }
}

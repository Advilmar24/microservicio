package com.micro_servicios.catalogo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micro_servicios.catalogo.entity.Productos;

public interface ProductsRepository extends JpaRepository<Productos, Long> {
    List<Productos> findByCategoria(String categoria);

    Optional<Productos> findById (Integer id);
}

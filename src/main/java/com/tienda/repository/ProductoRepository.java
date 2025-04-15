package com.tienda.repository;

import com.tienda.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto,Long>{
    
}

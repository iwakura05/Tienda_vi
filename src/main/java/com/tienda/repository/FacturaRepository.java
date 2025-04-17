package com.tienda.repository;

import com.tienda.domain.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepository extends JpaRepository <Factura,Long> {
     
}


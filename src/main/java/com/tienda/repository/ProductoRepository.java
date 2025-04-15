package com.tienda.repository;

import com.tienda.domain.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductoRepository extends 
        JpaRepository<Producto, Long> { // SE NECESITA QUE SEA QUI PARA QUE FUNCIONE

    //Consulta ampliada
    public List<Producto> findByPrecioBetweenOrderByPrecio(
            double precioInf, //BUSACR POR PRECIO
            double precioSup);

    //Consulta JPQL
    @Query(value="SELECT a"
            + " FROM Producto a"
            + " WHERE a.precio"
            + " BETWEEN :precioInf AND :precioSup"
            + " ORDER BY a.precio") // A ES UN ALIAS
    public List<Producto> consultaJPQL(
            double precioInf,
            double precioSup);
   
    //Consulta SQL
    @Query(nativeQuery=true,value="SELECT * "
            + "FROM producto a"
            + " WHERE a.precio"
            + " BETWEEN :precioInf AND :precioSup "
            + "ORDER BY a.precio")
    public List<Producto> consultaSQL(
            double precioInf,
            double precioSup);
}
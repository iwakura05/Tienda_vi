package com.tienda.service;

import com.tienda.domain.Producto;
import com.tienda.repository.ProductoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Transactional(readOnly = true)
    public List<Producto> getProductos(boolean activos) {
        var lista = productoRepository.findAll();
        //aca falta un codigo...
        return lista;
    }

    //Se crean los metodos para un CRUD Creat Read Update Delete
    @Transactional(readOnly = true)
    public Producto getProducto(Producto producto) {
        producto = productoRepository.findById(producto.getIdProducto()).orElse(null);

        return producto;
    }

    @Transactional
    public void delete(Producto producto) {
        //Elimina el resgistro que tiene producto, basado en el objeto producto
        productoRepository.delete(producto);
    }

    @Transactional
    public void save(Producto producto) {
        //Si el idProducto tiene un valor, se actualiza el registro de ese idProducto
        //Si el idProducto no tiene un valor, se inserta un registro con la informacion de esa producto
        productoRepository.save(producto);
    }

}

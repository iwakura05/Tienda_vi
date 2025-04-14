package com.tienda.service;

import com.tienda.domain.Categoria;
import com.tienda.repository.CategoriaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Transactional(readOnly = true)
    public List<Categoria> getCategorias(boolean activos) {
        var lista = categoriaRepository.findAll();
        //aca falta un codigo...
        return lista;
    }

    //Se crean los metodos para un CRUD Creat Read Update Delete
    @Transactional(readOnly = true)
    public Categoria getCategoria(Categoria categoria) {
        categoria = categoriaRepository.findById(categoria.getIdCategoria()).orElse(null);

        return categoria;
    }

    @Transactional
    public void delete(Categoria categoria) {
        //Elimina el resgistro que tiene categoria, basado en el objeto categoria
        categoriaRepository.delete(categoria);
    }

    @Transactional
    public void save(Categoria categoria) {
        //Si el idCategoria tiene un valor, se actualiza el registro de ese idCategoria
        //Si el idCategoria no tiene un valor, se inserta un registro con la informacion de esa categoria
        categoriaRepository.save(categoria);
    }

}

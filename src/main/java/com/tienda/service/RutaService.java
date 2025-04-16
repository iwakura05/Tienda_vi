package com.tienda.service;

import com.tienda.domain.Ruta;
import com.tienda.repository.RutaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RutaService {

    @Autowired
    private RutaRepository rutaRepository;
    
    @Transactional(readOnly=true)
    public List<Ruta> getRutas() {
        var lista = rutaRepository.findAll();               
        return lista;
    }

    @Transactional(readOnly=true)
    public Ruta getRuta(Ruta ruta) {
        return rutaRepository.findById(ruta.getIdRuta()).orElse(null);
    }

    @Transactional
    public void save(Ruta ruta) {
        rutaRepository.save(ruta);
    }

    @Transactional
    public void delete(Ruta ruta) {
        rutaRepository.delete(ruta);
    }
}
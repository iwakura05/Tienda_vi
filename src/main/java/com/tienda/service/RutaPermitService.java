package com.tienda.service;

import com.tienda.domain.RutaPermit;
import com.tienda.repository.RutaPermitRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RutaPermitService {

    @Autowired
    private RutaPermitRepository rutaPermitRepository;
    
    @Transactional(readOnly=true)
    public List<RutaPermit> getRutaPermits() {
        var lista = rutaPermitRepository.findAll();
        return lista;
    }

    @Transactional(readOnly=true)
    public String[] getRutaPermitsString(){
        var lista = rutaPermitRepository.findAll();
        String[] rutasPermit = new String[lista.size()];
        int i = 0;
        for (RutaPermit rp : lista) {
            rutasPermit[i] = rp.getPatron();
            i++;
        }
        return rutasPermit;
    }
    
    @Transactional(readOnly=true)
    public RutaPermit getRutaPermit(RutaPermit rutaPermit) {
        return rutaPermitRepository.findById(rutaPermit.getIdRuta()).orElse(null);
    }

    @Transactional
    public void save(RutaPermit rutaPermit) {
        rutaPermitRepository.save(rutaPermit);
    }

    @Transactional
    public void delete(RutaPermit rutaPermit) {
        rutaPermitRepository.delete(rutaPermit);
    }
}
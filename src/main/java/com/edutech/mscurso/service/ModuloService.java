package com.edutech.mscurso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.edutech.mscurso.model.Modulo;
import com.edutech.mscurso.repository.ModuloRepository;

@Service
public class ModuloService {

    @Autowired
    private ModuloRepository moduloRepository;

    public Modulo save(Modulo modulo) {
        return moduloRepository.save(modulo);
    }

    public Modulo findById(int idModulo) {
        return moduloRepository.findById(idModulo);
    }

    public List<Modulo> findAll() {
        return moduloRepository.findAll();
    }

    public void deleteById(int idCurso) {
        moduloRepository.deleteById(idCurso);
    }

    public Modulo moduloxId(int idModulo) {
        return moduloRepository.getReferenceById(idModulo);
    }

    public Boolean update(int idModulo, Modulo modulo) {
        Modulo mod = moduloRepository.findById(idModulo);
        if(mod != null) {
            mod.setIdModulo(idModulo);
            // mod.setIdCurso(modulo.getIdCurso());
            mod.setTitulo(modulo.getTitulo());
            mod.setDescripcion(modulo.getDescripcion());

            moduloRepository.save(mod);
            return true;
        } else {
            return false;
        }
    }

    public Boolean activar(int idModulo) {
        Modulo buscarModulo = moduloRepository.findById(idModulo);
        if(buscarModulo != null) {
            buscarModulo.setActivo(!buscarModulo.getActivo());
            moduloRepository.save(buscarModulo);
            return true;
        }

        return false;
    }
}

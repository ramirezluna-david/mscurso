package com.edutech.mscurso.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.edutech.mscurso.model.Clase;
import com.edutech.mscurso.repository.ClaseRepository;

@Service
public class ClaseService {

    @Autowired
    private ClaseRepository claseRepository;

    public Clase save(Clase clase) {
        return claseRepository.save(clase);
    }

    public Optional<Clase> findById(Long idClase) {
        return claseRepository.findById(idClase);
    }

    public List<Clase> findAll() {
        return claseRepository.findAll();
    }

    public void deleteById(Long idClase) {
        claseRepository.deleteById(idClase);
    }

    /* public Clase clasexId(int idClase) {
        return claseRepository.getReferenceById(idClase);
    } */

    public Clase cambiarVisibilidad(Long idClase) {
        Clase buscarClase = claseRepository.findById(idClase)
            .orElseThrow(() -> new RuntimeException("No existe la clase"));
        // if(buscarClase != null) {
        buscarClase.setPublicado((!buscarClase.getPublicado()));
        return claseRepository.save(buscarClase);
        //     return true;
        // }

        // return false;
    }

    public Clase update(Long idClase, Clase clase) {
        Clase existente = claseRepository.findById(idClase)
            .orElseThrow(() -> new RuntimeException("No existe la clase"));
        // if(existente != null) {
            // existente.setIdClase(idClase);
            // existente.setIdCurso(clase.getIdCurso());
        existente.setTitulo(clase.getTitulo());
        existente.setDescripcion(clase.getDescripcion());
        existente.setCategoria(clase.getCategoria());
        existente.setFechaCreacion(clase.getFechaCreacion());
        existente.setPublicado(clase.getPublicado());

        return claseRepository.save(existente);
        //     return true;
        // } else {
        //     return false;
        // }
    }

    public Clase activar(Long idClase) {
        Clase buscarClase = claseRepository.findById(idClase)
            .orElseThrow(() -> new RuntimeException("No existe la clase"));
        // if(buscarClase != null) {
        buscarClase.setActivo(!buscarClase.getActivo());
        return claseRepository.save(buscarClase);
            // return true;
        // }

        // return false;
    }
}

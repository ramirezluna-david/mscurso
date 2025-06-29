package com.edutech.mscurso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

    public Clase findById(int idClase) {
        return claseRepository.findById(idClase);
    }

    public List<Clase> findAll() {
        return claseRepository.findAll();
    }

    public void deleteById(int idClase) {
        claseRepository.deleteById(idClase);
    }

    public Clase clasexId(int idClase) {
        return claseRepository.getReferenceById(idClase);
    }

    public Clase cambiarVisibilidad(int idClase) {
        Clase buscarClase = claseRepository.findById(idClase);
        buscarClase.setPublicado((!buscarClase.getPublicado()));
        return claseRepository.save(buscarClase);
    }

    public Clase update(int idClase, Clase clase) {
        Clase existente = claseRepository.findById(idClase);
        existente.setTitulo(clase.getTitulo());
        existente.setDescripcion(clase.getDescripcion());
        existente.setCategoria(clase.getCategoria());
        existente.setFechaCreacion(clase.getFechaCreacion());
        existente.setPublicado(clase.getPublicado());
        return claseRepository.save(existente);
    }

    public Clase activar(int idClase) {
        Clase buscarClase = claseRepository.findById(idClase);
        buscarClase.setActivo(!buscarClase.getActivo());
        return claseRepository.save(buscarClase);
    }
}

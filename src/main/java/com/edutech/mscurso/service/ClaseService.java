package com.edutech.mscurso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    /* public Clase clasexId(int idClase) {
        return claseRepository.getReferenceById(idClase);
    } */

    public Boolean cambiarVisibilidad(int idClase) {
        Clase buscarClase = claseRepository.findById(idClase);
        if(buscarClase != null) {
            buscarClase.setPublicado((!buscarClase.getPublicado()));
            claseRepository.save(buscarClase);
            return true;
        }

        return false;
    }

    public Boolean update(int idClase, Clase clase) {
        Clase cla = claseRepository.findById(idClase);
        if(cla != null) {
            cla.setIdClase(idClase);
            // cla.setIdCurso(clase.getIdCurso());
            cla.setTitulo(clase.getTitulo());
            cla.setDescripcion(clase.getDescripcion());
            cla.setCategoria(clase.getCategoria());
            cla.setFechaCreacion(clase.getFechaCreacion());
            cla.setPublicado(clase.getPublicado());

            claseRepository.save(cla);
            return true;
        } else {
            return false;
        }
    }

    public Boolean activar(int idClase) {
        Clase buscarClase = claseRepository.findById(idClase);
        if(buscarClase != null) {
            buscarClase.setActivo(!buscarClase.getPublicado());
            claseRepository.save(buscarClase);
            return true;
        }

        return false;
    }
}

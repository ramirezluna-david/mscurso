package com.edutech.mscurso.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
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

    public Optional<Modulo> findById(Long idModulo) {
        return moduloRepository.findById(idModulo);
    }

    public List<Modulo> findAll() {
        return moduloRepository.findAll();
    }

    public void deleteById(Long idCurso) {
        moduloRepository.deleteById(idCurso);
    }

    public Modulo moduloxId(Long idModulo) {
        return moduloRepository.getReferenceById(idModulo);
    }

    public Boolean update(Long idModulo, Modulo modulo) {
        Modulo existente = moduloRepository.findById(idModulo)
            .orElseThrow(() -> new RuntimeException("No existe módulo"));
        if(existente != null) {
            // existente.setIdModulo(idModulo);
            // existente.setIdCurso(modulo.getIdCurso());
            existente.setTitulo(modulo.getTitulo());
            existente.setDescripcion(modulo.getDescripcion());

            moduloRepository.save(existente);
            return true;
        } else {
            return false;
        }
    }

    public Boolean activar(Long idModulo) {
        Modulo buscarModulo = moduloRepository.findById(idModulo)
            .orElseThrow(() -> new RuntimeException("No existe el módulo"));
        if(buscarModulo != null) {
            buscarModulo.setActivo(!buscarModulo.getActivo());
            moduloRepository.save(buscarModulo);
            return true;
        }

        return false;
    }
}

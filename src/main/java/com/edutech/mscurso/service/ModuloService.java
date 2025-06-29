package com.edutech.mscurso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

    public Modulo update(int idModulo, Modulo modulo) {
        Modulo existente = moduloRepository.findById(idModulo);
        existente.setTitulo(modulo.getTitulo());
        existente.setDescripcion(modulo.getDescripcion());

        return moduloRepository.save(existente);
    }

    public Modulo activar(int idModulo) {
        Modulo buscarModulo = moduloRepository.findById(idModulo);
        buscarModulo.setActivo(!buscarModulo.getActivo());
        return moduloRepository.save(buscarModulo);
    }
}

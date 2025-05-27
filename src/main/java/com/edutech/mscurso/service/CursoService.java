package com.edutech.mscurso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.mscurso.model.Curso;
import com.edutech.mscurso.repository.CursoRepository;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public Curso save(Curso curso) {
        return cursoRepository.save(curso);
    }

    public Curso findById(int idCurso) {
        return cursoRepository.findById(idCurso);
    }

    public List<Curso> findAll() {
        return cursoRepository.findAll();
    }

    public void deleteById(int idCurso) {
        cursoRepository.deleteById(idCurso);
    }

    public Boolean cambiarVisibilidad(int idCurso) {
        Curso buscarCurso = cursoRepository.findById(idCurso);
        if(buscarCurso != null) {
            buscarCurso.setPublicado((!buscarCurso.getPublicado()));
            cursoRepository.save(buscarCurso);
            return true;
        }

        return false;
    }
}

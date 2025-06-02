package com.edutech.mscurso.service;

import java.util.List;
import java.util.Map;

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

    public Curso cursoxId(int idCurso) {
        return cursoRepository.getReferenceById(idCurso);
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

    public Boolean update(int idCurso, Curso curso) {
        Curso cur = cursoRepository.findById(idCurso);
        if(cur != null) {
            cur.setIdCurso(idCurso);
            cur.setTitulo(curso.getTitulo());
            cur.setDescripcion(curso.getDescripcion());
            cur.setCategoria(curso.getCategoria());
            cur.setPrecio(curso.getPrecio());
            cur.setIdProfesor(curso.getIdProfesor());
            cur.setFechaCreacion(curso.getFechaCreacion());
            cur.setPublicado(curso.getPublicado());

            cursoRepository.save(cur);
            return true;
        } else {
            return false;
        }
    }

    public Boolean asignarTutor(int idCurso, int idProfesor) {
        Curso buscarCurso = cursoRepository.findById(idCurso);
        if(buscarCurso != null) {
            buscarCurso.setIdProfesor(idProfesor);
            cursoRepository.save(buscarCurso);
            return true;
        }

        return false;
    }
}

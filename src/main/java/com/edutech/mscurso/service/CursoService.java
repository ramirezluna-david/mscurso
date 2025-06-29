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

    public Curso cursoxId(int idCurso) {
        return cursoRepository.getReferenceById(idCurso);
    }

    public Curso cambiarVisibilidad(int idCurso) {
        Curso buscarCurso = cursoRepository.findById(idCurso);
        buscarCurso.setPublicado((!buscarCurso.getPublicado()));
        return cursoRepository.save(buscarCurso);
    }

    public Curso update(int idCurso, Curso curso) {
        Curso existente = cursoRepository.findById(idCurso);
        existente.setTitulo(curso.getTitulo());
        existente.setDescripcion(curso.getDescripcion());
        existente.setCategoria(curso.getCategoria());
        existente.setPrecio(curso.getPrecio());
        existente.setIdProfesor(curso.getIdProfesor());
        existente.setFechaCreacion(curso.getFechaCreacion());
        existente.setPublicado(curso.getPublicado());
        return cursoRepository.save(existente);
    }

    public Curso asignarTutor(int idCurso, int idProfesor) {
        Curso buscarCurso = cursoRepository.findById(idCurso);
        buscarCurso.setIdProfesor(idProfesor);
        return cursoRepository.save(buscarCurso);
    }

    public Curso activar(int idCurso) {
        Curso buscarCurso = cursoRepository.findById(idCurso);
        buscarCurso.setActivo(!buscarCurso.getActivo());
        return cursoRepository.save(buscarCurso);
    }
}

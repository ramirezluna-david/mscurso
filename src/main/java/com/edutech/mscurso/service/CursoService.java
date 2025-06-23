package com.edutech.mscurso.service;

import java.util.List;
// import java.util.Map;
import java.util.Optional;

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

    public Optional<Curso> findById(Long idCurso) {
        return cursoRepository.findById(idCurso);
    }

    public List<Curso> findAll() {
        return cursoRepository.findAll();
    }

    public void deleteById(Long idCurso) {
        cursoRepository.deleteById(idCurso);
    }

    public Curso cursoxId(Long idCurso) {
        return cursoRepository.getReferenceById(idCurso);
    }

    public Curso cambiarVisibilidad(Long idCurso) {
        Curso buscarCurso = cursoRepository.findById(idCurso)
            .orElseThrow(() -> new RuntimeException("No existe el curso"));
        // if(buscarCurso != null) {
        buscarCurso.setPublicado((!buscarCurso.getPublicado()));
        return cursoRepository.save(buscarCurso);
            // return true;
        // }

        // return false;
    }

    public Curso update(Long idCurso, Curso curso) {
        Curso existente = cursoRepository.findById(idCurso)
            .orElseThrow(() -> new RuntimeException("No existe el curso"));
        // if(existente != null) {
        // existente.setIdCurso(idCurso);
        existente.setTitulo(curso.getTitulo());
        existente.setDescripcion(curso.getDescripcion());
        existente.setCategoria(curso.getCategoria());
        existente.setPrecio(curso.getPrecio());
        existente.setIdProfesor(curso.getIdProfesor());
        existente.setFechaCreacion(curso.getFechaCreacion());
        existente.setPublicado(curso.getPublicado());

        return cursoRepository.save(existente);
        //     return true;
        // } else {
        //     return false;
        // }
    }

    public Curso asignarTutor(Long idCurso, Long idProfesor) {
        Curso buscarCurso = cursoRepository.findById(idCurso)
            .orElseThrow(() -> new RuntimeException("No existe el curso"));
        // if(buscarCurso != null) {
        buscarCurso.setIdProfesor(idProfesor);
        return cursoRepository.save(buscarCurso);
            // return true;
        // }

        // return false;
    }

    public Curso activar(Long idCurso) {
        Curso buscarCurso = cursoRepository.findById(idCurso)
            .orElseThrow(() -> new RuntimeException("No existe el curso"));
        // if(buscarCurso != null) {
        buscarCurso.setActivo(!buscarCurso.getActivo());
        return cursoRepository.save(buscarCurso);
            // return true;
        // }

        // return false;
    }
}

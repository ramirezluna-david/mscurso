package com.edutech.mscurso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edutech.mscurso.model.Curso;
import com.edutech.mscurso.service.CursoService;

@RestController
@RequestMapping("/api/v1/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<Curso>> listarCursos() {
        List<Curso> cursos = cursoService.findAll();
        if(cursos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(cursos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Curso> createCurso(@RequestBody Curso curso) {
        Curso buscar = cursoService.findById(curso.getIdCurso());
        if(buscar == null) {
            Curso nuevoCurso = cursoService.save(curso);
            return new ResponseEntity<>(nuevoCurso, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @GetMapping("/{idCurso}")
    public ResponseEntity<Curso> readCurso(@PathVariable int idCurso) {
        Curso buscarCurso = cursoService.findById(idCurso);
        if(buscarCurso != null) {
            return new ResponseEntity<>(buscarCurso, HttpStatus.OK);
        } else {
            // return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{idCurso}")
    public ResponseEntity<Curso> updateCurso(@PathVariable int idCurso, @RequestBody Curso curso) {
        Curso cur = cursoService.findById(idCurso);
        if(cur != null) {
            cur.setIdCurso(idCurso);
            cur.setTitulo(curso.getTitulo());
            cur.setDescripcion(curso.getDescripcion());
            cur.setCategoria(curso.getCategoria());
            cur.setPrecio(curso.getPrecio());
            cur.setIdProfesor(curso.getIdProfesor());
            cur.setFechaCreacion(curso.getFechaCreacion());
            cur.setPublicado(curso.getPublicado());

            cursoService.save(cur);
            return new ResponseEntity<>(curso, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{idCurso}")
    public ResponseEntity<?> deleteCurso(@PathVariable int idCurso) {
        Curso buscarCurso = cursoService.findById(idCurso);
        if(buscarCurso != null) {
            cursoService.deleteById(idCurso);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

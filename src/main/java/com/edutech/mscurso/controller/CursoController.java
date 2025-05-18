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
    public ResponseEntity<List<Curso>> listar() {
        List<Curso> cursos = cursoService.findAll();
        if(cursos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(cursos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Curso> guardar(@RequestBody Curso curso) {
        Curso nuevoCurso = cursoService.save(curso);
        return new ResponseEntity<>(nuevoCurso, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{idCurso}")
    public ResponseEntity<Curso> buscar(@PathVariable int idCurso) {
        try {
            Curso curso = cursoService.findById(idCurso);
            return new ResponseEntity<>(curso, HttpStatus.OK);
        } catch(Exception e) {
            // return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{idCurso}")
    public ResponseEntity<Curso> actualizar(@PathVariable int idCurso, @RequestBody Curso curso) {
        try {
            Curso cur = cursoService.findById(idCurso);
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
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{idCurso}")
    public ResponseEntity<?> eliminar(@PathVariable int idCurso) {
        try {
            cursoService.deleteById(idCurso);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

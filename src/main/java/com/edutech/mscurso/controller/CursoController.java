package com.edutech.mscurso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
}

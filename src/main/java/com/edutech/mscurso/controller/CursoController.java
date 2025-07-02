package com.edutech.mscurso.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edutech.mscurso.model.Curso;
import com.edutech.mscurso.service.CursoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/cursos")
@Tag(name = "Curso", description = "Operaciones CRUD para los Cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    @Operation(summary = "Listar Cursos", description = "Obtiene una lista de todos los cursos disponibles")
    public ResponseEntity<List<Curso>> listarCursos() {
        List<Curso> cursos =  cursoService.findAll();
        if(cursos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(cursos, HttpStatus.OK);
        }

    }

    @PostMapping
    @Operation(summary = "Crear Curso", description = "Crea un nuevo curso")
    public ResponseEntity<Curso> createCurso(@RequestBody Curso curso) {
        Curso buscarCurso = cursoService.findById(curso.getIdCurso());
        if(buscarCurso == null) {
            Curso nuevoCurso = cursoService.save(curso);
            return new ResponseEntity<>(nuevoCurso, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{idCurso}")
    @Operation(summary = "Leer Curso", description = "Obtiene los detalles de un curso por su ID")
    public ResponseEntity<Curso> readCurso(@PathVariable int idCurso) {
        Curso buscarCurso = cursoService.findById(idCurso);
        if(buscarCurso != null) {
            return new ResponseEntity<>(buscarCurso, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{idCurso}")
    @Operation(summary = "Actualizar Curso", description = "Actualiza los detalles de un curso existente")
    public ResponseEntity<Curso> updateCurso(@PathVariable int idCurso, @RequestBody Curso curso) {
        Curso actualizaCurso = cursoService.update(idCurso, curso);
        if(actualizaCurso != null) {
            return new ResponseEntity<>(actualizaCurso, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // REGISTROS YA NO SE ELIMINAN, SE CAMBIA ESTADO ACTIVO O INACTIVO
    // @DeleteMapping("/{idCurso}")
    // public ResponseEntity<?> deleteCurso(@PathVariable int idCurso) {
    //     Curso buscarCurso = cursoService.findById(idCurso);
    //     if(buscarCurso != null) {
    //         cursoService.deleteById(idCurso);
    //         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    //     } else {
    //         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //     }
    // }

    @PatchMapping("/{idCurso}/modificar/visibilidad")
    @Operation(summary = "Cambiar Visibilidad del Curso", description = "Cambia la visibilidad de un curso (público/privado)")
    public ResponseEntity<Curso> cambiarVisibilidad(@PathVariable int idCurso) {
        Curso habilitarCurso = cursoService.cambiarVisibilidad(idCurso);
        if(habilitarCurso != null) {
            return new ResponseEntity<>(habilitarCurso, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{idCurso}/modificar/tutor")
    @Operation(summary = "Asignar Tutor al Curso", description = "Asigna un tutor a un curso específico")
    public ResponseEntity<Curso> asignarTutor(@PathVariable int idCurso, @RequestBody Map<String, Integer> body) {
        int idProfesor = body.get("idProfesor");
        Curso curso = cursoService.asignarTutor(idCurso, idProfesor);
        if(curso != null) {
            return new ResponseEntity<>(curso, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("{idCurso}/activar")
    @Operation(summary = "Activar Curso", description = "Activa o Desactiva un curso para que esté disponible en la plataforma")
    public ResponseEntity<Curso> cambiarEstadoActivo(@PathVariable int idCurso) {
        Curso activarCurso = cursoService.activar(idCurso);
        if(activarCurso != null) {
            return new ResponseEntity<>(activarCurso, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

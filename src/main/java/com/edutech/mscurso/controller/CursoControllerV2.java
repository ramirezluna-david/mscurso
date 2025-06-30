package com.edutech.mscurso.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edutech.mscurso.assemblers.CursoModelAssembler;
import com.edutech.mscurso.model.Curso;
import com.edutech.mscurso.service.CursoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/cursos")
@Tag(name = "Curso", description = "Operaciones CRUD para los Cursos")
public class CursoControllerV2 {

    @Autowired
    private CursoService cursoService;

    @Autowired
    private CursoModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)

    public CollectionModel<EntityModel<Curso>> listarCursos() {
        List<EntityModel<Curso>> cursos = cursoService.findAll().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());
        return CollectionModel.of(cursos,
            linkTo(methodOn(CursoControllerV2.class).listarCursos()).withSelfRel());

    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Crear Curso", description = "Crea un nuevo curso")
    public ResponseEntity<EntityModel<Curso>> createCurso(@RequestBody Curso curso) {
        Curso nuevoCurso = cursoService.save(curso);
        return ResponseEntity
            .created(linkTo(methodOn(CursoControllerV2.class).readCurso(nuevoCurso.getIdCurso())).toUri())
            .body(assembler.toModel(nuevoCurso));
    }

    @GetMapping(value = "/{idCurso}", produces = MediaTypes.HAL_JSON_VALUE)

    public EntityModel<Curso> readCurso(@PathVariable int idCurso) {
        Curso curso = cursoService.findById(idCurso);
        return assembler.toModel(curso);
    }

    @PutMapping(value = "/{idCurso}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Actualizar Curso", description = "Actualiza los detalles de un curso existente")
    public ResponseEntity<EntityModel<Curso>> updateCurso(@PathVariable int idCurso, @RequestBody Curso curso) {
        Curso cursoActualizado = cursoService.update(idCurso, curso);
        return ResponseEntity
            .ok(assembler.toModel(cursoActualizado));
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

    @PatchMapping(value = "/{idCurso}/modificar/visibilidad", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Cambiar Visibilidad del Curso", description = "Cambia la visibilidad de un curso (público/privado)")
    public ResponseEntity<EntityModel<Curso>> cambiarVisibilidad(@PathVariable int idCurso) {
        Curso cursoActualizado = cursoService.cambiarVisibilidad(idCurso);
        return ResponseEntity
            .ok(assembler.toModel(cursoActualizado));

    }

    @PatchMapping(value = "/{idCurso}/modificar/tutor", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Asignar Tutor al Curso", description = "Asigna un tutor a un curso específico")
    public ResponseEntity<EntityModel<Curso>> asignarTutor(@PathVariable int idCurso, @RequestBody Map<String, Integer> body) {
        int idProfesor = body.get("idProfesor");
        Curso cursoActualizado = cursoService.asignarTutor(idCurso, idProfesor);
        return ResponseEntity
            .ok(assembler.toModel(cursoActualizado));
    }

    @PatchMapping(value = "{idCurso}/activar", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Activar Curso", description = "Activa o Desactiva un curso para que esté disponible en la plataforma")
    public ResponseEntity<EntityModel<Curso>> cambiarEstadoActivo(@PathVariable int idCurso) {
        Curso cursoActualizado = cursoService.activar(idCurso);
        return ResponseEntity
            .ok(assembler.toModel(cursoActualizado));
    }
}

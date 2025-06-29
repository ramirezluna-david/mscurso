package com.edutech.mscurso.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edutech.mscurso.model.Clase;
import com.edutech.mscurso.model.Curso;
import com.edutech.mscurso.service.CursoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/cursos")
@Tag(name = "Curso", description = "Operaciones CRUD para los Cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    @Operation(summary = "Listar Cursos", description = "Obtiene una lista de todos los cursos disponibles")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación exitosa",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = Clase.class))),
        @ApiResponse(responseCode = "404", description = "No hay contenido")
    })
    public List<Curso> listarCursos() {
        return cursoService.findAll();
    }

    @PostMapping
    @Operation(summary = "Crear Curso", description = "Crea un nuevo curso")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Curso creado con éxito",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Clase.class))),
    })
    public Curso createCurso(@RequestBody Curso curso) {
        return cursoService.save(curso);
    }

    @GetMapping("/{idCurso}")
    @Operation(summary = "Leer Curso", description = "Obtiene los detalles de un curso por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación exitosa",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Clase.class))),
        @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    })
    public Curso readCurso(@PathVariable int idCurso) {
        return cursoService.findById(idCurso);
    }

    @PutMapping("/{idCurso}")
    @Operation(summary = "Actualizar Curso", description = "Actualiza los detalles de un curso existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Curso actualizado exitosamente",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = Clase.class))),
        @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    })
    public Curso updateCurso(@PathVariable int idCurso, @RequestBody Curso curso) {
        return cursoService.update(idCurso, curso);
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
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación exitosa",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = Clase.class))),
        @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    })
    public Curso cambiarVisibilidad(@PathVariable int idCurso) {
        return cursoService.cambiarVisibilidad(idCurso);
    }

    @PatchMapping("/{idCurso}/modificar/tutor")
    @Operation(summary = "Asignar Tutor al Curso", description = "Asigna un tutor a un curso específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación exitosa",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = Clase.class))),
        @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    })
    public Curso asignarTutor(@PathVariable int idCurso, @RequestBody Map<String, Integer> body) {
        int idProfesor = body.get("idProfesor");
        return cursoService.asignarTutor(idCurso, idProfesor);
    }

    @PatchMapping("{idCurso}/activar")
    @Operation(summary = "Activar Curso", description = "Activa o Desactiva un curso para que esté disponible en la plataforma")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación exitosa",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = Clase.class))),
        @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    })
    public Curso cambiarEstadoActivo(@PathVariable int idCurso) {
        return cursoService.activar(idCurso);
    }
}

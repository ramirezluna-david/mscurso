package com.edutech.mscurso.controller;

import java.util.List;

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
import com.edutech.mscurso.model.Modulo;
import com.edutech.mscurso.service.CursoService;
import com.edutech.mscurso.service.ModuloService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/modulos")
@Tag(name = "Modulo", description = "Operaciones CRUD para los Módulos")
public class ModuloController {

    @Autowired
    private ModuloService moduloService;

    @Autowired
    private CursoService cursoService;

    @GetMapping
    @Operation(summary = "Listar Módulos", description = "Obtiene una lista de todos los módulos disponibles")
        @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación exitosa",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = Clase.class))),
        @ApiResponse(responseCode = "404", description = "No hay contenido")
    })
    public List<Modulo> listarModulos() {
        return moduloService.findAll();
    }

    @PostMapping
    @Operation(summary = "Crear Módulo", description = "Crea un nuevo módulo asociado a un curso")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Módulo creado con éxito",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Clase.class))),
    })
    public Modulo createModulo(@RequestBody Modulo modulo) {
        int idLink = modulo.getCurso().getIdCurso();
        Curso curso = cursoService.cursoxId(idLink);
        if(curso != null) {
            modulo.setCurso(curso);
        }
        return moduloService.save(modulo);
    }

    @GetMapping("/{idModulo}")
    @Operation(summary = "Leer Módulo", description = "Obtiene los detalles de un módulo por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación exitosa",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Clase.class))),
        @ApiResponse(responseCode = "404", description = "Módulo no encontrado")
    })
    public Modulo readModulo(@PathVariable int idModulo) {
        return moduloService.findById(idModulo);
    }

    @PutMapping("/{idModulo}")
    @Operation(summary = "Actualizar Módulo", description = "Actualiza los detalles de un módulo existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Curso actualizado exitosamente",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = Clase.class))),
        @ApiResponse(responseCode = "404", description = "Módulo no encontrado")
    })
    public Modulo updateModulo(@PathVariable int idModulo, @RequestBody Modulo modulo) {
        return moduloService.update(idModulo, modulo);
    }

    // REGISTROS YA NO SE ELIMINAN, SE CAMBIA ESTADO ACTIVO O INACTIVO
    // @DeleteMapping("/{idModulo}")
    // public ResponseEntity<?> deleteModulo(@PathVariable int idModulo) {
    //     Modulo buscarModulo = moduloService.findById(idModulo);
    //     if(buscarModulo != null) {
    //         moduloService.deleteById(idModulo);
    //         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    //     } else {
    //         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //     }
    // }

    @PatchMapping("/{idModulo}/activar")
    @Operation(summary = "Activar Módulo", description = "Cambia el estado de un módulo a activo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación exitosa",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = Clase.class))),
        @ApiResponse(responseCode = "404", description = "Módulo no encontrado")
    })
    public Modulo cambiarEstadoActivo(@PathVariable int idModulo) {
        return moduloService.activar(idModulo);
    }
}

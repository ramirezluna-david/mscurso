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

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import com.edutech.mscurso.model.Clase;
import com.edutech.mscurso.model.Modulo;
import com.edutech.mscurso.service.ClaseService;
import com.edutech.mscurso.service.ModuloService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/clases")
@Tag(name = "Clase", description = "Operaciones CRUD para las Clases")
public class ClaseController {

    @Autowired
    private ModuloService moduloService;

    @Autowired
    private ClaseService claseService;

    @GetMapping
    @Operation(summary = "Listar Clases", description = "Obtiene una lista de todas las clases disponibles")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación exitosa",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Clase.class))),
        @ApiResponse(responseCode = "404", description = "No hay contenido")
    })
    public List<Clase> listarClases() {
        return claseService.findAll();
    }

    @PostMapping
    @Operation(summary = "Crear Clase", description = "Crea una nueva clase asociada a un módulo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Clase creada con éxito",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Clase.class))),
    })
    public Clase createClase(@RequestBody Clase clase) {
        int idLink = clase.getModulo().getIdModulo();
        Modulo modulo = moduloService.moduloxId(idLink);
        if(modulo != null) {
            clase.setModulo(modulo);
        }
        return claseService.save(clase);
    }

    @GetMapping("/{idClase}")
    @Operation(summary = "Leer Clase", description = "Obtiene los detalles de una clase específica por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación exitosa",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Clase.class))),
        @ApiResponse(responseCode = "404", description = "Clase no encontrada")
    })
    public Clase readClase(@PathVariable int idClase) {
        return claseService.findById(idClase);
    }

    @PutMapping("/{idClase}")
    @Operation(summary = "Actualizar Clase", description = "Actualiza los detalles de una clase existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Clase actualizada exitosamente",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = Clase.class))),
        @ApiResponse(responseCode = "404", description = "Clase no encontrada")
    })
    public Clase updateClase(@PathVariable int idClase, @RequestBody Clase clase) {
        return claseService.update(idClase, clase);
    }

    // REGISTROS YA NO SE ELIMINAN, SE CAMBIA ESTADO ACTIVO O INACTIVO
    // @DeleteMapping("/{idClase}")
    // public ResponseEntity<?> deleteClase(@PathVariable int idClase) {
    //     Clase buscarClase = claseService.findById(idClase);
    //     if(buscarClase != null) {
    //         claseService.deleteById(idClase);
    //         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    //     } else {
    //         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //     }
    // }

    @PatchMapping("/{idClase}/modificar/visibilidad")
    @Operation(summary = "Cambiar Visibilidad de Clase", description = "Cambia la visibilidad de una clase entre pública y privada")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación exitosa",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = Clase.class))),
        @ApiResponse(responseCode = "404", description = "Clase no encontrada")
    })
    public Clase cambiarVisibilidad(@PathVariable int idClase) {
        return claseService.cambiarVisibilidad(idClase);
    }

    @PatchMapping("/{idClase}/activar")
    @Operation(summary = "Activar Clase", description = "Activa o Desactiva una clase para que esté disponible en la plataforma")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación exitosa",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = Clase.class))),
        @ApiResponse(responseCode = "404", description = "Clase no encontrada")
    })
    public Clase cambiarEstadoActivo(@PathVariable int idClase) {
        return claseService.activar(idClase);
    }
}

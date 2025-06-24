package com.edutech.mscurso.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.HttpStatusCode;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
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
        // if(clases.isEmpty()) {
        //     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        // }
        // return new ResponseEntity<>(clases, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Crear Clase", description = "Crea una nueva clase asociada a un módulo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Clase creada exitosa",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Clase.class))),
    })
    public Clase createClase(@RequestBody Clase clase) {
        Long idLink = clase.getModulo().getIdModulo();
        Modulo modulo = moduloService.moduloxId(idLink);
        if(modulo != null) {
            clase.setModulo(modulo);
        }
        // Optional<Clase> buscarClase = claseService.findById(clase.getIdClase());
        // if(buscarClase == null) {
        return claseService.save(clase);
        // } else {
        //     return null;
        // }
    }

    @GetMapping("/{idClase}")
    @Operation(summary = "Leer Clase", description = "Obtiene los detalles de una clase específica por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación exitosa",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Clase.class))),
        @ApiResponse(responseCode = "404", description = "Clase no encontrada")
    })
    public Optional<Clase> readClase(@PathVariable Long idClase) {
        // Optional<Clase> clase = claseService.findById(idClase);
        // if(clase != null) {
        //     return new ResponseEntity<>(clase, HttpStatus.OK);
        // } else {
        //     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        // }
        return claseService.findById(idClase);
            // .map(ResponseEntity::ok)
            // .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{idClase}")
    @Operation(summary = "Actualizar Clase", description = "Actualiza los detalles de una clase existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Clase actualizada exitosamente",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = Clase.class))),
        @ApiResponse(responseCode = "404", description = "Clase no encontrada")
    })
    public Clase updateClase(@PathVariable Long idClase, @RequestBody Clase clase) {
        return claseService.update(idClase, clase);
        // Clase claseUpdate = claseService.update(idClase, clase);
        // if(claseUpdate != null) {
        //     return new ResponseEntity<>(claseUpdate, HttpStatus.OK);
        // } else {
        //     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        // }
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
    public Clase cambiarVisibilidad(@PathVariable Long idClase) {
        return claseService.cambiarVisibilidad(idClase);
        // Clase clase =  claseService.cambiarVisibilidad(idClase);
        // if(clase != null) {
        //     return new ResponseEntity<>(clase, HttpStatus.OK);
        // } else {
        //     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        // }
    }

    @PatchMapping("/{idClase}/activar")
    @Operation(summary = "Activar Clase", description = "Activa o Desactiva una clase para que esté disponible en la plataforma")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación exitosa",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = Clase.class))),
        @ApiResponse(responseCode = "404", description = "Clase no encontrada")
    })
    public Clase cambiarEstadoActivo(@PathVariable Long idClase) {
        return claseService.activar(idClase);
        // Clase clase =  claseService.activar(idClase);
        // if(clase != null) {
        //     return new ResponseEntity<>(clase, HttpStatus.OK);
        // } else {
        //     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        // }
            // .map(ResponseEntity::ok)
            // .orElse(ResponseEntity.notFound().build());
    }
}

package com.edutech.mscurso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edutech.mscurso.assemblers.ClaseModelAssembler;
import com.edutech.mscurso.model.Clase;
import com.edutech.mscurso.model.Modulo;
import com.edutech.mscurso.service.ClaseService;
import com.edutech.mscurso.service.ModuloService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;

import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@Tag(name = "ClaseV2", description = "Operaciones CRUD para las Clases")
@RequestMapping("/api/v2/clases")
public class ClaseControllerV2 {

    @Autowired
    private ModuloService moduloService;

    @Autowired
    private ClaseService claseService;

    @Autowired
    private ClaseModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Listar Clases", description = "Obtiene una lista de todas las clases disponibles")
    public CollectionModel<EntityModel<Clase>> listarClases() {
        List<EntityModel<Clase>> clases = claseService.findAll().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());

        return CollectionModel.of(clases,
            linkTo(methodOn(ClaseControllerV2.class).listarClases()).withSelfRel());
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Crear Clase", description = "Crea una nueva clase asociada a un módulo")
    public ResponseEntity<EntityModel<Clase>> createClase(@RequestBody Clase clase) {
        int idLink = clase.getModulo().getIdModulo();
        Modulo modulo = moduloService.moduloxId(idLink);
        if(modulo != null) {
            clase.setModulo(modulo);
        }
        Clase nuevaClase = claseService.save(clase);
        return ResponseEntity
            .created(linkTo(methodOn(ClaseControllerV2.class).readClase(nuevaClase.getIdClase())).toUri())
            .body(assembler.toModel(nuevaClase));
    }

    @GetMapping(value = "/{idClase}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Clase> readClase(@PathVariable int idClase) {
        Clase clase = claseService.findById(idClase);
        return assembler.toModel(clase);
    }

    @PutMapping(value = "/{idClase}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Leer Clase", description = "Obtiene los detalles de una clase específica por su ID")
    public ResponseEntity<EntityModel<Clase>> updateClase(@PathVariable int idClase, @RequestBody Clase clase) {
        Clase claseActualizada = claseService.update(idClase, clase);
        return ResponseEntity
            .ok(assembler.toModel(claseActualizada));
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

    @PatchMapping(value = "/{idClase}/modificar/visibilidad", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Cambiar Visibilidad de Clase", description = "Cambia la visibilidad de una clase entre pública y privada")
    public ResponseEntity<EntityModel<Clase>> cambiarVisibilidad(@PathVariable int idClase) {
        Clase claseActualizada = claseService.cambiarVisibilidad(idClase);
        return ResponseEntity
            .ok(assembler.toModel(claseActualizada));
    }

    @PatchMapping(value = "/{idClase}/activar", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Activar Clase", description = "Activa o Desactiva una clase para que esté disponible en la plataforma")
    public ResponseEntity<EntityModel<Clase>> cambiarEstadoActivo(@PathVariable int idClase) {
        Clase claseActualizada = claseService.activar(idClase);
        return ResponseEntity
            .ok(assembler.toModel(claseActualizada));
    }
}

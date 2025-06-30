package com.edutech.mscurso.controller;

import java.util.List;
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

import com.edutech.mscurso.assemblers.ModuloModelAssembler;
import com.edutech.mscurso.model.Curso;
import com.edutech.mscurso.model.Modulo;
import com.edutech.mscurso.service.CursoService;
import com.edutech.mscurso.service.ModuloService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/modulos")
@Tag(name = "Modulo", description = "Operaciones CRUD para los Módulos")
public class ModuloControllerV2 {

    @Autowired
    private ModuloService moduloService;

    @Autowired
    private CursoService cursoService;

    @Autowired
    private ModuloModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Listar Módulos", description = "Obtiene una lista de todos los módulos disponibles")
    public CollectionModel<EntityModel<Modulo>> listarModulos() {
        List<EntityModel<Modulo>> modulos = moduloService.findAll().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());
        return CollectionModel.of(modulos,
            linkTo(methodOn(ModuloControllerV2.class).listarModulos()).withSelfRel());
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Crear Módulo", description = "Crea un nuevo módulo asociado a un curso")
    public ResponseEntity<EntityModel<Modulo>> createModulo(@RequestBody Modulo modulo) {
        int idLink = modulo.getCurso().getIdCurso();
        Curso curso = cursoService.cursoxId(idLink);
        if(curso != null) {
            modulo.setCurso(curso);
        }
        Modulo nuevoModulo = moduloService.save(modulo);
        return ResponseEntity
            .created(linkTo(methodOn(ModuloControllerV2.class).readModulo(nuevoModulo.getIdModulo())).toUri())
            .body(assembler.toModel(nuevoModulo));
    }

    @GetMapping(value = "/{idModulo}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Leer Módulo", description = "Obtiene los detalles de un módulo por su ID")
    public EntityModel<Modulo> readModulo(@PathVariable int idModulo) {
        Modulo nuevoModulo = moduloService.findById(idModulo);
        return assembler.toModel(nuevoModulo);
    }

    @PutMapping(value = "/{idModulo}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Actualizar Módulo", description = "Actualiza los detalles de un módulo existente")
    public ResponseEntity<EntityModel<Modulo>> updateModulo(@PathVariable int idModulo, @RequestBody Modulo modulo) {
        Modulo moduloActualizado = moduloService.update(idModulo, modulo);
        return ResponseEntity
            .ok(assembler.toModel(moduloActualizado));
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

    @PatchMapping(value ="/{idModulo}/activar", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Activar Módulo", description = "Cambia el estado de un módulo a activo")
    public ResponseEntity<EntityModel<Modulo>> cambiarEstadoActivo(@PathVariable int idModulo) {
        Modulo moduloActualizado = moduloService.activar(idModulo);
        return ResponseEntity
            .ok(assembler.toModel(moduloActualizado));
    }
}

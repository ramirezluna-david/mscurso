package com.edutech.mscurso.controller;

import java.util.List;

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

import com.edutech.mscurso.model.Clase;
import com.edutech.mscurso.model.Modulo;
import com.edutech.mscurso.service.ClaseService;
import com.edutech.mscurso.service.ModuloService;

import io.swagger.v3.oas.annotations.Operation;
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
    public ResponseEntity<List<Clase>> listarClases() {
        List<Clase> clases = claseService.findAll();
        if(clases.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(clases, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Crear Clase", description = "Crea una nueva clase asociada a un módulo")
    public ResponseEntity<Clase> createClase(@RequestBody Clase clase) {
        int idLink = clase.getModulo().getIdModulo();
        Modulo modulo = moduloService.moduloxId(idLink);
        if(modulo != null) {
            clase.setModulo(modulo);
        }
        Clase buscarClase = claseService.findById(clase.getIdClase());
        if(buscarClase == null) {
            Clase nuevaClase = claseService.save(clase);
            return new ResponseEntity<>(nuevaClase, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @GetMapping("/{idClase}")
    @Operation(summary = "Leer Clase", description = "Obtiene los detalles de una clase específica por su ID")
    public ResponseEntity<Clase> readClase(@PathVariable int idClase) {
        Clase buscarClase = claseService.findById(idClase);
        if(buscarClase != null) {
            return new ResponseEntity<>(buscarClase, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{idClase}")
    @Operation(summary = "Actualizar Clase", description = "Actualiza los detalles de una clase existente")
    public ResponseEntity<Clase> updateClase(@PathVariable int idClase, @RequestBody Clase clase) {
        Clase actualizarClase = claseService.update(idClase, clase);
        if(actualizarClase != null) {
            return new ResponseEntity<>(actualizarClase, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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
    public ResponseEntity<Clase> cambiarVisibilidad(@PathVariable int idClase) {
        Clase habilitarClase = claseService.cambiarVisibilidad(idClase);
        if(habilitarClase != null) {
            return new ResponseEntity<>(habilitarClase, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{idClase}/activar")
    @Operation(summary = "Activar Clase", description = "Activa o Desactiva una clase para que esté disponible en la plataforma")
    public ResponseEntity<Clase> cambiarEstadoActivo(@PathVariable int idClase) {
        Clase activarClase = claseService.activar(idClase);
        if(activarClase != null) {
            return new ResponseEntity<>(activarClase, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

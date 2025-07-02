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

import com.edutech.mscurso.model.Curso;
import com.edutech.mscurso.model.Modulo;
import com.edutech.mscurso.service.CursoService;
import com.edutech.mscurso.service.ModuloService;

import io.swagger.v3.oas.annotations.Operation;
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
    public ResponseEntity<List<Modulo>> listarModulos() {
        List<Modulo> modulos = moduloService.findAll();
        if(modulos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(modulos, HttpStatus.OK);
        }
    }

    @PostMapping
    @Operation(summary = "Crear Módulo", description = "Crea un nuevo módulo asociado a un curso")
    public ResponseEntity<Modulo> createModulo(@RequestBody Modulo modulo) {
        int idLink = modulo.getCurso().getIdCurso();
        Curso curso = cursoService.cursoxId(idLink);
        if(curso != null) {
            modulo.setCurso(curso);
        }
        Modulo buscarModulo = moduloService.findById(modulo.getIdModulo());
        if(buscarModulo == null) {
            Modulo nuevoModulo = moduloService.save(modulo);
            return new ResponseEntity<>(nuevoModulo, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{idModulo}")
    @Operation(summary = "Leer Módulo", description = "Obtiene los detalles de un módulo por su ID")
    public ResponseEntity<Modulo> readModulo(@PathVariable int idModulo) {
        Modulo buscarModulo = moduloService.findById(idModulo);
        if(buscarModulo != null) {
            return new ResponseEntity<>(buscarModulo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{idModulo}")
    @Operation(summary = "Actualizar Módulo", description = "Actualiza los detalles de un módulo existente")
    public ResponseEntity<Modulo> updateModulo(@PathVariable int idModulo, @RequestBody Modulo modulo) {
        Modulo actualizarModulo =  moduloService.update(idModulo, modulo);
        if(actualizarModulo != null) {
            return new ResponseEntity<>(actualizarModulo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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
    public ResponseEntity<Modulo> cambiarEstadoActivo(@PathVariable int idModulo) {
        Modulo activarModulo = moduloService.activar(idModulo);
        if(activarModulo != null) {
            return new ResponseEntity<>(activarModulo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

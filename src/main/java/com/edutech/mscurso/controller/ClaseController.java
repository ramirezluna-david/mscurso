package com.edutech.mscurso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

@RestController
@RequestMapping("/api/v1/clases")
public class ClaseController {

    @Autowired
    private ModuloService moduloService;

    @Autowired
    private ClaseService claseService;

    @GetMapping
    public ResponseEntity<List<Clase>> listarClases() {
        List<Clase> clases = claseService.findAll();
        if(clases.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(clases, HttpStatus.OK);
    }

    @PostMapping
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
    public ResponseEntity<Clase> readClase(@PathVariable int idClase) {
        Clase clase = claseService.findById(idClase);
        if(clase != null) {
            return new ResponseEntity<>(clase, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{idClase}")
    public ResponseEntity<Clase> updateClase(@PathVariable int idClase, @RequestBody Clase clase) {
        if(claseService.update(idClase, clase)) {
            return new ResponseEntity<>(clase, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{idClase}")
    public ResponseEntity<?> deleteClase(@PathVariable int idClase) {
        Clase buscarClase = claseService.findById(idClase);
        if(buscarClase != null) {
            claseService.deleteById(idClase);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{idClase}/modificar/visibilidad")
    public ResponseEntity<?> cambiarVisibilidad(@PathVariable int idClase) {
        if(claseService.cambiarVisibilidad(idClase)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

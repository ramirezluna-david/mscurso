package com.edutech.mscurso.controller;

import java.util.List;
import java.util.Optional;

// import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
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

@RestController
@RequestMapping("/api/v1/modulos")
public class ModuloController {

    @Autowired
    private ModuloService moduloService;

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<Modulo>> listarModulos() {
        List<Modulo> modulos = moduloService.findAll();
        if(modulos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(modulos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Modulo> createModulo(@RequestBody Modulo modulo) {
        Long idLink = modulo.getCurso().getIdCurso();
        Curso curso = cursoService.cursoxId(idLink);
        if(curso != null) {
            modulo.setCurso(curso);
        }

        Optional<Modulo> buscarModulo = moduloService.findById(modulo.getIdModulo());
        if(buscarModulo == null) {
            Modulo nuevoModulo = moduloService.save(modulo);
            return new ResponseEntity<>(nuevoModulo, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/{idModulo}")
    public ResponseEntity<Modulo> readModulo(@PathVariable Long idModulo) {
        // Modulo buscarModulo = moduloService.findById(idModulo);
        // if(buscarModulo != null) {
        //     return new ResponseEntity<>(buscarModulo, HttpStatus.OK);
        // } else {
        //     // return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        //     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        // }
        return moduloService.findById(idModulo)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{idModulo}")
    public ResponseEntity<Modulo> updateModulo(@PathVariable Long idModulo, @RequestBody Modulo modulo) {
        if(moduloService.update(idModulo, modulo)) {
            return new ResponseEntity<>(modulo, HttpStatus.OK);
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
    public ResponseEntity<?> cambiarEstadoActivo(@PathVariable Long idModulo) {
        if(moduloService.activar(idModulo)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

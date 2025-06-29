package com.edutech.mscurso.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.edutech.mscurso.controller.CursoControllerV2;
import com.edutech.mscurso.model.Curso;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class CursoModelAssembler implements RepresentationModelAssembler<Curso, EntityModel<Curso>>{

    @Override
    public EntityModel<Curso> toModel(Curso curso) {
        return EntityModel.of(curso,
            linkTo(methodOn(CursoControllerV2.class).readCurso(curso.getIdCurso())).withSelfRel(),
            linkTo(methodOn(CursoControllerV2.class).listarCursos()).withRel("cursos"));
    }
}

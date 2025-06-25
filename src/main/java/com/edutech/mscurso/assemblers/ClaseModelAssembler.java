package com.edutech.mscurso.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import com.edutech.mscurso.controller.ClaseControllerV2;
import com.edutech.mscurso.model.Clase;

import org.springframework.stereotype.Component;

@Component
public class ClaseModelAssembler implements RepresentationModelAssembler<Clase, EntityModel<Clase>> {

    @Override
    public EntityModel<Clase> toModel(Clase clase) {
        return EntityModel.of(clase,
        linkTo(methodOn(ClaseControllerV2.class).readClase(clase.getIdClase())).withSelfRel(),
        linkTo(methodOn(ClaseControllerV2.class).listarClases()).withRel("clases"));
    }

}

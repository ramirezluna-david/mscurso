package com.edutech.mscurso.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.edutech.mscurso.controller.ModuloControllerV2;
import com.edutech.mscurso.model.Modulo;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ModuloModelAssembler implements RepresentationModelAssembler<Modulo, EntityModel<Modulo>>{

    @Override
    public EntityModel<Modulo> toModel(Modulo modulo) {
        return EntityModel.of(modulo,
            linkTo(methodOn(ModuloControllerV2.class).readModulo(modulo.getIdModulo())).withSelfRel(),
            linkTo(methodOn(ModuloControllerV2.class).listarModulos()).withRel("modulos"));
    }
}

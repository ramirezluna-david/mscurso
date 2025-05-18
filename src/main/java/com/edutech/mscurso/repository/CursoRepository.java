package com.edutech.mscurso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edutech.mscurso.model.Curso;
import java.util.List;


public interface CursoRepository extends JpaRepository<Curso, Integer>{

    Curso save(Curso curso);

    Curso findByIdCurso(int idCurso);

    List<Curso> findAll();

    Curso deletebyidCurso(int idCurso);
}

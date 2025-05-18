package com.edutech.mscurso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edutech.mscurso.model.Curso;
import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer>{

    Curso save(Curso curso);

    Curso findByIdCurso(int idCurso);

    List<Curso> findAll();

    Curso deleteByIdCurso(int idCurso);
}

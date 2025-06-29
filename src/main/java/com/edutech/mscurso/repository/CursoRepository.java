package com.edutech.mscurso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edutech.mscurso.model.Curso;
import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer>{

    @SuppressWarnings("unchecked")
    Curso save(Curso curso);

    Curso findById(int idCurso);

    List<Curso> findAll();

    void deleteById(int idCurso);

    Curso getReferenceById(int idCurso);
}

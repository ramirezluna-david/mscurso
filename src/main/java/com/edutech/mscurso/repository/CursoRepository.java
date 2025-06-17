package com.edutech.mscurso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edutech.mscurso.model.Curso;
// import java.util.List;
// import java.util.Optional;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long>{

    // @SuppressWarnings("unchecked")
    // Curso save(Curso curso);

    // Optional<Curso> findById(Long idCurso);

    // List<Curso> findAll();

    // void deleteById(int idCurso);

    // Curso getReferenceById(Long idCurso);
}

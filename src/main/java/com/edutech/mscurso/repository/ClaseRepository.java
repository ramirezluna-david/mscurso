package com.edutech.mscurso.repository;

// import java.util.List;
// import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edutech.mscurso.model.Clase;

@Repository
public interface ClaseRepository extends JpaRepository<Clase, Long>{

    // @SuppressWarnings("unchecked")
    // Clase save(Clase clase);

    // Optional<Clase> findById(Long idClase);

    // List<Clase> findAll();

    // void deleteById(Long idClase);

    // Clase getReferenceById(int idClase);
}

package com.edutech.mscurso.repository;

// import java.util.List;
// import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edutech.mscurso.model.Modulo;

@Repository
public interface ModuloRepository extends JpaRepository<Modulo, Long>{

    // @SuppressWarnings("unchecked")
    // Modulo save(Modulo modulo);

    // Optional<Modulo> findById(Long idModulo);

    // List<Modulo> findAll();

    // void deleteById(Long idModulo);

    // Modulo getReferenceById(Long idModulo);
}

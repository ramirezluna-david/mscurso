package com.edutech.mscurso.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edutech.mscurso.model.Clase;

@Repository
public interface ClaseRepository extends JpaRepository<Clase, Integer>{

    @SuppressWarnings("unchecked")
    Clase save(Clase clase);

    Clase findById(int idClase);

    List<Clase> findAll();

    void deleteById(int idClase);

    Clase getReferenceById(int idClase);
}

package com.edutech.mscurso.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edutech.mscurso.model.Modulo;

@Repository
public interface ModuloRepository extends JpaRepository<Modulo, Integer>{

    Modulo save(Modulo modulo);

    Modulo findById(int idModulo);

    List<Modulo> findAll();

    void deleteById(int idModulo);

    Modulo getReferenceById(int idModulo);
}

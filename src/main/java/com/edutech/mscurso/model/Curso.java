package com.edutech.mscurso.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Curso")

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCurso;

    private String titulo;

    private String descripcion;

    //private Categoria categoria;

    private String precio;


}

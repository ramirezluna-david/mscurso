package com.edutech.mscurso.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "modulo")

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Modulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idModulo;

    /*@Column(nullable = false)
    private int idCurso;*/

    @Column(length = 50, nullable = false)
    private String titulo;

    @Column(length = 1000, nullable = false)
    private String descripcion;

    @OneToMany(mappedBy = "modulo", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Clase> clases;

    @ManyToOne()
    @JoinColumn(name = "idCurso")
    @JsonBackReference
    private Curso curso;
}

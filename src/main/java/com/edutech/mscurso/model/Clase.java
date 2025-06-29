package com.edutech.mscurso.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clase")

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Clase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idClase;

    @Column(length = 50, nullable = false)
    private String titulo;

    @Column(length = 1000, nullable = false)
    private String descripcion;

    @Column(length = 50, nullable = false)
    private String categoria;

    @Column(nullable = false)
    private LocalDate fechaCreacion;

    @Column(nullable = false)
    private Boolean publicado;

    @Column(nullable = false)
    private Boolean activo;

    @ManyToOne()
    @JoinColumn(name = "idModulo")
    @JsonBackReference
    private Modulo modulo;
}

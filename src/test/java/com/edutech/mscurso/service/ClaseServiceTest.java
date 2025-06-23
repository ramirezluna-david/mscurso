package com.edutech.mscurso.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.edutech.mscurso.model.Clase;
import com.edutech.mscurso.model.Modulo;
import com.edutech.mscurso.repository.ClaseRepository;

public class ClaseServiceTest {

    @Mock
    private ClaseRepository claseRepository;

    @InjectMocks
    private ClaseService claseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGuardarClase() {
        Modulo modulo1 = new Modulo();
        Clase clase = new Clase(
            null,
            "Introducción a Java", // titulo
            "Esta clase cubre los fundamentos básicos del lenguaje de programación Java.", // descripcion
            "Programación", // categoria
            LocalDate.of(2025, 6, 18), // fechaCreacion
            true, // publicado
            true, // activo
            modulo1 // modulo
        );
        Clase claseGuardada = new Clase(
            1L,
            "Introducción a Java", // titulo
            "Esta clase cubre los fundamentos básicos del lenguaje de programación Java.", // descripcion
            "Programación", // categoria
            LocalDate.of(2025, 6, 18), // fechaCreacion
            true, // publicado
            true, // activo
            modulo1 // modulo
        );
        when(claseRepository.save(clase)).thenReturn(claseGuardada);

        Clase resultado = claseService.save(clase);
        assertNotNull(resultado);
        assertThat(resultado.getIdClase()).isEqualTo(1L);
        verify(claseRepository).save(clase);
    }

    @Test
    void testListarClases() {
        Modulo modulo1 = new Modulo();
        Clase clase1 = new Clase(
            1L,
            "Introducción a Java",
            "Aprende los fundamentos del lenguaje Java: sintaxis, tipos de datos y estructuras básicas.",
            "Programación",
            LocalDate.of(2025, 6, 10),
            true,
            true,
            modulo1
        );
        Clase clase2 = new Clase(
            2L,
            "POO en Java",
            "Explora la Programación Orientada a Objetos: clases, herencia, encapsulamiento y más.",
            "Programación",
            LocalDate.of(2025, 6, 12),
            true,
            true,
            modulo1
        );
        when(claseRepository.findAll()).thenReturn(Arrays.asList(clase1, clase2));

        List<Clase> resultado = claseService.findAll();
        assertNotNull(resultado);
        assertThat(resultado).hasSize(2).contains(clase1, clase2);
        verify(claseRepository).findAll();
    }


}

package com.edutech.mscurso.service;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

    @Test
    void testFindById() {
        Modulo modulo1 = new Modulo();
        Clase clase1 = new Clase(
        100L,
        "POO en Java",
        "Clase sobre los fundamentos de la programación orientada a objetos en Java.",
        "Programación",
        LocalDate.of(2024, 10, 5),
        true,
        true,
        modulo1
        );
        when(claseRepository.findById(100L)).thenReturn(Optional.of(clase1));

        Optional<Clase> resultado = claseService.findById(100L);
        assertNotNull(resultado);
        assertThat(resultado.get().getIdClase()).isEqualTo(100L);
        assertThat(resultado.get().getTitulo()).isEqualTo(clase1.getTitulo());
        assertThat(resultado.get().getDescripcion()).isEqualTo(clase1.getDescripcion());
        assertThat(resultado.get().getCategoria()).isEqualTo(clase1.getCategoria());
        assertThat(resultado.get().getFechaCreacion()).isEqualTo(clase1.getFechaCreacion());
        assertThat(resultado.get().getPublicado()).isEqualTo(clase1.getPublicado());
        assertThat(resultado.get().getActivo()).isEqualTo(clase1.getActivo());
        assertThat(resultado.get().getModulo()).isEqualTo(clase1.getModulo());
        verify(claseRepository).findById(100L);
    }

    @Test
    void testUpdateClase() {
        Modulo modulo1 = new Modulo();
        Clase claseExistente = new Clase(
            1L,
            "Introducción a Java",
            "Aprende los fundamentos del lenguaje Java: sintaxis, tipos de datos y estructuras básicas.",
            "Programación",
            LocalDate.of(2025, 6, 10),
            true,
            true,
            modulo1
        );
        Clase claseActualizada = new Clase(
        1L,
        "POO en Java",
        "Clase sobre los fundamentos de la programación orientada a objetos en Java.",
        "Programación",
        LocalDate.of(2024, 10, 5),
        true,
        true,
        modulo1
        );
        when(claseRepository.findById(1L)).thenReturn(Optional.of(claseExistente));
        when(claseRepository.save(claseExistente)).thenReturn(claseExistente);

        Clase resultado = claseService.update(1L, claseActualizada);
        assertNotNull(resultado);
        assertThat(resultado.getIdClase()).isEqualTo(1L);
        assertThat(resultado.getTitulo()).isEqualTo(claseActualizada.getTitulo());
        assertThat(resultado.getDescripcion()).isEqualTo(claseActualizada.getDescripcion());
        assertThat(resultado.getCategoria()).isEqualTo(claseActualizada.getCategoria());
        assertThat(resultado.getFechaCreacion()).isEqualTo(claseActualizada.getFechaCreacion());
        assertThat(resultado.getPublicado()).isEqualTo(claseActualizada.getPublicado());
        assertThat(resultado.getActivo()).isEqualTo(claseActualizada.getActivo());
        assertThat(resultado.getModulo()).isEqualTo(claseActualizada.getModulo());
        verify(claseRepository).findById(1L);
    }

    @Test
    void testActivar() {
        Clase clase = new Clase();
        clase.setIdClase(1L);
        clase.setActivo(false);
        clase.setPublicado(false);
        when(claseRepository.findById(1L)).thenReturn(Optional.of(clase));
        when(claseRepository.save(clase)).thenReturn(clase);

        Clase claseActivar = claseService.activar(1L);
        assertNotNull(claseActivar);
        assertThat(claseActivar.getIdClase()).isEqualTo(1L);
        assertThat(claseActivar.getActivo()).isEqualTo(true);
        assertTrue(claseActivar.getActivo());
    }

    @Test
    void testDesactivar() {
        Clase clase = new Clase();
        clase.setIdClase(1L);
        clase.setActivo(true);
        clase.setPublicado(false);
        when(claseRepository.findById(1L)).thenReturn(Optional.of(clase));
        when(claseRepository.save(clase)).thenReturn(clase);

        Clase claseActivar = claseService.activar(1L);
        assertNotNull(claseActivar);
        assertThat(claseActivar.getIdClase()).isEqualTo(1L);
        assertThat(claseActivar.getActivo()).isEqualTo(false);
        assertFalse(claseActivar.getActivo());
    }

    @Test
    void testDeleteById() {
        Clase clase = new Clase();
        clase.setIdClase(1L);
        doNothing().when(claseRepository).deleteById(clase.getIdClase());

        claseService.deleteById(clase.getIdClase());
        verify(claseRepository, times(1)).deleteById(clase.getIdClase());
    }
}

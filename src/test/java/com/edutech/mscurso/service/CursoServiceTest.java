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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.edutech.mscurso.model.Curso;
import com.edutech.mscurso.model.Modulo;
import com.edutech.mscurso.repository.CursoRepository;

public class CursoServiceTest {

    @Mock
    private CursoRepository cursoRepository;

    @InjectMocks
    private CursoService cursoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        List<Modulo> modulos = new ArrayList<>();
        Curso curso = new Curso(
            10,
            "Programación en Java desde cero",
            "Curso completo para aprender Java desde lo más básico.",
            "Programación",
            49.99,
            5,
            LocalDate.of(2024, 9, 15),
            true,
            true,
            modulos
        );
        Curso cursoGuardar = new Curso(
            10,
            "Programación en Java desde cero",
            "Curso completo para aprender Java desde lo más básico.",
            "Programación",
            49.99,
            5,
            LocalDate.of(2024, 9, 15),
            true,
            true,
            modulos
        );
        when(cursoRepository.save(curso)).thenReturn(cursoGuardar);

        Curso resultado = cursoService.save(curso);
        assertNotNull(resultado);
        assertThat(resultado.getIdCurso()).isEqualTo(10);
        assertThat(resultado.getTitulo()).isEqualTo(cursoGuardar.getTitulo());
        assertThat(resultado.getDescripcion()).isEqualTo(cursoGuardar.getDescripcion());
        assertThat(resultado.getCategoria()).isEqualTo(cursoGuardar.getCategoria());
        assertThat(resultado.getPrecio()).isEqualTo(cursoGuardar.getPrecio());
        assertThat(resultado.getIdProfesor()).isEqualTo(cursoGuardar.getIdProfesor());
        assertThat(resultado.getFechaCreacion()).isEqualTo(cursoGuardar.getFechaCreacion());
        assertThat(resultado.getActivo()).isEqualTo(cursoGuardar.getActivo());
        assertThat(resultado.getPublicado()).isEqualTo(cursoGuardar.getPublicado());
        assertThat(resultado.getModulos()).isEqualTo(cursoGuardar.getModulos());
        verify(cursoRepository).save(curso);
    }

    @Test
    void testFindAll() {
        List<Modulo> modulos = new ArrayList<>();
        Curso curso2 = new Curso(
            11,
            "Java Intermedio",
            "Curso sobre colecciones, streams y manejo de errores.",
            "Programación",
            59.99,
            6,
            LocalDate.of(2024, 10, 5),
            true,
            true,
            modulos
        );

        Curso curso3 = new Curso(
            12,
            "Java Avanzado",
            "Hilos, concurrencia y patrones de diseño en Java.",
            "Programación",
            69.99,
            7,
            LocalDate.of(2024, 10, 20),
            false,
            true,
            modulos
        );
        when(cursoRepository.findAll()).thenReturn(Arrays.asList(curso2, curso3));

        List<Curso> resultado = cursoService.findAll();
        assertNotNull(resultado);
        assertThat(resultado).hasSize(2).contains(curso2, curso3);
        verify(cursoRepository).findAll();
    }

    @Test
    void testFindById() {
        List<Modulo> modulos = new ArrayList<>();
        Curso curso1 = new Curso(
            10,
            "Programación en Java desde cero",
            "Curso completo para aprender Java desde lo más básico.",
            "Programación",
            49.99,
            5,
            LocalDate.of(2024, 9, 15),
            true,
            true,
            modulos
        );

        when(cursoRepository.findById(10)).thenReturn(curso1);
        Curso resultado = cursoService.findById(10);
        assertNotNull(resultado);
        assertThat(resultado.getIdCurso()).isEqualTo(10);
        assertThat(resultado.getTitulo()).isEqualTo(curso1.getTitulo());
        assertThat(resultado.getDescripcion()).isEqualTo(curso1.getDescripcion());
        assertThat(resultado.getCategoria()).isEqualTo(curso1.getCategoria());
        assertThat(resultado.getPrecio()).isEqualTo(curso1.getPrecio());
        assertThat(resultado.getIdProfesor()).isEqualTo(curso1.getIdProfesor());
        assertThat(resultado.getFechaCreacion()).isEqualTo(curso1.getFechaCreacion());
        assertThat(resultado.getActivo()).isEqualTo(curso1.getActivo());
        assertThat(resultado.getPublicado()).isEqualTo(curso1.getPublicado());
        assertThat(resultado.getModulos()).isEqualTo(curso1.getModulos());
        verify(cursoRepository).findById(10);
    }

    @Test
    void testUpdate() {
        List<Modulo> modulos = new ArrayList<>();
        Curso cursoExistente = new Curso(
            1,
            "Introducción a Java",
            "Aprende los fundamentos del lenguaje Java: sintaxis, tipos de datos y estructuras básicas.",
            "Programación",
            29.99,
            3,
            LocalDate.of(2025, 6, 10),
            true,
            true,
            modulos
        );
        Curso cursoActualizado = new Curso(
            1,
            "Programación en Java desde cero",
            "Curso completo para aprender Java desde lo más básico.",
            "Programación",
            49.99,
            5,
            LocalDate.of(2024, 9, 15),
            true,
            true,
            modulos
        );
        when(cursoRepository.findById(1)).thenReturn(cursoExistente);
        when(cursoRepository.save(cursoActualizado)).thenReturn(cursoActualizado);

        Curso resultado = cursoService.update(1, cursoActualizado);
        assertNotNull(resultado);
        assertThat(resultado.getIdCurso()).isEqualTo(1);
        assertThat(resultado.getTitulo()).isEqualTo(cursoActualizado.getTitulo());
        assertThat(resultado.getDescripcion()).isEqualTo(cursoActualizado.getDescripcion());
        assertThat(resultado.getCategoria()).isEqualTo(cursoActualizado.getCategoria());
        assertThat(resultado.getPrecio()).isEqualTo(cursoActualizado.getPrecio());
        assertThat(resultado.getIdProfesor()).isEqualTo(cursoActualizado.getIdProfesor());
        assertThat(resultado.getFechaCreacion()).isEqualTo(cursoActualizado.getFechaCreacion());
        assertThat(resultado.getActivo()).isEqualTo(cursoActualizado.getActivo());
        assertThat(resultado.getPublicado()).isEqualTo(cursoActualizado.getPublicado());
        assertThat(resultado.getModulos()).isEqualTo(cursoActualizado.getModulos());
        verify(cursoRepository).findById(1);
        verify(cursoRepository).save(cursoActualizado);
    }

    @Test
    void testActivar1() {
        Curso curso = new Curso();
        curso.setIdCurso(1);
        curso.setActivo(false);
        when(cursoRepository.findById(1)).thenReturn(curso);
        when(cursoRepository.save(curso)).thenReturn(curso);

        Curso cursoActivar = cursoService.activar(1);
        assertNotNull(cursoActivar);
        assertThat(cursoActivar.getIdCurso()).isEqualTo(1);
        assertThat(cursoActivar.getActivo()).isEqualTo(true);
        assertTrue(cursoActivar.getActivo());
    }

    @Test
    void testActivar2() {
        Curso curso = new Curso();
        curso.setIdCurso(1);
        curso.setActivo(true);
        curso.setPublicado(false);
        when(cursoRepository.findById(1)).thenReturn(curso);
        when(cursoRepository.save(curso)).thenReturn(curso);

        Curso cursoActivar = cursoService.activar(1);
        assertNotNull(cursoActivar);
        assertThat(cursoActivar.getIdCurso()).isEqualTo(1);
        assertThat(cursoActivar.getActivo()).isEqualTo(false);
        assertFalse(cursoActivar.getActivo());
    }

    @Test
    void testDeleteById() {
        Curso curso = new Curso();
        curso.setIdCurso(1);
        curso.setPublicado(false);
        doNothing().when(cursoRepository).deleteById(curso.getIdCurso());

        cursoService.deleteById(curso.getIdCurso());
        verify(cursoRepository, times(1)).deleteById(curso.getIdCurso());
    }

    @Test
    void testCambiarVisibilidad() {
        Curso curso = new Curso();
        curso.setIdCurso(1);
        curso.setPublicado(false);
        when(cursoRepository.findById(1)).thenReturn(curso);
        when(cursoRepository.save(curso)).thenReturn(curso);

        Curso cursoCambiado = cursoService.cambiarVisibilidad(1);
        assertNotNull(cursoCambiado);
        assertThat(cursoCambiado.getIdCurso()).isEqualTo(1);
        assertThat(cursoCambiado.getPublicado()).isEqualTo(true);
        assertTrue(cursoCambiado.getPublicado());
    }

    @Test
    void testAsignarTutor() {
        Curso curso = new Curso();
        curso.setIdCurso(1);
        curso.setIdProfesor(3);
        when(cursoRepository.findById(1)).thenReturn(curso);
        when(cursoRepository.save(curso)).thenReturn(curso);

        Curso resultado = cursoService.asignarTutor(1, 5);
        assertNotNull(resultado);
        assertThat(resultado.getIdCurso()).isEqualTo(1);
        assertThat(resultado.getIdProfesor()).isEqualTo(5);
    }
}

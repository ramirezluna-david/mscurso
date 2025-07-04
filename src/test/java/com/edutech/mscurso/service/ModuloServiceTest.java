package com.edutech.mscurso.service;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.edutech.mscurso.model.Clase;
import com.edutech.mscurso.model.Curso;
import com.edutech.mscurso.model.Modulo;
import com.edutech.mscurso.repository.ModuloRepository;

public class ModuloServiceTest {

    @Mock
    private ModuloRepository moduloRepository;

    @InjectMocks
    private ModuloService moduloService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        List<Clase> clases = new ArrayList<>();
        Curso curso = new Curso();
        Modulo modulo = new Modulo(
            10,
            "Módulo 1: Fundamentos de Spring",
            "Aprenderás los conceptos básicos de Spring Boot.",
            clases,
            true,
            curso
        );
        Modulo moduloGuardar = new Modulo(
            10,
            "Módulo 1: Fundamentos de Spring",
            "Aprenderás los conceptos básicos de Spring Boot.",
            clases,
            true,
            curso
        );
        when(moduloRepository.save(modulo)).thenReturn(moduloGuardar);

        Modulo resultado = moduloService.save(modulo);
        assertNotNull(resultado);
        assertThat(resultado.getIdModulo()).isEqualTo(10);
        assertThat(resultado.getTitulo()).isEqualTo(moduloGuardar.getTitulo());
        assertThat(resultado.getDescripcion()).isEqualTo(moduloGuardar.getDescripcion());
        assertThat(resultado.getClases()).isEqualTo(moduloGuardar.getClases());
        assertThat(resultado.getActivo()).isEqualTo(moduloGuardar.getActivo());
        assertThat(resultado.getCurso()).isEqualTo(moduloGuardar.getCurso());
        verify(moduloRepository).save(modulo);
    }

    @Test
    void testFindAll() {
        List<Clase> clases = new ArrayList<>();
        Curso curso = new Curso();
        Modulo modulo1 = new Modulo(
            20,
            "Módulo 2: Spring Data JPA",
            "Dominando el acceso a datos con JPA y Spring Data.",
            clases, // Lista vacía temporalmente
            true,
            curso
        );
        Modulo modulo3 = new Modulo(
            20,
            "Módulo 3: Seguridad con Spring Security",
            "Implementa autenticación y autorización en tus aplicaciones.",
            clases,
            true,
            curso
        );
        when(moduloRepository.findAll()).thenReturn(Arrays.asList(modulo1, modulo3));

        List<Modulo> resultado = moduloService.findAll();
        assertNotNull(resultado);
        assertThat(resultado).hasSize(2).contains(modulo1, modulo3);
        verify(moduloRepository).findAll();
    }

    @Test
    void testFindById() {
        List<Clase> clases = new ArrayList<>();
        Curso curso = new Curso();
        Modulo modulo4 = new Modulo(
            22,
            "Módulo 4: Testing y Buenas Prácticas",
            "Aprende a testear tu aplicación con JUnit y Mockito.",
            clases,
            true,
            curso
        );
        when(moduloRepository.findById(22)).thenReturn(modulo4);

        Modulo resultado = moduloService.findById(22);
        assertNotNull(resultado);
        assertThat(resultado.getIdModulo()).isEqualTo(modulo4.getIdModulo());
        assertThat(resultado.getTitulo()).isEqualTo(modulo4.getTitulo());
        assertThat(resultado.getDescripcion()).isEqualTo(modulo4.getDescripcion());
        assertThat(resultado.getClases()).isEqualTo(modulo4.getClases());
        assertThat(resultado.getActivo()).isEqualTo(modulo4.getActivo());
        assertThat(resultado.getCurso()).isEqualTo(modulo4.getCurso());
        verify(moduloRepository).findById(22);
    }

    @Test
    void testUpdate() {
        List<Clase> clases = new ArrayList<>();
        Curso curso = new Curso();
        Modulo moduloExistente = new Modulo(
            10,
            "Módulo 1: Fundamentos de Spring",
            "Aprenderás los conceptos básicos de Spring Boot.",
            clases,
            true,
            curso
        );
        Modulo moduloActualizar = new Modulo(
            10,
            "Módulo 4: Testing y Buenas Prácticas",
            "Aprende a testear tu aplicación con JUnit y Mockito.",
            clases,
            true,
            curso
        );
        when(moduloRepository.findById(10)).thenReturn(moduloExistente);
        when(moduloRepository.save(moduloExistente)).thenReturn(moduloExistente);

        Modulo resultado = moduloService.update(10, moduloActualizar);
        assertNotNull(resultado);
        assertThat(resultado.getIdModulo()).isEqualTo(moduloActualizar.getIdModulo());
        assertThat(resultado.getTitulo()).isEqualTo(moduloActualizar.getTitulo());
        assertThat(resultado.getDescripcion()).isEqualTo(moduloActualizar.getDescripcion());
        assertThat(resultado.getClases()).isEqualTo(moduloActualizar.getClases());
        assertThat(resultado.getActivo()).isEqualTo(moduloActualizar.getActivo());
        assertThat(resultado.getCurso()).isEqualTo(moduloActualizar.getCurso());
        verify(moduloRepository).findById(10);
        verify(moduloRepository).save(moduloActualizar);
    }

    @Test
    void testActivar1() {
        Modulo modulo = new Modulo();
        modulo.setIdModulo(1);
        modulo.setActivo(false);
        when(moduloRepository.findById(1)).thenReturn(modulo);
        when(moduloRepository.save(modulo)).thenReturn(modulo);

        Modulo moduloActivar = moduloService.activar(1);
        assertNotNull(moduloActivar);
        assertThat(moduloActivar.getIdModulo()).isEqualTo(1);
        assertThat(moduloActivar.getActivo()).isEqualTo(true);
        assertTrue(moduloActivar.getActivo());
    }

    @Test
    void testActivar2() {
        Modulo modulo = new Modulo();
        modulo.setIdModulo(1);
        modulo.setActivo(true);
        when(moduloRepository.findById(1)).thenReturn(modulo);
        when(moduloRepository.save(modulo)).thenReturn(modulo);

        Modulo moduloActivar = moduloService.activar(1);
        assertNotNull(moduloActivar);
        assertThat(moduloActivar.getIdModulo()).isEqualTo(1);
        assertThat(moduloActivar.getActivo()).isEqualTo(false);
        assertFalse(moduloActivar.getActivo());
    }

    @Test
    void testDeleteById() {
        Modulo modulo = new Modulo();
        modulo.setIdModulo(1);
        doNothing().when(moduloRepository).deleteById(modulo.getIdModulo());

        moduloService.deleteById(modulo.getIdModulo());
        verify(moduloRepository, times(1)).deleteById(modulo.getIdModulo());
    }
}

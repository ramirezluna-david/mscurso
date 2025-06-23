package com.edutech.mscurso.service;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

// import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.edutech.mscurso.model.Clase;
// import com.edutech.mscurso.model.Clase;
import com.edutech.mscurso.model.Curso;
import com.edutech.mscurso.model.Modulo;
// import com.edutech.mscurso.repository.CursoRepository;
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
    void testGuardarModulo() {
        List<Clase> clases = new ArrayList<>();
        Curso curso = new Curso();
        Modulo modulo = new Modulo(
            10L,
            "Módulo 1: Fundamentos de Spring",
            "Aprenderás los conceptos básicos de Spring Boot.",
            clases,
            true,
            curso
        );
        Modulo moduloGuardar = new Modulo(
            10L,
            "Módulo 1: Fundamentos de Spring",
            "Aprenderás los conceptos básicos de Spring Boot.",
            clases,
            true,
            curso
        );
        when(moduloRepository.save(modulo)).thenReturn(moduloGuardar);

        Modulo resultado = moduloService.save(modulo);
        assertNotNull(resultado);
        assertThat(resultado.getIdModulo()).isEqualTo(10L);
        assertThat(resultado.getTitulo()).isEqualTo(moduloGuardar.getTitulo());
        assertThat(resultado.getDescripcion()).isEqualTo(moduloGuardar.getDescripcion());
        assertThat(resultado.getClases()).isEqualTo(moduloGuardar.getClases());
        assertThat(resultado.getActivo()).isEqualTo(moduloGuardar.getActivo());
        assertThat(resultado.getCurso()).isEqualTo(moduloGuardar.getCurso());
        verify(moduloRepository).save(modulo);
    }

    @Test
    void testListarModulos() {
        List<Clase> clases = new ArrayList<>();
        Curso curso = new Curso();
        Modulo modulo1 = new Modulo(
            20L,
            "Módulo 2: Spring Data JPA",
            "Dominando el acceso a datos con JPA y Spring Data.",
            clases, // Lista vacía temporalmente
            true,
            curso
        );
        Modulo modulo3 = new Modulo(
            21L,
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
            22L,
            "Módulo 4: Testing y Buenas Prácticas",
            "Aprende a testear tu aplicación con JUnit y Mockito.",
            clases,
            true,
            curso
        );
        when(moduloRepository.findById(22L)).thenReturn(Optional.of(modulo4));

        Optional<Modulo> resultado = moduloService.findById(22L);
        assertNotNull(resultado);
        assertThat(resultado.get().getIdModulo()).isEqualTo(modulo4.getIdModulo());
        assertThat(resultado.get().getTitulo()).isEqualTo(modulo4.getTitulo());
        assertThat(resultado.get().getDescripcion()).isEqualTo(modulo4.getDescripcion());
        assertThat(resultado.get().getClases()).isEqualTo(modulo4.getClases());
        assertThat(resultado.get().getActivo()).isEqualTo(modulo4.getActivo());
        assertThat(resultado.get().getCurso()).isEqualTo(modulo4.getCurso());
        verify(moduloRepository).findById(22L);
    }

    @Test
    void testUpdateModulo() {
        List<Clase> clases = new ArrayList<>();
        Curso curso = new Curso();
        Modulo moduloExistente = new Modulo(
            10L,
            "Módulo 1: Fundamentos de Spring",
            "Aprenderás los conceptos básicos de Spring Boot.",
            clases,
            true,
            curso
        );
        Modulo moduloActualizar = new Modulo(
            10L,
            "Módulo 4: Testing y Buenas Prácticas",
            "Aprende a testear tu aplicación con JUnit y Mockito.",
            clases,
            true,
            curso
        );
        when(moduloRepository.findById(10L)).thenReturn(Optional.of(moduloExistente));
        when(moduloRepository.save(moduloExistente)).thenReturn(moduloExistente);

        Modulo resultado = moduloService.update(10L, moduloActualizar);
        assertNotNull(resultado);
        assertThat(resultado.getIdModulo()).isEqualTo(moduloActualizar.getIdModulo());
        assertThat(resultado.getTitulo()).isEqualTo(moduloActualizar.getTitulo());
        assertThat(resultado.getDescripcion()).isEqualTo(moduloActualizar.getDescripcion());
        assertThat(resultado.getClases()).isEqualTo(moduloActualizar.getClases());
        assertThat(resultado.getActivo()).isEqualTo(moduloActualizar.getActivo());
        assertThat(resultado.getCurso()).isEqualTo(moduloActualizar.getCurso());
        verify(moduloRepository).findById(10L);
        verify(moduloRepository).save(moduloActualizar);
    }

    @Test
    void testActivar() {
        Modulo modulo = new Modulo();
        modulo.setIdModulo(1L);
        modulo.setActivo(false);
        when(moduloRepository.findById(1L)).thenReturn(Optional.of(modulo));
        when(moduloRepository.save(modulo)).thenReturn(modulo);

        Modulo moduloActivar = moduloService.activar(1L);
        assertNotNull(moduloActivar);
        assertThat(moduloActivar.getIdModulo()).isEqualTo(1L);
        assertThat(moduloActivar.getActivo()).isEqualTo(true);
        assertTrue(moduloActivar.getActivo());
    }

    @Test
    void testDesactivar() {
        Modulo modulo = new Modulo();
        modulo.setIdModulo(1L);
        modulo.setActivo(true);
        when(moduloRepository.findById(1L)).thenReturn(Optional.of(modulo));
        when(moduloRepository.save(modulo)).thenReturn(modulo);

        Modulo moduloActivar = moduloService.activar(1L);
        assertNotNull(moduloActivar);
        assertThat(moduloActivar.getIdModulo()).isEqualTo(1L);
        assertThat(moduloActivar.getActivo()).isEqualTo(false);
        assertFalse(moduloActivar.getActivo());
    }

    @Test
    void testDeleteById() {
        Modulo modulo = new Modulo();
        modulo.setIdModulo(1L);
        doNothing().when(moduloRepository).deleteById(modulo.getIdModulo());

        moduloService.deleteById(modulo.getIdModulo());
        verify(moduloRepository, times(1)).deleteById(modulo.getIdModulo());
    }
}

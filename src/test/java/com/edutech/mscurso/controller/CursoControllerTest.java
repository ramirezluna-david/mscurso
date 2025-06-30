package com.edutech.mscurso.controller;

import com.edutech.mscurso.model.Curso;
import com.edutech.mscurso.model.Modulo;
import com.edutech.mscurso.service.CursoService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CursoController.class)
public class CursoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @SuppressWarnings("removal")
    @MockBean
    private CursoService cursoService;

    private Curso curso;

    @BeforeEach
    void setup() {
        List<Modulo> modulos = new ArrayList<>();
        curso = new Curso(
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
    }

    @Test
    void testListarCursos() throws Exception{
        Mockito.when(cursoService.findAll()).thenReturn(Arrays.asList(curso));

        mockMvc.perform(get("/api/v1/cursos"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].idCurso").value(10))
            .andExpect(jsonPath("$[0].titulo").value("Programación en Java desde cero"))
            .andExpect(jsonPath("$[0].descripcion").value("Curso completo para aprender Java desde lo más básico."))
            .andExpect(jsonPath("$[0].categoria").value("Programación"))
            .andExpect(jsonPath("$[0].precio").value(49.99))
            .andExpect(jsonPath("$[0].publicado").value(true))
            .andExpect(jsonPath("$[0].activo").value(true));
    }

    @Test
    void testCreateCurso() throws Exception {
        Mockito.when(cursoService.save(any(Curso.class))).thenReturn(curso);
        mockMvc.perform(post("/api/v1/cursos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(curso)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.idCurso").value(10))
            .andExpect(jsonPath("$.titulo").value("Programación en Java desde cero"))
            .andExpect(jsonPath("$.descripcion").value("Curso completo para aprender Java desde lo más básico."))
            .andExpect(jsonPath("$.categoria").value("Programación"))
            .andExpect(jsonPath("$.precio").value(49.99))
            .andExpect(jsonPath("$.publicado").value(true))
            .andExpect(jsonPath("$.activo").value(true));
    }

    @Test
    void testReadCurso() throws Exception {
        Mockito.when(cursoService.findById(10)).thenReturn(curso);

        mockMvc.perform(get("/api/v1/cursos/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.idCurso").value(10))
            .andExpect(jsonPath("$.titulo").value("Programación en Java desde cero"))
            .andExpect(jsonPath("$.descripcion").value("Curso completo para aprender Java desde lo más básico."))
            .andExpect(jsonPath("$.categoria").value("Programación"))
            .andExpect(jsonPath("$.precio").value(49.99))
            .andExpect(jsonPath("$.publicado").value(true))
            .andExpect(jsonPath("$.activo").value(true));
    }

    @Test
    void testUpdateCurso() throws Exception {
        Mockito.when(cursoService.update(10, curso)).thenReturn(curso);

        mockMvc.perform(put("/api/v1/cursos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(curso)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.idCurso").value(10))
            .andExpect(jsonPath("$.titulo").value("Programación en Java desde cero"))
            .andExpect(jsonPath("$.descripcion").value("Curso completo para aprender Java desde lo más básico."))
            .andExpect(jsonPath("$.categoria").value("Programación"))
            .andExpect(jsonPath("$.precio").value(49.99))
            .andExpect(jsonPath("$.publicado").value(true))
            .andExpect(jsonPath("$.activo").value(true));
    }

    @Test
    void testCambiarVisibilidad() throws Exception {
        Mockito.when(cursoService.cambiarVisibilidad(10)).thenReturn(curso);

        mockMvc.perform(put("/api/v1/cursos/1/modificar/visibilidad")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(curso)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.idCurso").value(10))
            .andExpect(jsonPath("$.titulo").value("Programación en Java desde cero"))
            .andExpect(jsonPath("$.descripcion").value("Curso completo para aprender Java desde lo más básico."))
            .andExpect(jsonPath("$.categoria").value("Programación"))
            .andExpect(jsonPath("$.precio").value(49.99))
            .andExpect(jsonPath("$.publicado").value(true))
            .andExpect(jsonPath("$.activo").value(true));
    }

    @Test
    void testActivar() throws Exception {
        Mockito.when(cursoService.activar(10)).thenReturn(curso);

        mockMvc.perform(put("/api/v1/cursos/1/activar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(curso)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.idCurso").value(10))
            .andExpect(jsonPath("$.titulo").value("Programación en Java desde cero"))
            .andExpect(jsonPath("$.descripcion").value("Curso completo para aprender Java desde lo más básico."))
            .andExpect(jsonPath("$.categoria").value("Programación"))
            .andExpect(jsonPath("$.precio").value(49.99))
            .andExpect(jsonPath("$.publicado").value(true))
            .andExpect(jsonPath("$.activo").value(true));
    }
}

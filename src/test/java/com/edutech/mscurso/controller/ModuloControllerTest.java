package com.edutech.mscurso.controller;

import com.edutech.mscurso.model.Clase;
import com.edutech.mscurso.model.Curso;
import com.edutech.mscurso.model.Modulo;
import com.edutech.mscurso.model.Modulo;
import com.edutech.mscurso.service.ModuloService;
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

@WebMvcTest(ModuloController.class)
public class ModuloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @SuppressWarnings("removal")
    @MockBean
    private ModuloService moduloService;

    private Modulo modulo;

    @BeforeEach
    void setup() {
        List<Clase> clases = new ArrayList<>();
        Curso curso = new Curso();
        modulo = new Modulo(
            10,
            "Módulo 1: Fundamentos de Spring",
            "Aprenderás los conceptos básicos de Spring Boot.",
            clases,
            true,
            curso
        );
    }

    @Test
    void testListarModulos() throws Exception{
        Mockito.when(moduloService.findAll()).thenReturn(Arrays.asList(modulo));

        mockMvc.perform(get("/api/v1/modulos"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].idModulo").value(10))
            .andExpect(jsonPath("$[0].titulo").value("Programación en Java desde cero"))
            .andExpect(jsonPath("$[0].descripcion").value("Modulo completo para aprender Java desde lo más básico."))
            .andExpect(jsonPath("$[0].activo").value(true));
    }

    @Test
    void testCreateModulo() throws Exception {
        Mockito.when(moduloService.save(any(Modulo.class))).thenReturn(modulo);
        mockMvc.perform(post("/api/v1/modulos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(modulo)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.idModulo").value(10))
            .andExpect(jsonPath("$.titulo").value("Programación en Java desde cero"))
            .andExpect(jsonPath("$.descripcion").value("Modulo completo para aprender Java desde lo más básico."))
            .andExpect(jsonPath("$.activo").value(true));
    }

    @Test
    void testReadModulo() throws Exception {
        Mockito.when(moduloService.findById(10)).thenReturn(modulo);

        mockMvc.perform(get("/api/v1/modulos/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.idModulo").value(10))
            .andExpect(jsonPath("$.titulo").value("Programación en Java desde cero"))
            .andExpect(jsonPath("$.descripcion").value("Modulo completo para aprender Java desde lo más básico."))
            .andExpect(jsonPath("$.activo").value(true));
    }

    @Test
    void testUpdateModulo() throws Exception {
        Mockito.when(moduloService.update(10, modulo)).thenReturn(modulo);

        mockMvc.perform(put("/api/v1/modulos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(modulo)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.idModulo").value(10))
            .andExpect(jsonPath("$.titulo").value("Programación en Java desde cero"))
            .andExpect(jsonPath("$.descripcion").value("Modulo completo para aprender Java desde lo más básico."))
            .andExpect(jsonPath("$.activo").value(true));
    }

    @Test
    void testActivar() throws Exception {
        Mockito.when(moduloService.activar(10)).thenReturn(modulo);

        mockMvc.perform(put("/api/v1/modulos/1/activar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(modulo)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.idModulo").value(10))
            .andExpect(jsonPath("$.titulo").value("Programación en Java desde cero"))
            .andExpect(jsonPath("$.descripcion").value("Modulo completo para aprender Java desde lo más básico."))
            .andExpect(jsonPath("$.activo").value(true));
    }
}

package com.edutech.mscurso.controller;

import com.edutech.mscurso.model.Clase;
import com.edutech.mscurso.model.Modulo;
import com.edutech.mscurso.service.ClaseService;
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
import java.util.Arrays;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClaseController.class)
public class ClaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @SuppressWarnings("removal")
    @MockBean
    private ClaseService claseService;

    private Clase clase;
    private Modulo modulo;

    @BeforeEach
    void setup() {
        modulo = new Modulo();
        clase = new Clase(
            1,
            "Introducción a Java",
            "Aprende los fundamentos del lenguaje Java: sintaxis, tipos de datos y estructuras básicas.",
            "Programación",
            LocalDate.of(2025, 6, 10),
            true,
            true,
            modulo
        );
    }

    @Test
    void testListarClases() throws Exception{
        Mockito.when(claseService.findAll()).thenReturn(Arrays.asList(clase));

        mockMvc.perform(get("/api/v1/clases"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].idClase").value(1));
    }

    @Test
    void testCreateClase() throws Exception {
        Mockito.when(claseService.save(any(Clase.class))).thenReturn(clase);
        mockMvc.perform(post("/api/v1/clases")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clase)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.idClase").value(1));
    }


    @Test
    void testReadClase() throws Exception {
        Mockito.when(claseService.findById(1)).thenReturn(clase);

        mockMvc.perform(get("/api/v1/clase/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.idClase").value(1));
    }

    @Test
    void testReadClaseNotFound() throws Exception {
        Mockito.when(claseService.findById(2)).thenReturn(clase);

        mockMvc.perform(get("/api/v1/clase/2"))
            .andExpect(status().isNotFound());
    }

    @Test
    void testUpdateClase() throws Exception {
        Mockito.when(claseService.update(1, clase)).thenReturn(clase);

        mockMvc.perform(put("/api/v1/clases/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clase)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.idClase").value(1));
    }

    @Test
    void testUpdateClaseNotFound() throws Exception {
        Mockito.when(claseService.update(2, clase)).thenReturn(clase);

        mockMvc.perform(put("/api/v1/clases/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clase)))
            .andExpect(status().isNotFound());
    }

    @Test
    void testCambiarVisibilidad() throws Exception {
        Mockito.when(claseService.cambiarVisibilidad(1)).thenReturn(clase);

        mockMvc.perform(patch("/api/v1/clases/1/modificar/visibilidad")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clase)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.idClase").value(1));
    }

    @Test
    void testCambiarVisibilidadNotFound() throws Exception {
        Mockito.when(claseService.cambiarVisibilidad(2)).thenReturn(clase);

        mockMvc.perform(patch("/api/v1/clases/2/modificar/visibilidad")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clase)))
            .andExpect(status().isNotFound());
    }

    @Test
    void testActivar() throws Exception {
        Mockito.when(claseService.activar(1)).thenReturn(clase);

        mockMvc.perform(patch("/api/v1/clases/1/activar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clase)))
            .andExpect(status().isOk());
    }

    @Test
    void testActivarNotFound() throws Exception {
        Mockito.when(claseService.activar(2)).thenReturn(clase);

        mockMvc.perform(patch("/api/v1/clases/2/activar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clase)))
            .andExpect(status().isNotFound());
    }
}

package com.escrims.application;

import java.time.LocalDateTime;

/**
 * DTO (Data Transfer Object).
<<<<<<< HEAD
 * Transporta los filtros de busqueda.
 * ¡AHORA CON FILTRO DE FORMATO!
=======
 * Esta clase "tonta" transporta los filtros de busqueda
 * desde el Controller (API) hasta la Fachada.
 * Corresponde a: GET /api/scrims?juego=&region=...
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588
 */
public class FiltrosBusqueda {

    private String juego;
    private String region;
    private int rangoMin;
    private int rangoMax;
    private LocalDateTime fecha;
    private int latenciaMax;

<<<<<<< HEAD
    // ============================================================
    // ¡¡NUEVO ATRIBUTO!!
    // ============================================================
    private String formato; // ej: "5v5", "1v1"
    // ============================================================

    // --- Getters y Setters ---

    public String getJuego() { return juego; }
    public void setJuego(String juego) { this.juego = juego; }

    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }

    public int getRangoMin() { return rangoMin; }
    public void setRangoMin(int rangoMin) { this.rangoMin = rangoMin; }

    public int getRangoMax() { return rangoMax; }
    public void setRangoMax(int rangoMax) { this.rangoMax = rangoMax; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public int getLatenciaMax() { return latenciaMax; }
    public void setLatenciaMax(int latenciaMax) { this.latenciaMax = latenciaMax; }

    // --- NUEVOS METODOS ---
    public String getFormato() { return formato; }
    public void setFormato(String formato) { this.formato = formato; }
=======
    // --- Getters y Setters ---

    public String getJuego() {
        return juego;
    }

    public void setJuego(String juego) {
        this.juego = juego;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getRangoMin() {
        return rangoMin;
    }

    public void setRangoMin(int rangoMin) {
        this.rangoMin = rangoMin;
    }

    public int getRangoMax() {
        return rangoMax;
    }

    public void setRangoMax(int rangoMax) {
        this.rangoMax = rangoMax;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public int getLatenciaMax() {
        return latenciaMax;
    }

    public void setLatenciaMax(int latenciaMax) {
        this.latenciaMax = latenciaMax;
    }
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588
}
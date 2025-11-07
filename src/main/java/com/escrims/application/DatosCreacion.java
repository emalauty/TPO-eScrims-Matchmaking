package com.escrims.application;

import java.time.LocalDateTime; // (Lo agregamos por si acaso)

/**
 * DTO (Data Transfer Object).
 * Clase "tonta" que solo transporta datos
 * desde la API/Vista hacia la Fachada.
 * ¡AHORA CON SETTERS!
 */
public class DatosCreacion {
    private String juego;
    private String formato;
    private int rangoMin;
    private int rangoMax;
    private LocalDateTime fechaHora;

    // --- Getters y Setters ---

    public String getJuego() { return juego; }
    public void setJuego(String juego) { // <-- ¡EL METODO QUE FALTABA!
        this.juego = juego;
    }

    public String getFormato() { return formato; }
    public void setFormato(String formato) { this.formato = formato; }

    public int getRangoMin() { return rangoMin; }
    public void setRangoMin(int rangoMin) { // <-- ¡Y ESTE!
        this.rangoMin = rangoMin;
    }

    public int getRangoMax() { return rangoMax; }
    public void setRangoMax(int rangoMax) { // <-- ¡Y ESTE!
        this.rangoMax = rangoMax;
    }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }
}
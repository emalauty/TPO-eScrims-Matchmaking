package com.escrims.domainCore;

import java.util.Objects;

/**
 * Objeto de Valor (Value Object) que asocia un Juego con un Rango
 * para un Usuario.
 * Reemplaza a un Map.
 */
public class RangoJuego {

    private final String juego; // ej: "Valorant"
    private final String rango; // ej: "Diamante 3"

    public RangoJuego(String juego, String rango) {
        this.juego = juego;
        this.rango = rango;
    }

    // --- Getters (no setters, es inmutable) ---

    public String getJuego() { return juego; }
    public String getRango() { return rango; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RangoJuego that = (RangoJuego) o;
        return juego.equals(that.juego) && rango.equals(that.rango);
    }

    @Override
    public int hashCode() {
        return Objects.hash(juego, rango);
    }
}
package com.escrims.domainCore;

import java.util.List;
import java.util.ArrayList;

/**
 * Entidad que representa a uno de los equipos dentro de un Scrim.
 * Mantiene la lista de jugadores de ese lado.
 */
public class Equipo {

    private String id;
    private String lado; // ej: "Equipo A", "Atacantes"

    // Relacion: Un equipo tiene uno o mas jugadores
    private List<Usuario> jugadores;

    public Equipo(String lado) {
        this.lado = lado;
        this.jugadores = new ArrayList<>();
    }

    public void addJugador(Usuario usuario) {
        // (Aqui podria ir logica para validar que no se exceda el cupo del equipo)
        this.jugadores.add(usuario);
    }

    public void removeJugador(Usuario usuario) {
        this.jugadores.remove(usuario);
    }

    // --- Getters y Setters ---

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getLado() { return lado; }
    public void setLado(String lado) { this.lado = lado; }
    public List<Usuario> getJugadores() { return jugadores; }
}
package com.escrims.domainCore;

/**
 * Entidad que registra la confirmacion de un Usuario
 * para un Scrim especifico.
 */
public class Confirmacion {

    private String id;
    private boolean confirmado;

    // Relacion: La confirmacion pertenece a un Scrim
    private Scrim scrim;

    // Relacion: La confirmacion es de un Usuario
    private Usuario usuario;

    public Confirmacion(Scrim scrim, Usuario usuario) {
        this.scrim = scrim;
        this.usuario = usuario;
        this.confirmado = false; // Inicia sin confirmar
    }

    public void confirmar() {
        this.confirmado = true;
    }

    public void desconfirmar() {
        this.confirmado = false;
    }

    // --- Getters y Setters ---

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public boolean isConfirmado() { return confirmado; }
    public Usuario getUsuario() { return usuario; }
    public Scrim getScrim() { return scrim; }
}
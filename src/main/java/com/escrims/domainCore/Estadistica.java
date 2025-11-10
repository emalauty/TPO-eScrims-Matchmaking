package com.escrims.domainCore;

/**
 * Entidad que guarda los resultados y estadisticas
 * de un Usuario en un Scrim finalizado.
<<<<<<< HEAD
 * (Requisito: Estadisticas y feedback)
=======
 [cite_start]* [cite: 98]
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588
 */
public class Estadistica {

    private String id;
    private boolean mvp;
    private String kda; // ej: "15/3/10"
    private String observaciones;

<<<<<<< HEAD
    // Relaciones
    private Scrim scrim;
=======
    // Relacion: La estadistica es de un Scrim
    private Scrim scrim;

    // Relacion: La estadistica es de un Usuario
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588
    private Usuario usuario;

    public Estadistica(Scrim scrim, Usuario usuario) {
        this.scrim = scrim;
        this.usuario = usuario;
        this.mvp = false;
    }

    // --- Getters y Setters ---

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public boolean isMvp() { return mvp; }
    public void setMvp(boolean mvp) { this.mvp = mvp; }
    public String getKda() { return kda; }
    public void setKda(String kda) { this.kda = kda; }
    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
    public Scrim getScrim() { return scrim; }
    public Usuario getUsuario() { return usuario; }
}
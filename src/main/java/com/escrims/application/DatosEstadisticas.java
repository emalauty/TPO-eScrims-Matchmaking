package com.escrims.application;

/**
 * DTO para cargar las estadisticas de un jugador
 * al finalizar un Scrim.
 * (Para POST /api/scrims/{id}/estadisticas)
 */
public class DatosEstadisticas {

    private String usuarioId;
    private String kda;
    private boolean mvp;
    private String observaciones;

    // --- Getters y Setters ---

    public String getUsuarioId() { return usuarioId; }
    public void setUsuarioId(String usuarioId) { this.usuarioId = usuarioId; }

    public String getKda() { return kda; }
    public void setKda(String kda) { this.kda = kda; }

    public boolean isMvp() { return mvp; }
    public void setMvp(boolean mvp) { this.mvp = mvp; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
}
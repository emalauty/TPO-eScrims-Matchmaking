package com.escrims.application;

/**
 * DTO para confirmar asistencia.
 * (Para POST /api/scrims/{id}/confirmaciones)
 */
public class DatosConfirmar {

    private String usuarioId;

    // --- Getters y Setters ---

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }
}
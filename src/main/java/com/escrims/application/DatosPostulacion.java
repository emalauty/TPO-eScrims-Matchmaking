package com.escrims.application;

import com.escrims.domainCore.Rol;

/**
 * DTO para transportar datos de una nueva postulacion.
 * (Para POST /api/scrims/{id}/postulaciones)
 */
public class DatosPostulacion {

    private String usuarioId;
    private Rol rol; // Usamos nuestra clase Rol del dominio

    // --- Getters y Setters ---

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}

package com.escrims.application;

import com.escrims.domainCore.Rol;

/**
 * DTO para la accion del Patron Command 'AsignarRol'.
 * (Para POST /api/scrims/{id}/acciones/asignar-rol)
 */
public class DatosAsignarRol {

    private String usuarioId;
    private Rol rol;

    // --- Getters y Setters ---

    public String getUsuarioId() { return usuarioId; }
    public void setUsuarioId(String usuarioId) { this.usuarioId = usuarioId; }

    public Rol getRol() { return rol; }
    public void setRol(Rol rol) { this.rol = rol; }
}
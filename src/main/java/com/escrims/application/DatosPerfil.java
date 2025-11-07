package com.escrims.application;

import com.escrims.domainCore.Rol;
import java.util.List;

/**
 * DTO (Data Transfer Object).
 * Transporta los datos del formulario "Editar Perfil"
 * a la fachada. (Requisito: Perfil Editable)
 */
public class DatosPerfil {

    private String username;
    private String region;
    private int mmr;
    private List<Rol> roles;
    private String disponibilidad; // El campo que agregamos a Usuario

    // --- Getters y Setters ---

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }

    public int getMmr() { return mmr; }
    public void setMmr(int mmr) { this.mmr = mmr; }

    public List<Rol> getRoles() { return roles; }
    public void setRoles(List<Rol> roles) { this.roles = roles; }

    public String getDisponibilidad() { return disponibilidad; }
    public void setDisponibilidad(String disponibilidad) { this.disponibilidad = disponibilidad; }
}
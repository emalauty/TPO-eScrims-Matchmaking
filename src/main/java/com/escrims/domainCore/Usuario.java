package com.escrims.domainCore;

import java.util.List;
import java.util.ArrayList;

public class Usuario {

    private String id;
    private String username;
    private String email;
    private String passwordHash;
    private String estadoEmail; // "Pendiente", "Verificado"
    private String region;
    private String rolSistema;
    private int mmr;
    private String disponibilidad;

    // ============================================================
    // ¡¡ATRIBUTO NUEVO!!
    // ============================================================
    private String verificationToken; // Token para verificar email
    // ============================================================

    private List<RangoJuego> rangos;
    private List<Rol> rolesPreferidos;

    public Usuario() {
        this.rangos = new ArrayList<>();
        this.rolesPreferidos = new ArrayList<>();
    }

    // --- METODOS DE LOGICA ---

    public void addRango(RangoJuego rangoJuego) { this.rangos.add(rangoJuego); }
    public void verificarEmail() {
        this.estadoEmail = "Verificado";
        this.verificationToken = null; // El token ya no es necesario
    }

    // --- GETTERS Y SETTERS ---
    // ( ... todos los getters/setters que ya tenias ... )
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public String getEstadoEmail() { return estadoEmail; }
    public void setEstadoEmail(String estadoEmail) { this.estadoEmail = estadoEmail; }
    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }
    public String getRolSistema() { return rolSistema; }
    public void setRolSistema(String rolSistema) { this.rolSistema = rolSistema; }
    public List<RangoJuego> getRangos() { return rangos; }
    public void setRangos(List<RangoJuego> rangos) { this.rangos = rangos; }
    public List<Rol> getRolesPreferidos() { return rolesPreferidos; }
    public void setRolesPreferidos(List<Rol> rolesPreferidos) { this.rolesPreferidos = rolesPreferidos; }
    public int getMmr() { return mmr; }
    public void setMmr(int mmr) { this.mmr = mmr; }
    public String getDisponibilidad() { return disponibilidad; }
    public void setDisponibilidad(String disponibilidad) { this.disponibilidad = disponibilidad; }


    public String getVerificationToken() { return verificationToken; }
    public void setVerificationToken(String verificationToken) { this.verificationToken = verificationToken; }
    // ============================================================
}
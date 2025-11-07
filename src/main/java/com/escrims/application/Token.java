package com.escrims.application;

/**
 * DTO (Data Transfer Object).
 * Esta clase representa el "boleto" de sesion (ej. un JWT)
 * que se le devuelve al usuario cuando se loguea exitosamente.
 * (Para POST /api/auth/login)
 */
public class Token {

    private final String accessToken;
    private final long expiresIn; // (Cuanto dura, en segundos)

    public Token(String accessToken, long expiresIn) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
    }

    // --- Getters ---

    public String getAccessToken() {
        return accessToken;
    }

    public long getExpiresIn() {
        return expiresIn;
    }
}
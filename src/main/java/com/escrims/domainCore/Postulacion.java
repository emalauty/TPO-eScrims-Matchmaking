package com.escrims.domainCore;

public class Postulacion {
    private String id;
    private Usuario usuario;
    private Scrim scrim;
    private Rol rolDeseado; // <-- Actualizado a la clase Rol
    private String estado;

    public Postulacion(Usuario u, Scrim s, Rol r) { /*...*/ }
    // ... Metodos ...
}
package com.escrims.domainCore;

import java.util.Objects;

/**
 * Reemplaza al enum 'Rol'.
 * Es un Objeto de Valor (Value Object).
 */
public class Rol {

    private final String nombre; // ej: "Duelista", "Support"

    public Rol(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del rol no puede ser vacio");
        }
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    // Es crucial agregar equals y hashCode para que dos Rols "Duelista"
    // sean tratados como el mismo objeto.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rol rol = (Rol) o;
        return nombre.equals(rol.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }
}
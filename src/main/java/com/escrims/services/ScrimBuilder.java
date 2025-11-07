package com.escrims.services;

import com.escrims.domainCore.Rol;
import com.escrims.domainCore.Scrim;
import com.escrims.domainCore.Usuario;
import java.time.LocalDateTime;

/**
 * Patron Builder para construir un Scrim complejo paso a paso.
 */
public class ScrimBuilder {

    private Scrim scrimEnConstruccion;

    public ScrimBuilder() {
        this.scrimEnConstruccion = new Scrim();
    }

    public ScrimBuilder conOrganizador(Usuario organizador) {
        // scrimEnConstruccion.setOrganizador(organizador);
        return this;
    }

    public ScrimBuilder deJuego(String juego) {
        // scrimEnConstruccion.setJuego(juego);
        return this;
    }

    public ScrimBuilder conFormato(String formato) {
        // scrimEnConstruccion.setFormato(formato);
        return this;
    }

    public ScrimBuilder conRango(int min, int max) {
        // scrimEnConstruccion.setRangoMin(min);
        // scrimEnConstruccion.setRangoMax(max);
        return this;
    }

    public ScrimBuilder enFecha(LocalDateTime fecha) {
        // scrimEnConstruccion.setFechaHora(fecha);
        return this;
    }

    public ScrimBuilder necesitaRol(Rol rol) {
        // scrimEnConstruccion.getRolesObligatorios().add(rol);
        return this;
    }

    /**
     * Valida y devuelve el Scrim construido.
     */
    public Scrim build() {
        // AQUI VA LA VALIDACION FINAL
        // if (scrimEnConstruccion.getJuego() == null) {
        //     throw new IllegalStateException("El juego no puede ser nulo");
        // }
        // if (scrimEnConstruccion.getRangoMin() > scrimEnConstruccion.getRangoMax()) {
        //     throw new IllegalStateException("El rango minimo no puede ser mayor al maximo");
        // }
        System.out.println("Scrim construido exitosamente.");
        return this.scrimEnConstruccion;
    }
}
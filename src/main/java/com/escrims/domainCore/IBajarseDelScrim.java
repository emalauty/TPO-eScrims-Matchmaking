package com.escrims.domainCore;

/**
 * Interfaz de Accion del Patron State (Modo Complejo).
 * Define la capacidad de un estado para manejar
 * cuando un jugador se "baja" (cancela su asistencia).
 */
public interface IBajarseDelScrim {
    /**
     * Logica para cuando un jugador abandona el lobby.
     * @param scrim El contexto
     * @param usuario El jugador que se baja
     */
    void bajarJugador(Scrim scrim, Usuario usuario);
}
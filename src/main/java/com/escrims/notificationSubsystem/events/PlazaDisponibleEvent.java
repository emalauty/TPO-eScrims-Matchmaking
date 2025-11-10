package com.escrims.notificationSubsystem.events;

import com.escrims.domainCore.Scrim;

/**
 * Evento que se publica cuando un jugador se baja
 * de un Scrim que estaba lleno, dejando un lugar libre.
 * (Para el Requisito: Sistema de Suplentes)
 */
public class PlazaDisponibleEvent implements DomainEvent {

    private final Scrim scrimConPlaza;

    public PlazaDisponibleEvent(Scrim scrim) {
        this.scrimConPlaza = scrim;
    }

    public Scrim getScrim() {
        return scrimConPlaza;
    }
}
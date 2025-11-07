package com.escrims.notificationSubsystem.events;

/**
 * Evento que se publica cuando un Scrim
 * es cancelado por el organizador (o sistema)
 * antes de que comience.
 */
public class ScrimCanceladoEvent implements DomainEvent {

    private final String scrimId;
    private final String motivo; // (Opcional: "Organizador cancelo", "Faltaron jugadores")

    public ScrimCanceladoEvent(String scrimId, String motivo) {
        this.scrimId = scrimId;
        this.motivo = motivo;
    }

    public ScrimCanceladoEvent(String scrimId) {
        this(scrimId, "El scrim fue cancelado."); // Motivo por defecto
    }

    public String getScrimId() {
        return scrimId;
    }

    public String getMotivo() {
        return motivo;
    }
}
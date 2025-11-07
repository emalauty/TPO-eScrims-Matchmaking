package com.escrims.notificationSubsystem.events;

public class ScrimFinalizadoEvent implements DomainEvent {
    private final String scrimId;
    public ScrimFinalizadoEvent(String scrimId) { this.scrimId = scrimId; }
    public String getScrimId() { return scrimId; }
}
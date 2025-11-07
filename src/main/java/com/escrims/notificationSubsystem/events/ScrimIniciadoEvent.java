package com.escrims.notificationSubsystem.events;

public class ScrimIniciadoEvent implements DomainEvent {
    private final String scrimId;
    public ScrimIniciadoEvent(String scrimId) { this.scrimId = scrimId; }
    public String getScrimId() { return scrimId; }
}
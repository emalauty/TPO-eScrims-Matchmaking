package com.escrims.notificationSubsystem.events;

public class ScrimConfirmadoEvent implements DomainEvent {
    private final String scrimId;
    public ScrimConfirmadoEvent(String scrimId) { this.scrimId = scrimId; }
    public String getScrimId() { return scrimId; }
}
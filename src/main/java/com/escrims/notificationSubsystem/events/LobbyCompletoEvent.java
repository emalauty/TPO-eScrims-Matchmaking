package com.escrims.notificationSubsystem.events;

public class LobbyCompletoEvent implements DomainEvent {
    private final String scrimId;
    public LobbyCompletoEvent(String scrimId) { this.scrimId = scrimId; }
    public String getScrimId() { return scrimId; }
}
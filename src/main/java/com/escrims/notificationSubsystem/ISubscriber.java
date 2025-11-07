package com.escrims.notificationSubsystem;
import com.escrims.notificationSubsystem.events.DomainEvent;

/**
 * Contrato del Patron Observer.
 * Cualquiera que quiera "escuchar" eventos
 * debe implementar esta interfaz.
 */
public interface ISubscriber {
    void onEvent(DomainEvent event);
}
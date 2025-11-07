package com.escrims.notificationSubsystem;

/**
 * Contrato del Patron Abstract Factory.
 * Sabe "que" crear (notificadores), pero no "como".
 * El "como" lo definen las fabricas concretas.
 */
public interface INotifierFactory {
    IMetodoNotificacion createEmail();
    IMetodoNotificacion createPush();
    IMetodoNotificacion createChat();
}
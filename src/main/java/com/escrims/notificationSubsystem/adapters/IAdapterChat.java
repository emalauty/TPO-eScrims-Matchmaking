package com.escrims.notificationSubsystem.adapters;

public interface IAdapterChat {
    void enviarMensajeWebhook(String webhookURL, String messageContent);
}
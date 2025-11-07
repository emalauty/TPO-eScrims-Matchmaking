package com.escrims.notificationSubsystem;

import com.escrims.notificationSubsystem.adapters.IAdapterChat;
import java.io.File; // Import nuevo

/**
 * Componente Concreto: La implementacion base
 * para enviar un mensaje de Chat (Discord).
 * ¡AHORA CUMPLE EL CONTRATO DE IMetodoNotificacion!
 */
public class ChatNotifier implements IMetodoNotificacion {

    private IAdapterChat adapter;

    public ChatNotifier(IAdapterChat adapter) {
        this.adapter = adapter;
    }

    /**
     * Metodo simple (el original)
     */
    @Override
    public void notificar(Notificacion n) {
        // "Traduce" al formato del adapter de Chat
        String webhookURL = n.getDestinatario(); // Asumimos que el destinatario es la URL del webhook
        String messageContent = n.getPayload();

        adapter.enviarMensajeWebhook(webhookURL, messageContent);
    }

    /**
     * Metodo sobrecargado (¡El que faltaba!)
     * Se llama cuando se intenta enviar un adjunto.
     */
    @Override
    public void notificar(Notificacion n, File adjunto) {
        // Las notificaciones de Chat no soportan adjuntos.
        // Ignoramos el adjunto y llamamos al metodo simple.
        if (adjunto != null) {
            System.out.println("ChatNotifier: Adjunto .ics ignorado para notificacion de chat.");
        }
        this.notificar(n); // Llama al metodo original (solo texto)
    }
}
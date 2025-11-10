package com.escrims.notificationSubsystem;

import com.escrims.notificationSubsystem.adapters.IAdapterChat;
<<<<<<< HEAD
import java.io.File;

/**
 * Componente Concreto: Chat (Discord).
 * AHORA IMPLEMENTA AMBOS METODOS.
=======
import java.io.File; // Import nuevo

/**
 * Componente Concreto: La implementacion base
 * para enviar un mensaje de Chat (Discord).
 * ¡AHORA CUMPLE EL CONTRATO DE IMetodoNotificacion!
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588
 */
public class ChatNotifier implements IMetodoNotificacion {

    private IAdapterChat adapter;

    public ChatNotifier(IAdapterChat adapter) {
        this.adapter = adapter;
    }

<<<<<<< HEAD
    @Override
    public void notificar(Notificacion n) {
        // "Traduce" al formato del adapter de Chat
        String webhookURL = n.getDestinatario();
=======
    /**
     * Metodo simple (el original)
     */
    @Override
    public void notificar(Notificacion n) {
        // "Traduce" al formato del adapter de Chat
        String webhookURL = n.getDestinatario(); // Asumimos que el destinatario es la URL del webhook
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588
        String messageContent = n.getPayload();

        adapter.enviarMensajeWebhook(webhookURL, messageContent);
    }

    /**
     * Metodo sobrecargado (¡El que faltaba!)
<<<<<<< HEAD
     * Las notificaciones de Chat no soportan adjuntos.
     */
    @Override
    public void notificar(Notificacion n, File adjunto) {
        if (adjunto != null) {
            System.out.println("ChatNotifier: Adjunto .ics ignorado para notificacion de chat.");
        }
        this.notificar(n); // Llama al metodo simple (solo texto)
=======
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
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588
    }
}
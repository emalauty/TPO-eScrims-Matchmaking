package com.escrims.notificationSubsystem;

import com.escrims.notificationSubsystem.adapters.IAdapterPush;
<<<<<<< HEAD
import java.io.File;
=======
import java.io.File; // Import nuevo
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588

/**
 * Componente Concreto: Push.
 * AHORA IMPLEMENTA AMBOS METODOS.
 */
public class PushNotifier implements IMetodoNotificacion {

    private IAdapterPush adapter;

    public PushNotifier(IAdapterPush adapter) {
        this.adapter = adapter;
    }

    @Override
    public void notificar(Notificacion n) {
        // "Traduce" al formato del adapter de Push
        adapter.enviarPush(n.getDestinatario(), n.getPayload());
    }

<<<<<<< HEAD
    /**
     * Metodo sobrecargado (¡El que faltaba!)
     * Las notificaciones Push no soportan adjuntos.
     */
    @Override
    public void notificar(Notificacion n, File adjunto) {
        if (adjunto != null) {
            System.out.println("PushNotifier: Adjunto .ics ignorado para notificacion push.");
        }
        this.notificar(n); // Llama al metodo simple (solo texto)
=======
    @Override
    public void notificar(Notificacion n, File adjunto) {
        // Las notificaciones Push no soportan adjuntos.
        // Ignoramos el adjunto y llamamos al metodo simple.
        System.out.println("PushNotifier: Adjunto ignorado para notificacion push.");
        this.notificar(n);
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588
    }
}
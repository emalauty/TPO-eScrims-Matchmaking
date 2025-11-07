package com.escrims.notificationSubsystem;

import com.escrims.notificationSubsystem.adapters.IAdapterPush;
import java.io.File; // Import nuevo

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

    @Override
    public void notificar(Notificacion n, File adjunto) {
        // Las notificaciones Push no soportan adjuntos.
        // Ignoramos el adjunto y llamamos al metodo simple.
        System.out.println("PushNotifier: Adjunto ignorado para notificacion push.");
        this.notificar(n);
    }
}
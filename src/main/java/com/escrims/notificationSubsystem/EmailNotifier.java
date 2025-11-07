package com.escrims.notificationSubsystem;

import com.escrims.notificationSubsystem.adapters.IAdapterEmail;
import java.io.File; // Import nuevo

/**
 * Componente Concreto: Email.
 * AHORA IMPLEMENTA AMBOS METODOS.
 */
public class EmailNotifier implements IMetodoNotificacion {

    private IAdapterEmail adapter;

    public EmailNotifier(IAdapterEmail adapter) {
        this.adapter = adapter;
    }

    /**
     * Implementacion simple (llama a la completa con adjunto null)
     */
    @Override
    public void notificar(Notificacion n) {
        this.notificar(n, null);
    }

    /**
     * Implementacion completa (con adjunto)
     */
    @Override
    public void notificar(Notificacion n, File adjunto) {
        // "Traduce" nuestro objeto Notificacion generico
        // a los parametros especificos del Adapter de Email
        adapter.enviar(
                "lautaroaburrido1214@gmail.com", // El mail verificado en SendGrid
                n.getDestinatario(),
                "Novedades de tu Scrim",
                n.getPayload(),
                adjunto // Pasa el adjunto (puede ser null)
        );
    }
}
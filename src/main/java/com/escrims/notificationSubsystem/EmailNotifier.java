package com.escrims.notificationSubsystem;

import com.escrims.notificationSubsystem.adapters.IAdapterEmail;
<<<<<<< HEAD
import java.io.File;
=======
import java.io.File; // Import nuevo
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588

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
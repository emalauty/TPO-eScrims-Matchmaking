package com.escrims.notificationSubsystem;

<<<<<<< HEAD
import java.io.File;
=======
import java.io.File; // Import nuevo
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588

/**
 * Patron Decorator: El "molde" abstracto.
 * AHORA IMPLEMENTA AMBOS METODOS.
 */
public abstract class NotificadorDecorator implements IMetodoNotificacion {

    protected IMetodoNotificacion wrappee;

    public NotificadorDecorator(IMetodoNotificacion wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public void notificar(Notificacion n) {
        // Delega al metodo simple
        wrappee.notificar(n);
    }

    @Override
    public void notificar(Notificacion n, File adjunto) {
        // Delega al metodo con adjunto
        wrappee.notificar(n, adjunto);
    }
}
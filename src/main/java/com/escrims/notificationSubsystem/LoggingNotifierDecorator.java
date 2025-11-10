package com.escrims.notificationSubsystem;

<<<<<<< HEAD
import java.io.File;
=======
import java.io.File; // Import nuevo
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588

/**
 * Decorador Concreto: Agrega logging.
 * AHORA LOGUEA AMBOS METODOS.
 */
public class LoggingNotifierDecorator extends NotificadorDecorator {

    public LoggingNotifierDecorator(IMetodoNotificacion wrappee) {
        super(wrappee);
    }

    @Override
    public void notificar(Notificacion n) {
        System.out.println("[LOG] Enviando notificacion (simple): " + n.getId());
        super.notificar(n);
    }

    @Override
    public void notificar(Notificacion n, File adjunto) {
        String logAdjunto = (adjunto != null) ? " con adjunto: " + adjunto.getName() : "";
        System.out.println("[LOG] Enviando notificacion (con adjunto): " + n.getId() + logAdjunto);
        super.notificar(n, adjunto);
    }
}
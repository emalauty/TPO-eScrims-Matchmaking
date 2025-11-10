package com.escrims.notificationSubsystem;

<<<<<<< HEAD
import java.io.File;

/**
 * Contrato del Producto (Factory) y Componente (Decorator).
 * AHORA SOPORTA ADJUNTOS.
 */
public interface IMetodoNotificacion {

    // Metodo simple
    void notificar(Notificacion n);

    // Metodo sobrecargado con adjunto
    void notificar(Notificacion n, File adjunto);
=======
import java.io.File; // Import nuevo

public interface IMetodoNotificacion {
    // Sobrecargamos el metodo
    void notificar(Notificacion n);
    void notificar(Notificacion n, File adjunto); // ¡NUEVO METODO!
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588
}
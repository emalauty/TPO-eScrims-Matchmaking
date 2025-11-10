package com.escrims.notificationSubsystem.adapters;

import java.io.File; // Import nuevo

/**
 * Contrato del Adapter de Email.
 * AHORA SOPORTA ADJUNTOS.
 */
public interface IAdapterEmail {
<<<<<<< HEAD

    // Metodo simple (el que ya teniamos)
=======
    // Metodo simple
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588
    void enviar(String from, String to, String subject, String body);

    // Metodo sobrecargado con adjunto
    void enviar(String from, String to, String subject, String body, File adjunto);
}